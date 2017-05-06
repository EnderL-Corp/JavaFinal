package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.RMI;

public class RMIClient {
	public static void main(String[] args) {
		RMIClient client = new RMIClient();
		client.connectToServer();
	}
	
	public void connectToServer() {
		try {
			Registry reg = LocateRegistry.getRegistry(null, 1098);
			RMI rmi = (RMI) reg.lookup("server");
			System.out.println("Connected to server.");
			String[] text = rmi.getData(new String[]{"Hello ", "my name is ", "Srihari"});
			String texticle = text[0] + text[1];
			System.out.println(texticle);
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}