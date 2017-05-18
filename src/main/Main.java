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
		Scanner s = new Scanner(System.in);
		System.out.print("Please other client ip: ");
		String otherIP = s.nextLine();
		GameClient g1 = null, g2 = null;
		
		try {
			g1 = new GameClient(0, otherIP, 1098, "1", "2");
			g2 = new GameClient(0, otherIP, 1099, "2", "1");		//temporary port to test on same device
			
			g1.createMyRegistry(1099);
			g2.createMyRegistry(1098);
			
			g1.connectToOther();
			g2.connectToOther();
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//g.startup(null);
		
	}
}
