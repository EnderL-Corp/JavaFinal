package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import cards.Card;

/**
 * An RMI Server that allows for a connection between multiple GameClients.
 * @author Srihari Subramanian
 *
 */
public class GameServer extends UnicastRemoteObject implements GameServerInterface{
	private static final long serialVersionUID = 1L;
	
	private int port, numConnections = 0;
	private ArrayList<GameClient> clients = new ArrayList<GameClient>();
	private ArrayList<Card> recentChanges;
	
	protected static Registry myRegistry;
	
	protected String myIP = "127.0.0.1";
	
	protected String name = "", otherIP, otherName, recentClientName = "";
	
	public GameServer() throws RemoteException {
		super();
	}	
	
	/**
	 * Common constructor for any GameServer.
	 * @param serverName The name of this server
	 * @param port The port that this GameServer is running at
	 * @param ip The ip of the computer at which this server is running.
	 * @throws RemoteException
	 */
	public GameServer(String serverName, int port, String ip) throws RemoteException {	//TODO serverName must be deleted.
		name = "Server @" + ip;
		this.port = port;
		this.myIP = ip;
	}
	
	public String[] getData(String[] args) {
		if(args.length >= 3)
			return new String[] {args[0] + args[1], args[1] + args[2]};
		else
			return new String[] {args[0] + args[1], "No position 2"};
	}
	
	public String getRecentClientName() {
		return recentClientName;
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
		
		gs.createMyRegistry();
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Will create or use the registry at which the GameServer is running.
	 */
	public void createMyRegistry() {
		try {
			myRegistry = LocateRegistry.getRegistry(myIP, port);
			System.out.println("Registry present, connected.");
			myRegistry.rebind(name, this);
			System.out.println(name + " has started.");
		} catch(Exception e) {
			e.printStackTrace();
			try {
				myRegistry = LocateRegistry.createRegistry(port);
				System.out.println("Registry created, connected.");
				myRegistry.rebind(name, this);
				System.out.println(name + " has started.");
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			return;				
		}
	}
	
	public void connect(GameClient l) throws RemoteException {
		clients.add(l);
		numConnections++;
	}
	/*
	private void fireActionPerformed(ActionEvent e) {
		for(ActionListener l : clients) {
			l.actionPerformed(e);
		}
	}
	public void actionPerformed(ActionEvent e) {}*/

	@Override
	public void receiveRecentCardChanges(String clientName, ArrayList<Card> cardList) throws RemoteException {
		recentChanges = cardList;
	}

	@Override
	public ArrayList<Card> getRecentCardsList() throws RemoteException {
		return recentChanges;
	}

	@Override
	public ArrayList<GameClient> getGameClients() throws RemoteException {
		return clients;
	}
	
	public int getConnections() {
		return numConnections;
	}
}
