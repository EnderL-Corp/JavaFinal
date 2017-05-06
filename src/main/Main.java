package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	public static void main(String[] args) {
		JFrame j = new JFrame("Title");
		JPanel p = new JPanel();
		JLabel l = new JLabel("JFrame test");
		JButton b = new JButton("Test Action"); 
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Action Performed.");
			}
		});
		//p.add(l);
		p.add(b);
		j.add(p);
		j.setSize(300, 400);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
