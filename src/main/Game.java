package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import graphics.BoardPanel;

public class Game extends GameClient implements Serializable {
	
	/**
	 * 
	 * @param tag
	 * @param otherIP IP of other client
	 * @param port Port to connect to on other device
	 * @param refTag Only has to be filled out if testing is carried out on same device. Make note of the refTag when using it. 
	 * 			Null if multiple devices
	 * @throws RemoteException
	 */
	public Game(int tag, String otherIP, int port, String refTag, String otherRefTag) throws RemoteException {
		super(tag, otherIP, port, refTag, otherRefTag);
	}
	
	private BoardPanel b;
	public void startup(String[] args) {
		if(super.connectToOther())
			System.out.println("Connected!!");
		b = new BoardPanel();
		
		JFrame j = new JFrame();
		JPanel p = new JPanel();
		JButton right = new JButton("Move Right");
		JButton left = new JButton("Move Left");
		JButton up = new JButton("Move Up");
		JButton down = new JButton("Move Down");
		
		p.add(right);
		p.add(left);
		p.add(up);
		p.add(down);
		
		j.add(p);
		
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(300, 400);
		j.setVisible(true);
		
		
		JFrame frame = new JFrame();
		
		frame.setSize(1000,1000);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(b);

		frame.setVisible(true);
		
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Moving commander right!");
				b.changeCommanderPos(b.getCommanderX(), b.getCommanderY() + 1);
				frame.repaint();
				System.out.println(b.getCommanderX() + ", " + b.getCommanderY());
			}
		});
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Moving commander left!");
				b.changeCommanderPos(b.getCommanderX(), b.getCommanderY() - 1);
				frame.repaint();
				System.out.println(b.getCommanderX() + ", " + b.getCommanderY());
			}
		});
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Moving commander up!");
				b.changeCommanderPos(b.getCommanderX() - 1, b.getCommanderY());
				frame.repaint();
				System.out.println(b.getCommanderX() + ", " + b.getCommanderY());
			}
		});
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Moving commander down!");
				b.changeCommanderPos(b.getCommanderX() + 1, b.getCommanderY());
				frame.repaint();
				System.out.println(b.getCommanderX() + ", " + b.getCommanderY());
			}
		});
		
	}
}
