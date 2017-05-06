package main;
import java.awt.*; 
import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		JFrame j = new JFrame("Title");
		JPanel p = new JPanel();
		JLabel l = new JLabel("JFrame test");
		p.add(l);
		j.add(p);
		j.setSize(300, 400);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
