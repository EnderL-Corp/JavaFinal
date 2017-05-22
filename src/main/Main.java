package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rmi.GameClient;

public class Main {
	public static void main(String[] args) {
		//connectSameComp();
		connectDiffComp();
	}
	public static void connectSameComp() {
		Scanner s = new Scanner(System.in);
		System.out.print("Please other client ip: ");
		String otherIP = s.nextLine();
		Game g1 = null, g2 = null;
		
		try {
			g1 = new Game(0, otherIP, 1098, "1", "2");
			g2 = new Game(3, otherIP, 1099, "2", "1");		//temporary port to test on same device
			
			g1.createMyRegistry(1099);
			g2.createMyRegistry(1098);
			g1.startup(null);
			g2.startup(null);
			
			g1.connectToOther();
			g2.connectToOther();
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void connectDiffComp() {
		Scanner s = new Scanner(System.in);
		try {
			System.out.println("Here is your IP: " + java.net.InetAddress.getLocalHost().getHostAddress() + ".");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Please give this address to your opponent to enter for gameplay.\n");
		System.out.print("Please enter your opponent's ip: ");
		String otherIP = s.nextLine();
		Game g1 = null;
		
		System.out.println("Please type a letter and click \"enter\" when your opponent has also entered your IP.");
		s.next();
		
		try {
			g1 = new Game(0, otherIP, 1099);
			
			g1.createMyRegistry(1099);
			g1.startup(null);
			
			g1.connectToOther();
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
