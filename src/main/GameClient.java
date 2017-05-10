package main;

import java.awt.event.ActionEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.GameServerInterface;

public class GameClient extends ActionEvent{
	private boolean connected = false;
	
	public static void main(String[] args) {
		GameClient client = new GameClient();
		client.connectToServer();
	}
	
	public GameClient() {
		super(LocateRegistry.getRegistry("127.0.0.1", 1099), ActionEvent.ACTION_PERFORMED, null);
	}
	
	public boolean connectToServer() {
		try {
			Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
			GameServerInterface remoteServer = (GameServerInterface) reg.lookup("server");
			System.out.println("Connected to server.");
			connected = true;
			String[] text = remoteServer.getData(new String[]{"Hello ", "my name is ", "Srihari"});
			String text2 = text[0] + text[1];
			System.out.println(text2);
		} catch(Exception e) {
			System.out.println("connectToServer() : " + e);
		}
		return true;
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
