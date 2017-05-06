package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.RMI;

public class RMIClient {
	boolean connected = false;
	
	public static void main(String[] args) {
		RMIClient client = new RMIClient();
		client.connectToServer();
	}
	
	public boolean connectToServer() {
		try {
			Registry reg = LocateRegistry.getRegistry(null, 1098);
			RMI remoteServer = (RMI) reg.lookup("server");
			System.out.println("Connected to server.");
			connected = true;
			String[] text = remoteServer.getData(new String[]{"Hello ", "my name is ", "Srihari"});
			String texticle = text[0] + text[1];
			System.out.println(texticle);
		} catch(Exception e) {
			System.out.println("connectToServer() : " + e);
		}
		return isConnected();
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	/*public static void unbind() {
		try {
			reg.unbind("server");
		} catch(Exception e) {
			System.out.println(e);
		}
	}*/
}
