package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import graphics.BoardPanel;

public class Game extends GameClient{
	public void startup(String[] args) {
		if(connectToServer())
			System.out.println("Connected!!");
		BoardPanel b = new BoardPanel();
		
		JFrame j = new JFrame();
		JPanel p = new JPanel();
		JButton butt = new JButton();
		
		butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Moving commander!");
				b.changeCommanderPos(b.getCommanderX() + 1, b.getCommanderY());
			}
		});
		j.add(p.add(butt));
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(300, 400);
		j.setVisible(true);
		
		
		JFrame frame = new JFrame();
		
		frame.setSize(1000,1000);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(b);
		frame.setVisible(true);		
		
	}
}
