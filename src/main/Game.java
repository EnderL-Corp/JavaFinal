package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends GameClient{
	public static void main(String[] args) {
		Game g = new Game();
		JFrame j = new JFrame("Title");
		JPanel p = new JPanel(new GridBagLayout());
		JLabel l = new JLabel("JFrame test");
		JButton b = new JButton("Test Action"); 
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Action Performed.");
			}
		});
		//p.add(l);

		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 1;		
		p.add(b, c);
		
		c.gridx = 0;
		c.gridy = 2;
		p.add(l, c);
		
		
		//j.add(p);
		j.setSize(300, 400);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c.gridx = -1;
		c.gridy = 0;
		if(g.connectToServer())
			p.add(new JLabel("Successfully connected!!"), c);

		j.add(p);
	}
}
