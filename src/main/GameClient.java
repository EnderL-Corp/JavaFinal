package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.ClientCommand;
import rmi.GameServerInterface;

public class GameClient implements ActionListener {
	private boolean connected = false;
	private static Registry reg = getRegistry("127.0.0.1", 1099);
	private int tag;
	
	public static void main(String[] args) {
		GameClient client = new GameClient(0);
		client.connectToServer();
	}
	
	public GameClient(int tag) {
		this.tag = tag;
	}
	
	public boolean connectToServer() {
		try {
			//reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
			GameServerInterface remoteServer = (GameServerInterface) reg.lookup("server");
			remoteServer.connect(this);
			System.out.println("Connected to server.");
			connected = true;
			//String[] text = remoteServer.getData(new String[]{"Hello ", "my name is ", "Srihari"});
			//String text2 = text[0] + text[1];
			//System.out.println(text2);
		} catch(Exception e) {
			System.out.println("connectToServer() : " + e);
		}
		return true;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	private static Registry getRegistry(String address, int port) {
		Registry reg = null;
		try {
			reg = LocateRegistry.getRegistry(address, port);
		} catch(Exception e) {
			System.out.println("connectToServer() : " + e);
		}
		return reg;
	}
	
	public int getTag() {
		return tag;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof GameClient && (((GameClient)e.getSource()).getTag() != tag)) {
			for(ClientCommand c : ((GameServerInterface) reg).getCommands()) {
				c.performAction();
			}
		}
	}
}
