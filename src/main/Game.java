package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import cards.Amplifier;
import cards.Card;
import cards.Commander;
import cards.Deck;
import cards.Deck.DeckEnum;
import cards.Dragon;
import cards.Entity;
import cards.Gear;
import cards.Mech;
import cards.MovePoint;
import cards.Structure;
import cards.Technique;
import cards.Troop;
import cards.TroopEnum;
import cards.Amplifier.AmpEnum;
import graphics.GameMenu;
import graphics.MainMenu;
import rmi.ClientInfo;
import rmi.GameClient;
import rmi.GameServer;

/**
 * Class representing the physical card game. It is a GameClient.
 * @author Srihari Subramanian, Andre Artaud, Luke Letourneau
 */
public class Game extends GameClient implements Serializable {
	private static final long serialVersionUID = -188401400677518168L;

	private ArrayList<Card> myCards;
	private ArrayList<Card> graveyard;
	private ArrayList<Card> myHand;
	public static Game game;

	private Entity[][] recentBoard = new Entity[15][15];

	private Commander commander;
	private Deck deck;
	private Color playerColor;
	private GameMenu gameMenu;

	private char currentPlayerAction;
	private List<Card> queuedPlayerActions;
	private Amplifier[] ampPanel = new Amplifier[5];

	private int ap;
	private int cp;
	private int tp;
	private int territory;

	private int testCounter;

	private boolean myTurn;
	private boolean boardChanged;

	/**
	 * Required no-args constructor for RMI.
	 * @throws RemoteException
	 */
	public Game() throws RemoteException {

	}

	/**
	 * Constructor for a connection between multiple computers, with no ties to
	 * the actual game.
	 * @param tag Tag of this GameClient
	 * @param serverIP IP of the server
	 * @param serverPort Port at which the server is running
	 * @throws RemoteException
	 */
	public Game(int tag, String serverIP, int serverPort) throws RemoteException {
		super(tag, serverIP, serverPort);
	}

	/**
	 * Constructor to be used for actual gameplay, involving a deckEnum for the
	 * player's class.
	 * @param tag Tag of this GameClient
	 * @param serverIP IP of the server
	 * @param serverPort Port at which the server is running
	 * @param deckEnum The player's class
	 * @throws RemoteException
	 */
	public Game(int tag, String serverIP, int serverPort, Color playerColor) throws RemoteException {
		this(tag, serverIP, serverPort);
		this.playerColor = playerColor;
	}

	/**
	 * Called after creating Game. Used to avoid Null Pointer Exceptions as the
	 * constructors for some of these objects need the ability to call methods
	 * off of Game, which in the constructor is not fully constructed.
	 * 
	 * @param deckEnum the DeckEnum of this Game (the player's class)
	 */
	public void init(DeckEnum deckEnum) {
		deck = new Deck(deckEnum);
		graveyard = new ArrayList<Card>();
		myHand = new ArrayList<Card>();
		commander = new Commander("Jimmy", "He was a good boy", deck.getClassType(), 7, 2, -1);
		myCards = deck.getDeck();
		cp = deck.getCP();
		ap = deck.getAP();
		tp = deck.getTP();
		territory = deck.getTerritory();
		for (int i = 0; i < 5; i++) {
			updateAmpPanel(new Amplifier(AmpEnum.NONE), false);
		}

		gameMenu = new GameMenu();
		gameMenu.getFrame().setVisible(true);
	}

	/**
	 * Method called every timer event to refresh the gameMenu if it exists.
	 */
	public void refreshBoard() {
		gameMenu.refresh();
	}

	/**
	 * actionPerformed() for this class as it listens upon a timer, which is
	 * declared in GameClient, Game's superclass. Will update board or send
	 * changes in the board to the server when needed.
	 */
	public void actionPerformed(ActionEvent e) {
		System.out.println(this);
		try {
			if (connected)
				if (boardChanged) {
					sendRecentChanges();
					boardChanged = false;
				} else
					refreshBoard();
			updateServerInformation();
			testCounter++;
			if (remoteServer.getConnections() > 1)
				System.out.println(remoteServer.getOtherClient(clientInfo).getTag()); // RMI
																						// TESTER
																						// IF
																						// DELETE
																						// PLEASE
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Adds a card to the graveyard.
	 * @param card the card to place in the graveyard
	 */
	public void addToGraveyard(Card card) {
		graveyard.add(card);
	}

	public int getDeckSize() {
		return myCards.size();
	}

	/**
	 * Will convert the board to a String.
	 */
	public String toString() { // DELETE UNNECESSARY METHOD
		String string = "";
		for (int i = 0; i < recentBoard.length; i++) {
			for (int j = 0; j < recentBoard.length; j++) {
				if (recentBoard[i][j] != null)
					string += recentBoard[i][j].getName() + "   ";
				else
					string += "null\t";
			}
			string += "\n";
		}
		return string;
	}

	/**
	 * Will begin Game.game as the host of the game.
	 * @param serverIP the IP of the server on which the server will run.
	 */
	public static void createHost(String serverIP) {
		GameServer gs = null;
		try {
			gs = new GameServer(serverIP, 1099);
			gs.createMyRegistry();
			game = new Game(0, serverIP, 1099, Color.BLUE);
			game.init(DeckEnum.RAVAGER);
			game.connectToServer();
			game.clientInfo = new SpecificClientInfo(game.getName(), game.commander, game.getTag(), game.cp, game.ap,
					game.tp);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Will begin Game.game as the client of the game.
	 * @param serverIP the IP at which the server is running
	 */
	public static void createClient(String serverIP) {
		try {
			game = new Game(1, serverIP, 1099, Color.RED);
			game.init(DeckEnum.DJ);
			game.connectToServer();
			game.clientInfo = new SpecificClientInfo(game.getName(), game.commander, game.getTag(), game.cp, game.ap,
					game.tp);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Will begin the game by launching the MainMenu
	 * @param args
	 */
	public static void main(String[] args) {
		new MainMenu();
	}

	/**
	 * Will remove a card from the deck and add it to your hand.
	 */
	public void drawCard() {
		getHand().add(getDeck().remove(0));
	}

	public int getCP() {
		return cp;
	}

	public int getAP() {
		return ap;
	}

	public int getTP() {
		return tp;
	}

	public Commander getCommander() {
		return commander;
	}

	public ArrayList<Card> getDeck() {
		return myCards;
	}

	public ArrayList<Card> getHand() {
		return myHand;
	}

	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}

	/**
	 * Will get the entity at the x and y coordinates specified.
	 * @param x the x-position of the entity
	 * @param y the y-position of the entity
	 * @return the entity at the specified position.
	 */
	public Entity getEntityAt(int x, int y) {
		return recentBoard[x][y];
	}

	public Entity[][] getBoard() {
		return recentBoard;
	}

	public Color getColor() {
		return playerColor;
	}

	/**
	 * Will end the turn of the current player.
	 */
	public void endTurn() {
		try {
			remoteServer.endMyTurn();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Will get the other client represented by a ClientInfo object.
	 * @return the recent information on the other client, updated every timer event
	 */
	public ClientInfo getOtherClient() {
		return otherClientInfo;
	}

	/**
	 * Will end the game as the winner or loser.
	 * @param winOrLose the boolean representing a win or a loss
	 */
	public void endGame(boolean winOrLose) {
		try {
			if (winOrLose) {
				remoteServer.gameOver(clientInfo);
				gameMenu.getLog().publish("Congratulations, you won!");
			} else
				gameMenu.getLog().publish("Sorry, you lost!");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Every timer tick, the recent board, current player turn, and info of the
	 * other client are updated. Additionally, the program checks for a win or a
	 * loss
	 */
	public void updateServerInformation() {
		try {
			recentBoard = remoteServer.getBoard();
			myTurn = remoteServer.getTurnTag() == getTag();
			otherClientInfo = remoteServer.getOtherClient(clientInfo);
			if (remoteServer.getWinner() != null && remoteServer.getWinner().getTag() == getTag()) {
				endGame(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to send the recently changed BoardPanel to the server for storage.
	 */
	public void sendRecentChanges() {
		try {
			remoteServer.updateBoard(clientInfo, recentBoard);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Places an Entity on the board as per the Entity's stored x and y
	 * coordinates.
	 * @param e the Entity to place on the board
	 * @return whether the Entity was successfully placed onto the board
	 */
	public boolean placeEntity(Entity e) {
		if (recentBoard[e.getPosX()][e.getPosY()] == null) {
			recentBoard[e.getPosX()][e.getPosY()] = e;
			boardChanged = true;
			return true;
		}
		return false;
	}

	public char getCurrentPlayerAction() {
		return currentPlayerAction;
	}

	/**
	 * Gets the Amplifier at a certain position in the ampPanel.
	 * @param slot the slot to look in for the Amplifier
	 * @return the Amplifier at the slot.
	 */
	public Amplifier getAmpAt(int slot) {
		return ampPanel[slot];
	}

	/**
	 * Will either take or add the specified amplifier based on the boolean
	 * take.
	 * @param amp Amplifier to add or remove
	 * @param take whether to remove or add
	 * @return whether the AmpPanel has been updated or not.
	 */
	public boolean updateAmpPanel(Amplifier amp, boolean take) {
		boolean updated = false;

		if (take) {
			for (int i = 0; i < ampPanel.length; i++) {
				if (Game.game.getAmpAt(i).getName() == this.getName() && updated == false) // XXX Replace "this" with "amp"?
				{
					ampPanel[i] = new Amplifier(AmpEnum.NONE);
					updated = true;
				}
			}
		} else {
			for (int i = 0; i < ampPanel.length; i++) {
				if (ampPanel[i] == null && updated == false) {
					ampPanel[i] = amp;
					updated = true;
				}
			}
		}
		boardChanged = true;
		return updated;
	}

	public void setCurrentPlayerAction(char a) {
		currentPlayerAction = a;
	}

	/**
	 * Adds specified card to the ArrayList of queuedPlayerActions.
	 * @param c the Card to add to the action queue
	 */
	public void addToPlayerActionQueue(Card c) {
		queuedPlayerActions.add(c);
	}

	public void clearPlayerActionQueue() {
		queuedPlayerActions.clear();
	}

	/**
	 * Will be called frequently and do nothing until there is a full selection
	 * of actions to act upon.
	 */
	public void checkPlayerActionQueue() {
		if (queuedPlayerActions.size() == 2 && (currentPlayerAction != '\n' || currentPlayerAction != 't')) {
			executePlayerActionQueue();
		} else if (currentPlayerAction == 't' && queuedPlayerActions.get(0) instanceof Technique
				&& queuedPlayerActions.size() == ((Technique) queuedPlayerActions.get(0)).getNumTargets() + 1) {
			executePlayerActionQueue();
		}
	}

	/**
	 * TODO add javadoc to this.
	 */
	public void executePlayerActionQueue() {
		Card first = queuedPlayerActions.get(0);
		Card second = queuedPlayerActions.get(1);
		boardChanged = false;
		if (myTurn) {
			switch (currentPlayerAction) {
			case 'm':
				if (first instanceof Entity && second instanceof MovePoint)
					; {
				ap = Entity.move(((Entity) first), ap, ((MovePoint) second).getX(), ((MovePoint) second).getY());
				boardChanged = true;
			}
				break;

			case 'a':
				if (first instanceof Entity && second instanceof Entity) {
					if (!(((Entity) first).hasAbility(3) && second instanceof Commander)) {
						((Entity) first).attack((Entity) second);
						boardChanged = true;
					}
				}
				break;

			case 't':
				if (first instanceof Technique && second instanceof Troop) {
					if (((Technique) first).canCast(tp)) {
						for (int i = 1; i < queuedPlayerActions.size(); i++) {
							if (queuedPlayerActions.get(i) instanceof Troop) {
								((Technique) first).cast((Troop) second);
								boardChanged = true;
							} else {
								break;
							}
						}
					}
				}

			case 'g':
				if (first instanceof Gear && second instanceof Troop) {
					((Gear) first).effect((Troop) second);
					boardChanged = true;
				}
				break;

			case 'p':
				if (first instanceof Troop && myHand.contains(first) && second instanceof MovePoint) {
					((Troop) first).setCoords((MovePoint) second);
					placeEntity((Entity) first);
					boardChanged = true;
				}
				break;

			case 's':
				if (first instanceof Amplifier && second instanceof Amplifier
						&& ((Amplifier) second).getAmpType().equals(AmpEnum.NONE))
					; {
				for (int i = 0; i < 5; i++) {
					if (getAmpAt(i) == second) {
						updateAmpPanel((Amplifier) first, false);
						boardChanged = true;
					}
				}
			}
				break;
			}
		}
		currentPlayerAction = '\n';
		clearPlayerActionQueue();
	}

	public GameMenu getGameMenu() {
		return gameMenu;
	}

	public Amplifier[] getAmpArray() {
		return ampPanel;
	}
}
