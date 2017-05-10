package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.ClientCommand;
import rmi.GameServerInterface;

public class GameClient implements ActionListener {
	private boolean connected = false;
	private Registry reg;
	private int tag;
	private String ip;
	private final int PORT;
	
	public static void main(String[] args) {
		GameClient client = new GameClient(0, null);
		client.connectToServer();
	}
	
	public GameClient(int tag, String ip) {
		this.tag = tag;
		this.ip = ip;
		PORT = 1099;
	}
	
	public boolean connectToServer() {
		try {
			reg = LocateRegistry.getRegistry(ip, PORT);
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
	
	/*private static Registry getRegistry(String address, int port) {
		Registry reg = null;
		try {
			reg = LocateRegistry.getRegistry(address, port);
		} catch(Exception e) {
			System.out.println("connectToServer() : " + e);
		}
		return reg;
	}*/
	
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
