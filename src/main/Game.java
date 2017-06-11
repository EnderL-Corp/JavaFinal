package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import cards.Card;
import cards.Commander;
import cards.Deck;
import cards.Deck.Decks;
import cards.Entity;
import graphics.BoardPanel;
import rmi.ClientInfo;
import rmi.GameClient;

/**
 * Class representing the physical card game. It is a GameClient.
 * 
 * @author Srihari Subramanian, Andre Artaud
 *
 */
public class Game extends GameClient implements Serializable {

	private static final long serialVersionUID = -188401400677518168L;
	private ArrayList<Card> myCards;
	private static ArrayList<Card> graveyard = new ArrayList<Card>();
	public static Game game;
	public static Entity[][] board = new Entity[15][15];

	public Game() throws RemoteException {

	}

	/**
	 * 
	 * @param tag
	 * @param otherIP
	 *            IP of other client
	 * @param port
	 *            Port to connect to on other device
	 * @param refTag
	 *            Only has to be filled out if testing is carried out on same
	 *            device. Make note of the refTag when using it. Null if
	 *            multiple devices
	 * @throws RemoteException
	 */
	public Game(int tag, String serverIP, String refTag) throws RemoteException {
		super(tag, serverIP, refTag);
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
	public Game(int tag, String serverIP, int serverPort, Decks deckEnum) throws RemoteException {
		this(tag, serverIP, serverPort);
		myCards = new Deck(deckEnum).getDeck();
	}

	private BoardPanel b;
	private JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	// TODO The following are tester methods for RMI
	public void moveCommRight() {
		b.changeCommanderPos(b.getCommanderX(), b.getCommanderY() + 1);
		frame.repaint();
	}

	public void moveCommLeft() {
		b.changeCommanderPos(b.getCommanderX(), b.getCommanderY() - 1);
		frame.repaint();
	}

	public void moveCommUp() {
		b.changeCommanderPos(b.getCommanderX() - 1, b.getCommanderY());
		frame.repaint();
	}

	public void moveCommDown() {
		b.changeCommanderPos(b.getCommanderX() + 1, b.getCommanderY());
		frame.repaint();
	}

	public void startup(String[] args) {
		b = new BoardPanel();

		JFrame j = new JFrame();
		j.setTitle(getName());

		JPanel p = new JPanel();
		JButton right = new JButton("Move Right");
		JButton left = new JButton("Move Left");
		JButton up = new JButton("Move Up");
		JButton down = new JButton("Move Down");

		p.add(left);
		p.add(up);
		p.add(right);
		p.add(down);

		j.add(p);

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(300, 400);
		j.setVisible(true);

		frame = new JFrame();

		frame.setTitle(getName());
		frame.setSize(650, 675);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(b);
		frame.setResizable(false);
		frame.setVisible(true);

		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Moving commander right!");
				moveCommRight();
				System.out.println(b.getCommanderX() + ", " + b.getCommanderY());
			}
		});
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Moving commander left!");
				moveCommLeft();
				System.out.println(b.getCommanderX() + ", " + b.getCommanderY());
			}
		});
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Moving commander up!");
				moveCommUp();
				System.out.println(b.getCommanderX() + ", " + b.getCommanderY());
			}
		});
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Moving commander down!");
				moveCommDown();
				System.out.println(b.getCommanderX() + ", " + b.getCommanderY());
			}
		});

	}

	// TODO End Testing

	/**
	 * Will update a card that has been changed in the other client. Only the
	 * actionPerformed() method will ever have to interact with this.
	 * 
	 * @param cardToChange
	 *            The card that has been changed and must be updated
	 * @return true if the card has been successfully updated (it is in the list
	 *         of cards myCards, and is changed appropriately).
	 */
	public boolean updateCard(Card cardToChange) {
		for (Card c : myCards) {
			if (c instanceof Entity && ((Entity) c).getTag() == ((Entity) cardToChange).getTag()) {
				c = cardToChange;
				frame.repaint();
				return true;
			}
		}
		return false;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (connected) {
				ArrayList<Card> cardsList = remoteServer.getRecentCardsList();
				if (remoteServer.getRecentClientName() != getName() && cardsList != null && cardsList.size() > 0)
					for (Card c : cardsList) {
						updateCard(c);
					}
			}
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

	public void endGame(Commander loser) {
		// end the game
	}

	public static void main(String[] args) {
		try {
			game = new Game();
			game.startup(null);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		;
	}

	public String toString() {
		String string = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != null)
					string += board[i][j].getName() + "   ";
				else
					string += "null\t\t";
			}
			string += "\n";
		}
		return string;
	}
}
