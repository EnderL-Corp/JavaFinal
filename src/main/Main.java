package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("Please enter host ip: ");
		String ip = s.nextLine();
		Game g = new Game(0, ip);
		g.startup(null);
	}
}
