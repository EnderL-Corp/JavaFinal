package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import cards.Amplifier;
import cards.Amplifier.AmpEnum;
import cards.Card;
import cards.Commander;
import cards.Deck;
import cards.Deck.DeckEnum;
import cards.Entity;
import cards.Gear;
import cards.MovePoint;
import cards.Technique;
import cards.Troop;
import graphics.CommandLog;
import graphics.GameMenu;
import graphics.MainMenu;
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
	private MainMenu mainMenu;
	private GameMenu gameMenu;
	private String typeAsString;

	private char currentPlayerAction;
	private List<Card> queuedPlayerActions;
	private Amplifier[] ampPanel = new Amplifier[5];

	private int ap;
	private int cp;
	private int tp;
	private int territory;
	
	private int numPings = 0, otherClientWaiter = 0;
	
	private boolean myTurn, previousTurnCheck = false, isHost;
	private boolean boardChanged = false;
	private int phase;
	private String recentClientDescription = "";
	
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
		String specificDescription = null;
		switch(deckEnum) {
		case DJ:
			typeAsString = "DJ";
			specificDescription = "This is the DJ. Let's get this party jumping!";
			break;
		case RAVAGER:
			typeAsString = "Ravager";
			specificDescription = "I'm the Ravager. Pump that beat!";
			break;
		case SWARM:
			typeAsString = "Swarm";
			specificDescription = "Join the Swarm. Let's rave!";
			break;
		}
		graveyard = new ArrayList<Card>();
		myHand = new ArrayList<Card>();
		commander = new Commander(typeAsString, specificDescription, deck.getClassType(), 1, 7);
		myCards = deck.getDeck();
		cp = deck.getCP();
		ap = deck.getAP();
		tp = deck.getTP();
		territory = deck.getTerritory();
		
		for (int i = 0; i < recentBoard.length; i++) {
			for (int j = 0; j < recentBoard.length; j++) {

				// VOID
				if ((i == 0 || i == recentBoard.length - 1 || j == 0 || j == recentBoard.length - 1)
						|| ((i == 1 || i == recentBoard.length - 2) && (j == 1 || j == 2 || j == 3 || j == 4 || j == recentBoard.length - 2
								|| j == recentBoard.length - 3 || j == recentBoard.length - 4 || j == recentBoard.length - 5))
						|| ((i == 2 || i == recentBoard.length - 3)
								&& (j == 1 || j == 2 || j == recentBoard.length - 2 || j == recentBoard.length - 3))
						|| ((i == 3 || i == 4 || i == recentBoard.length - 4 || i == recentBoard.length - 5)
								&& (j == 1 || j == recentBoard.length - 2))
						|| ((i == 5 || i == recentBoard.length - 6) && (j == 6 || j == 7 || j == recentBoard.length - 7))
						|| ((i == 6 || i == 7 || i == recentBoard.length - 7)
								&& (j == 5 || j == 6 || j == 7 || j == recentBoard.length - 7 || j == recentBoard.length - 6))) {
					recentBoard[i][j] = new Troop();
					((Troop) recentBoard[i][j]).addAbilities(5) ;
				}
			}
		}
		
		shuffleDeck(deck.getDeck());
		drawCard();
		drawCard();
		drawCard();
		drawCard();
		
		queuedPlayerActions = new ArrayList<Card>();
		System.out.println(myHand);
		gameMenu = new GameMenu();
		gameMenu.getFrame().setVisible(true);
		CommandLog.publish("[Game] Loading information...");
		CommandLog.publish("You are of class " + typeAsString + ".\n\"" + specificDescription + "\"");
		if(myTurn) {
			phase = 0;
			CommandLog.publish("[Game] You are now in phase 1. You can:\n\tPlay troops.\n\tUse structures such as Gear and Amplifiers.");
		} else {
			phase = -1;
			CommandLog.publish("[Game] Currently opponent's turn.");
		}
		if(!isHost) {
			commander.setCoords(new MovePoint(13, 7));
		}
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
		numPings++;
		if(numPings == 1) {						//Once connected, will get the most recent server information and will place its own commander
			if(isHost)
				updateServerInformation();
			placeEntity(commander);
			boardChanged = true;
			if(!isHost)
				updateServerInformation();
		}
		System.out.println(playerColor);
		System.out.println(queuedPlayerActions + " " + currentPlayerAction);
		try {
			if(myTurn && !previousTurnCheck) {
				changePhase();
			}
			if(connected)
				if (myTurn && boardChanged) {
					sendRecentChanges();		//If it's your turn and the board was changed, update the server board info
					boardChanged = false;
				} else
					refreshBoard();
			updateServerInformation();
			if (remoteServer.getConnections() > 1) {
				otherClientWaiter++;
				if(otherClientWaiter > 1) {		//Wait one seconds for the other client to update its info in the server
					if(!isHost) {
						clientInfo.setCommander(commander);
						remoteServer.updateInfo(clientInfo);
					}
					if(isHost && otherClientWaiter == 3) {
						otherClientInfo = remoteServer.getOtherClient(clientInfo);
						placeEntity(otherClientInfo.getCommander());
						CommandLog.publish("Opponent has connected! Fight!");
					}
					if(!myTurn && remoteServer.getRecentClientTag() != this.getTag()) {
						String tempDesc = remoteServer.getRecentClientActionDescription();
						if(!recentClientDescription.equals(tempDesc)) {
							recentClientDescription = tempDesc;
							CommandLog.publish(recentClientDescription);
						}
					}
				}
			}
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
	
	public String getTypeAsString() {
		return typeAsString;
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
			game = new Game(0, serverIP, 1099, Color.CYAN);
			game.myTurn = true;
			game.isHost = true;
			game.init(DeckEnum.values()[(int)(Math.random() * 3)]);
			game.clientInfo = new ClientInfo(game.getName(), game.getCommander(), game.getTag(), game.cp, game.ap, game.tp);
			game.connectToServer(game.clientInfo);
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
			game.myTurn = false;
			game.isHost = false;
			game.init(DeckEnum.values()[(int)(Math.random() * 3)]);
			game.clientInfo = new ClientInfo(game.getName(), game.getCommander(), game.getTag(), game.cp, game.ap, game.tp);
			game.connectToServer(game.clientInfo);
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
	
	public void setGameMainMenu(MainMenu m)
	{
		mainMenu = m;
	}
	
	public void changePhase() {
		phase = ++phase > 2 ? -1 : phase;
		switch(phase) {
		case -1:
			CommandLog.publish("[Game] Currently opponent's turn.");
			break;
		case 0:
			CommandLog.publish("[Game] You are now in phase 1. You can:\n\tPlay troops.\n\tUse structures such as Gear and Amplifiers.");
			break;
		case 1:
			CommandLog.publish("[Game] You are now in phase 2. You can:\n\tMove troops.\n\tUse techniques.");
			break;
		case 2:
			endTurn();
			break;
		}
	}

	/**
	 * Will remove a card from the deck and add it to your hand.
	 */
	public void drawCard() {
		Card c = getDeck().remove(0);
		getHand().add(c);
		c.updateDescription();
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
	
	public int getPhase() {
		return phase;
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
	
	public ArrayList<Card> emptyGraveyard() 
    {
		ArrayList<Card> tempGraveyard = graveyard;
        graveyard = new ArrayList<Card>();
        return tempGraveyard;
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
			myTurn = false;
			CommandLog.publish("[Game] You have ended your turn.");
			List<Amplifier> found = new ArrayList<Amplifier>();
			for(int i = 0; i < 5; i++)
			{
				if(found.contains(ampPanel[i]) && (ampPanel[i].getAmpType() == AmpEnum.HEALING_WAVE || ampPanel[i].getAmpType() == AmpEnum.SHORT_CIRCUIT))
				{
					
				}
				else
				{
					ampPanel[i].effectBoard();
					found.add(ampPanel[i]);
				}
			}
			drawCard();
			cp = deck.getCP();
			ap = deck.getAP();
			tp = deck.getTP();
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
				CommandLog.publish("Congratulations, you won!");
			} else
				CommandLog.publish("Sorry, you lost!");
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
			previousTurnCheck = myTurn;
			myTurn = remoteServer.getTurnTag() == getTag();
			otherClientInfo = remoteServer.getOtherClient(clientInfo);
			remoteServer.updateInfo(clientInfo);
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
				if (game.getAmpAt(i).getName() == this.getName() && updated == false) // XXX Replace "this" with "amp"?
				{
					ampPanel[i] = null;
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
		checkPlayerActionQueue();
	}

	public void clearPlayerActionQueue() {
		queuedPlayerActions.clear();
	}

	/**
	 * Will be called frequently and do nothing until there is a full selection
	 * of actions to act upon.
	 */
	public void checkPlayerActionQueue() {
		System.out.println(currentPlayerAction);
		try
		{
			if(currentPlayerAction == 'E') {
				clearPlayerActionQueue();
				currentPlayerAction = '\n';
			} else if (queuedPlayerActions.size() == 2 && currentPlayerAction != '\n' && currentPlayerAction != 'T') {
				executePlayerActionQueue();
			} else if (currentPlayerAction == 'T' && queuedPlayerActions.get(0) instanceof Technique
					&& queuedPlayerActions.size() == ((Technique) queuedPlayerActions.get(0)).getNumTargets() + 1) {
				System.out.println("gay lemon");
				executePlayerActionQueue();
			}
		}
		catch (Exception e)
		{
			
		}
	}

	/**
	 * <code>executePlayerActionQueue()</code> is responsible for the movement, attacking, and placing of
	 * Entities, as well as the placement of Gear, Amplifiers, and Techniques. The method gets the action
	 * the player wants to execute from <code>currentPlayerAction</code> and then checks to see if the
	 * selections made for that actions are appropriate. If they are, the correct method is run to do the
	 * action of the Card.
	 */
	public void executePlayerActionQueue() {
		Card first = queuedPlayerActions.get(0);
		Card second;
		try
		{
			second = queuedPlayerActions.get(1);
		}
		catch (Exception e)
		{
			second = null;
		}
		
		boardChanged = false;
		if (myTurn) {
			switch (currentPlayerAction) {
				case 'M':	//MOVE
					if (phase == 1 && first instanceof Entity && second instanceof MovePoint) {
						ap = Entity.move(((Entity) first), ap, ((MovePoint) second).getX(), ((MovePoint) second).getY());
						CommandLog.publish("[Game] You are moving " + first.getName() + " to position " + ((MovePoint)second) + ".");
						try {
							remoteServer.setRecentClientActionDescription(
									"[Game] Opponent moved Entity" + first.getName() + " to position " + ((MovePoint)second) + ".");
						} catch (RemoteException e) {
							e.printStackTrace();
						}
						boardChanged = true;
					}
					break;
	
				case 'A':	//ATTACK
					if (phase == 1 && first instanceof Entity && second instanceof Entity)
						if (!(((Entity) first).hasAbility(3) && second instanceof Commander)) {
							((Entity) first).attack((Entity) second);
							CommandLog.publish("[Game] You, Entity " + first.getName() + ", are attacking Entity " + second.getName() + ".");
							try {
								remoteServer.setRecentClientActionDescription(
										"[Game] Opponent, Entity " + first.getName() + ", attacked Entity " + second.getName() + ".");
							} catch (RemoteException e) {
								e.printStackTrace();
							}
							boardChanged = true;
						}
					break;
	
				case 'T':
					if (first instanceof Technique) { 
						if (((Technique) first).canCast(tp)) {
							if(queuedPlayerActions.size() > 1) {
								for (int i = 1; i < queuedPlayerActions.size(); i++)
									if (queuedPlayerActions.get(i) instanceof Troop) {
										((Technique) first).cast((Troop)queuedPlayerActions.get(i));
										if (queuedPlayerActions.get(i) instanceof Troop) {
											CommandLog.publish(
													"[Game] You are using technique " + first.getName() + " on Troop " + second.getName() + ".");
											boardChanged = true;
											try {
												remoteServer.setRecentClientActionDescription(
														"[Game] Opponent used technique " + first.getName() + " on Troop " + second.getName() + ".");
											} catch (RemoteException e) {
												e.printStackTrace();
											}
										}
									}
							}
							else
								((Technique) first).cast(null);
							}
							addToGraveyard(first);
							myHand.remove(first);
						}
						break;
	
				case 'G':
					if (phase == 0 && first instanceof Gear && second instanceof Troop) {
						((Gear)first).effect((Troop)second);
						CommandLog.publish("[Game] You are using the Gear " + first.getName() + " on Troop " + second.getName() + ".");
						try {
							remoteServer.setRecentClientActionDescription(
									"[Game] Opponent used the Gear " + first.getName() + " on Troop " + second.getName() + ".");
						} catch (RemoteException e) {
							e.printStackTrace();
						}
						boardChanged = true;
					}
					break;
	
				case 'P':
					if (phase == 0 && first instanceof Troop && myHand.contains(first) && second instanceof MovePoint) {
						if((getColor() == Color.CYAN && ((MovePoint)second).getX() <= territory) || (getColor() == Color.RED && ((MovePoint)second).getX() >= recentBoard.length - territory - 1))
						{
							System.out.println("Placing Troop");
							((Troop) first).setCoords((MovePoint)second);
							if(cp >= ((Troop)first).getCpCost()) {
								CommandLog.publish("[Game] You are placing the Troop " + first.getName() + " on Position " + ((MovePoint)second) + ".");
								try {
									remoteServer.setRecentClientActionDescription(
											"[Game] Opponent placed the Troop " + first.getName() + " at Position " + ((MovePoint)second) + ".");
								} catch (RemoteException e) {
									e.printStackTrace();
								}
								first.setTeamColor(getColor());
								cp = Troop.placeOnBoard((Troop)first, cp);
							}
							boardChanged = true;
						}
					}
					break;
	
				case 'S':
					if (phase == 0 && first instanceof Amplifier && myHand.contains(first) && !(((Amplifier) first).getAmpType().equals(AmpEnum.NONE))
						&& second instanceof Amplifier && ((Amplifier) second).getAmpType().equals(AmpEnum.NONE));
						for(int i = 0; i < 5; i++) {
							if(ampPanel[i] == null)
							{
								updateAmpPanel((Amplifier) first, false);
								addToGraveyard(first);
								myHand.remove(first);
								CommandLog.publish("[Game] You are using placing the Amplifier " + first.getName() + ".");
								try {
									remoteServer.setRecentClientActionDescription("[Game] Opponent used the Amplifier " + first.getName() + ".");
								} catch (RemoteException e) {
									e.printStackTrace();
								}
								boardChanged = true;
								break;
							}
						}
					break;
			}
		} else {
			CommandLog.publish("[Game] It is currently not your turn.");
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
	
	public ArrayList<Card> shuffleDeck(ArrayList<Card> deck)
	{	
		Collections.shuffle(deck);
		return deck;
	}

	public MainMenu getGameMainMenu() {
		return mainMenu;
	}
}
