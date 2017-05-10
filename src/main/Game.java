package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import graphics.BoardPanel;

public class Game extends GameClient{
	public Game(int tag) {
		super(tag);
	}
	
	BoardPanel b;
	public void startup(String[] args) {
		if(connectToServer())
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
				frame.setVisible(false);
				frame.setVisible(true);
				System.out.println(b.getCommanderX() + ", " + b.getCommanderY());
			}
		});
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Moving commander left!");
				b.changeCommanderPos(b.getCommanderX(), b.getCommanderY() - 1);
				frame.setVisible(false);
				frame.setVisible(true);
				System.out.println(b.getCommanderX() + ", " + b.getCommanderY());
			}
		});
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Moving commander up!");
				b.changeCommanderPos(b.getCommanderX() - 1, b.getCommanderY());
				frame.setVisible(false);
				frame.setVisible(true);
				System.out.println(b.getCommanderX() + ", " + b.getCommanderY());
			}
		});
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Moving commander down!");
				b.changeCommanderPos(b.getCommanderX() + 1, b.getCommanderY());
				frame.setVisible(false);
				frame.setVisible(true);
				System.out.println(b.getCommanderX() + ", " + b.getCommanderY());
			}
		});
		
	}
}
