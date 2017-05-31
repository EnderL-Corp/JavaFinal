package rmi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class GameServer extends UnicastRemoteObject implements GameClientInterface{
	private static final long serialVersionUID = 1L;
	
	private int port;
	private ArrayList<GameClientInterface> clients;
	private ArrayList<ClientCommand> currentMoves;
	
	protected static Registry myRegistry;
	
	protected String myIP = "127.0.0.1";
	
	protected String name, otherIP, otherName, recentClientName;
	
	public GameServer() throws RemoteException {
		super();
	}	
	
	public GameServer(String serverName, int port, String ip) throws RemoteException{
		name = serverName;
		this.port = port;
		this.myIP = ip;
	}
	
	public String[] getData(String[] args) {
		if(args.length >= 3)
			return new String[] {args[0] + args[1], args[1] + args[2]};
		else
			return new String[] {args[0] + args[1], "No position 2"};
	}
	
	/**
	 * @return the list of commands for the one that did not fire commandList
	 */
	public void receiveRecentCommands(String clientName, ArrayList<ClientCommand> commandList) throws RemoteException {
		currentMoves = commandList;
		recentClientName = clientName;
	}
	
	public String getRecentClientName() {
		return recentClientName;
	}
	
	public ArrayList<ClientCommand> getCommands() {
		return currentMoves;
	}
	
	public static void main(String[] args) {
		String IP;
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter the IP of the system to connect to: ");
		IP = s.nextLine();
		GameServer gs = null;
		
		try {
			gs = new GameServer("server", 1099, java.net.InetAddress.getLocalHost().getHostAddress());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		gs.createMyRegistry(1099);
	}
	
	public String getName(String mod){return null;}
	
	public void createMyRegistry(int port) {
		try {
			try {
				myRegistry = LocateRegistry.getRegistry(myIP, port);
				System.out.println("Registry present, connected.");
				myRegistry.rebind(name, this);
				System.out.println(name + " has started.");
			} catch(Exception e) {
				myRegistry = LocateRegistry.createRegistry(port);
				System.out.println("Registry created, connected.");
				myRegistry.rebind(name, this);
				System.out.println(name + " has started.");
				return;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void connect(GameClientInterface l) throws RemoteException {
		clients.add(l);
	}
	/*
	private void fireActionPerformed(ActionEvent e) {
		for(ActionListener l : clients) {
			l.actionPerformed(e);
		}
	}
	public void actionPerformed(ActionEvent e) {}*/
}
