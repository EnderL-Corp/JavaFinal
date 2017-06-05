package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import cards.Card;
import cards.Deck;
import cards.Deck.Decks;
import cards.Entity;
import graphics.BoardPanel;
import rmi.GameClient;

public class Game extends GameClient implements Serializable {
	
	private ArrayList<Card> myCards;
	
	public Game() throws RemoteException{
		
	}
	
	/**
	 * 
	 * @param tag
	 * @param otherIP IP of other client
	 * @param port Port to connect to on other device
	 * @param refTag Only has to be filled out if testing is carried out on same device. Make note of the refTag when using it. 
	 * 			Null if multiple devices
	 * @throws RemoteException
	 */
	public Game(int tag, String serverIP, String refTag) throws RemoteException {
		super(tag, serverIP, refTag);
	}
	
	public Game(int i, String otherIP, int j) throws RemoteException {
		super(i, otherIP, j);
	}
	
	public Game(int tag, String serverIP, int serverPort, Decks deckEnum) throws RemoteException {
		this(tag, serverIP, serverPort);
		myCards = new Deck(deckEnum).getDeck();
	}

	private BoardPanel b;
	private JFrame frame;
	
	
	public JFrame getFrame() {
		return frame;
	}
	
	// The following are tester methods for RMI
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
		j.setTitle(name);
		
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
		
		frame.setTitle(name);
		frame.setSize(1000,1000);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(b);

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
	
	public boolean updateCard(Card cardToChange) {
		for(Card c : myCards) {
			if(c instanceof Entity && ((Entity)c).getTag() == ((Entity)cardToChange).getTag()) {
				c = cardToChange;
				frame.repaint();
				return true;
			}
		}
		return false;
	}
	
	public void actionPerformed(ActionEvent e)  {
		try {
			ArrayList<Card> cardsList = remoteServer.getRecentCardsList();
			if(remoteServer.getRecentClientName() != this.name && cardsList != null && cardsList.size() > 0)
				for(Card c : cardsList) {
					updateCard(c);
				}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		/*Scanner s = new Scanner(System.in);
		System.out.print("Please enter server IP: ");
		String serverIP = s.nextLine();
		Game gc = null;
		
		try {
			gc = new Game(1, serverIP, 1099, Decks.RAVAGER);
			
			gc.startup(null);
			
			gc.connectToServer();
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			new Game().startup(null);
		} catch (RemoteException e) {
			e.printStackTrace();
		};
	}
}
