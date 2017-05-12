package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.ClientCommand;
import rmi.GameServerInterface;

public class GameClient implements ActionListener, Serializable {
	private boolean connected = false;
	private Registry reg;
	private int tag;
	private String ip;
	private final int PORT = 1099;
	
	public static void main(String[] args) {
		GameClient client = new GameClient(0, "127.0.0.1");
		client.connectToServer();
	}
	
	public GameClient() {}
	
	public GameClient(int tag, String ip) {
		this.tag = tag;
		this.ip = ip;
	}
	
	public boolean connectToServer() {
		int line = 0;
		try {
			line++;
			reg = LocateRegistry.getRegistry(ip, PORT);
			line++;
			GameServerInterface remoteServer = (GameServerInterface) reg.lookup("server");
			line++;			
			remoteServer.connect(this);
			line++;
			System.out.println("Connected to server.");
			line++;
			connected = true;
			line++;
			String[] text = remoteServer.getData(new String[]{"Hello ", "my name is ", "Srihari"});
			String text2 = text[0] + text[1];
			System.out.println(text2);
		} catch(Exception e) {
			System.out.println("connectToServer() " + line+ ":");
			e.printStackTrace();
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
			try {
				for(ClientCommand c : ((GameServerInterface) reg).getCommands()) {
					c.performAction();
				}
			} catch (RemoteException e1) {
				System.out.println("actionPerformed() : " + e);
			}
		}
	}
}
