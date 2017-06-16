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
import cards.Entity;
import cards.Gear;
import cards.Structure;
import cards.Technique;
import cards.Troop;
import cards.Amplifier.AmpEnum;
import graphics.GameMenu;
import graphics.MainMenu;
import rmi.GameClient;
import rmi.GameServer;

/**
 * Class representing the physical card game. It is a GameClient.
 * 
 * @author Srihari Subramanian, Andre Artaud
 *
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
	public Amplifier[] ampPanel = new Amplifier[5];
	
	private int ap;
	private int cp;
	private int tp;
	private int territory;
	
	private boolean myTurn;
	
	public Game() throws RemoteException {

	}

	/**
	 * Constructor for a simple connection (supporting multiple computers)
	 * 
	 * @param tag
	 *            Tag of this GameClient
	 * @param serverIP
	 *            IP of the server
	 * @param serverPort
	 *            Port at which the server is running
	 * @throws RemoteException
	 */
	public Game(int tag, String serverIP, int serverPort) throws RemoteException {
		super(tag, serverIP, serverPort);
	}

	/**
	 * Constructor to be used for actual gameplay, involving a deckEnum for the
	 * player's class.
	 * 
	 * @param tag
	 *            Tag of this GameClient
	 * @param serverIP
	 *            IP of the server
	 * @param serverPort
	 *            Port at which the server is running
	 * @param deckEnum
	 *            The player's class
	 * @throws RemoteException
	 */
	public Game(int tag, String serverIP, int serverPort, Color playerColor) throws RemoteException {
		this(tag, serverIP, serverPort);
		this.playerColor = playerColor;
		graveyard = new ArrayList<Card>();
		myHand = new ArrayList<Card>();
	}
	
	/**
	 * Called after creating Game. Used to avoid Null POinter Exceptions as the constructors for some 
	 * of these objects need the ability to call methods off of Game, which in the constructor is
	 * not fully constructed.
	 */
	public void init(DeckEnum deckEnum) {
		deck = new Deck(deckEnum);
		commander = new Commander("Jimmy", "He was a good boy", deck.getClassType(), 7, 2, -1);
		myCards = deck.getDeck();
		cp = deck.getCP();
		ap = deck.getAP();
		tp = deck.getTP();
		territory = deck.getTerritory();
		for(int i = 0; i < 5; i++)
		{
			updateAmpPanel(new Amplifier(AmpEnum.NONE),  false);
		}
		
		gameMenu = new GameMenu();
		gameMenu.getFrame().setVisible(true);
	}
	
	public void refreshBoard() {
		gameMenu.refresh();
	}

	public void actionPerformed(ActionEvent e) {
		updateServerInformation();
		try {
			if(connected)
				if (myTurn)
					sendRecentChanges();
				else
					refreshBoard();
			if (remoteServer.getConnections() > 1)
				for (GameClient ci : remoteServer.getGameClients()) {
					if (ci.getTag() != getTag()) {
						System.out.println(ci.getTag());
					}
				}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void addToGraveyard(Card card) {
		graveyard.add(card);
	}

	/*public void endGame(Commander loser) {		//This is a useless method where the f would it be used
		// end the game
	}*/
	
	public int getDeckSize() {
		return myCards.size();
	}

	public String toString() {
		String string = "";
		for (int i = 0; i < recentBoard.length; i++) {
			for (int j = 0; j < recentBoard.length; j++) {
				if (recentBoard[i][j] != null)
					string += recentBoard[i][j].getName() + "   ";
				else
					string += "null\t\t";
			}
			string += "\n";
		}
		return string;
	}
	
	public static void createHost(String serverIP) {
		GameServer gs = null;		
		try {
			gs = new GameServer("server", 1099, serverIP);
			gs.createMyRegistry();
			game = new Game(0, serverIP, 1099, Color.BLUE);
			game.init(DeckEnum.RAVAGER);
			game.connectToServer();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void createClient(String serverIP) {		
		try {
			game = new Game(1, serverIP, 1099, Color.RED);
			game.init(DeckEnum.DJ);
			game.connectToServer();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MainMenu();
	}
	
	public void drawCard()
	{
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
	public Entity getEntityAt(int x, int y){
		return recentBoard[x][y];
	}
	public Entity[][] getBoard() {
		return recentBoard;
	}
	public Color getColor() {
		return playerColor;
	}
	public void endTurn() {
		try {
			remoteServer.endMyTurn();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void endGame(boolean winOrLose) {
		try {
			if(winOrLose) {
				remoteServer.gameOver(this);
				System.out.println("Congratulations, you've won!");		//Change to command log
			}
			else
				System.out.println("Oh no, you've lost!");				//Change to command log
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void updateServerInformation() {
		try {
			recentBoard = remoteServer.getBoard();
			myTurn = remoteServer.getTurnTag() == getTag();
			if(remoteServer.getWinner() != null && remoteServer.getWinner().getTag() == getTag()) {
				endGame(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendRecentChanges() {
		try {
			remoteServer.updateBoard(this, recentBoard);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public boolean placeEntity(Entity e, int x, int y)
	{
		if(recentBoard[x][y] == null)
		{
			recentBoard[x][y] = e;
			return true;
		}
		return false;
	}
	
	public char getCurrentPlayerAction()
	{
		return currentPlayerAction;
	}
	
	public Amplifier getAmpAt(int slot)
	{
		return ampPanel[slot];
	}
	
	public boolean updateAmpPanel(Amplifier amp, boolean take)
	{
		boolean updated = false;
		
		if(take)
		{
			for(int i = 0; i < ampPanel.length; i++)
			{
				if(Game.game.getAmpAt(i).getName() == this.getName() && updated == false) //XXX Replace "this" with "amp"?
				{
					ampPanel[i] = new Amplifier(AmpEnum.NONE);
					updated = true;
				}
			}
		}
		else
		{
			for(int i = 0; i < ampPanel.length; i++)
			{
				if(ampPanel[i] == null && updated == false)
				{
					ampPanel[i] = amp;
					updated = true;
				}
			}
		}
		
		return updated;
	}
	
	public void setCurrentPlayerAction(char a)
	{
		currentPlayerAction = a;
	}
	
	public void addToPlayerActionQueue(Card c)
	{
		queuedPlayerActions.add(c);
	}
	
	public void clearPlayerActionQueue(Card c)
	{
		queuedPlayerActions.clear();
	}
	
	public void checkPlayerActionQueue()
	{
		if(queuedPlayerActions.size() == 2)
		{
			executePlayerActionQueue();
		}
		else if(queuedPlayerActions.size() > 2)
		{
			//do something
		}
	}
	
	public void executePlayerActionQueue()
	{
		Card first = queuedPlayerActions.get(0);
		Card second = queuedPlayerActions.get(0);
		switch(currentPlayerAction)
		{
			case 'm':
				if(first instanceof Entity)
				{
					//Get a second position SOMEHOW
				}
				break;
				
			case 'a':
				if(first instanceof Entity && second instanceof Entity)
				{
					if(!(((Entity)first).hasAbility(3) && second instanceof Commander))
					{
						((Entity)first).attack((Entity)second);
					}
				}
				break;
				
			case 'p':
				if(first instanceof Technique && second instanceof Troop)
				{
					if(((Technique)first).canCast(tp))
					{
						while (((Technique)first).cast((Troop)second))
						{
							//select next target
						} 
					}
				}
				else if(first instanceof Gear && second instanceof Troop)
				{
					((Gear)first).effect((Troop)second);
				}
				break;
				
			case 's':
				if(first instanceof Amplifier && second instanceof Amplifier && ((Amplifier)second).getAmpType().equals(AmpEnum.NONE));
				{
					for(int i = 0; i < 5; i++)
					{
						if(getAmpAt(i) == second)
						{
							updateAmpPanel((Amplifier)first, false);
						}
					}
				}
				break;
		}
	}
}
