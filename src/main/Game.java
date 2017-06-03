package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import cards.Card;
import graphics.BoardPanel;
import rmi.GameClient;

public class Game extends GameClient implements Serializable {
	w
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

	private BoardPanel b;
	private JFrame frame;
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
	
	public JFrame getFrame() {
		return frame;
	}
	
	// The following are tester classes for RMI
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
	
	public 
}
