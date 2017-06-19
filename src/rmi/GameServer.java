package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import cards.Card;
import cards.Entity;
import main.ClientInfo;

/**
 * An RMI Server that allows for a connection between multiple GameClients.
 * @author Srihari Subramanian
 *
 */
public class GameServer extends UnicastRemoteObject implements GameServerInterface {
	private static final long serialVersionUID = 1L;
	private int port;
	private ArrayList<ClientInfo> clients = new ArrayList<ClientInfo>();
	protected static Registry myRegistry;
	protected String myIP = "127.0.0.1";
	protected String name = "", otherIP, otherName, recentClientName = "";
	private Entity[][] board = new Entity[15][15];
	private int turnTag = 0, recentClientTag;
	private ClientInfo winner = null;
	private String recentClientActionDescription = "";

	/**
	 * Required no-args constructor for RMI.
	 * @throws RemoteException
	 */
	public GameServer() throws RemoteException {
		super();
	}

	/**
	 * Common constructor for any GameServer.
	 * 
	 * @param serverName The name of this server
	 * @param port The port that this GameServer is running at
	 * @param ip The ip of the computer at which this server is running.
	 * @throws RemoteException
	 */
	public GameServer(String ip, int port) throws RemoteException {
		name = "Server @" + ip;
		this.port = port;
		this.myIP = ip;
	}

	public static void main(String[] args) {
		String IP;
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter the IP of the system to connect to: ");
		IP = s.nextLine();
		GameServer gs = null;

		try {
			gs = new GameServer(java.net.InetAddress.getLocalHost().getHostAddress(), 1099);
		} catch (Exception e) {
			e.printStackTrace();
		}

		gs.createMyRegistry();
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
		} catch (Exception e) {
			e.printStackTrace();
			try {
				myRegistry = LocateRegistry.createRegistry(port);
				System.out.println("Registry created, connected.");
				myRegistry.rebind(name, this);
				System.out.println(name + " has started.");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return;
		}
	}

	/*
	 * The following are RMI methods that are documented in GameServerInterface
	 */

	public synchronized void connect(ClientInfo l) throws RemoteException {
		clients.add(l);
	}

	public synchronized int getConnections() {
		return clients.size();
	}

	public synchronized Entity[][] getBoard() throws RemoteException {
		return board;
	}

	public synchronized boolean updateBoard(ClientInfo gc, Entity[][] updated) throws RemoteException {
		if (gc.getTag() == turnTag) {
			board = updated;
			return true;
		}
		return false;
	}

	public synchronized void endMyTurn() throws RemoteException {
		if (clients.get(0).getTag() == turnTag)
			turnTag = clients.get(1).getTag();
		else
			turnTag = clients.get(0).getTag();
	}

	public synchronized int getTurnTag() throws RemoteException {
		return turnTag;
	}

	public synchronized void gameOver(ClientInfo loser) throws RemoteException {
		if (clients.get(0).getTag() == loser.getTag())
			winner = clients.get(1);
		else
			winner = clients.get(0);
	}

	public synchronized ClientInfo getWinner() throws RemoteException {
		return winner;
	}

	public void updateInfo(ClientInfo newInfo) throws RemoteException {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getTag() == newInfo.getTag()) {
				clients.set(i, newInfo);
			}
		}
	}

	public synchronized ClientInfo getOtherClient(ClientInfo thisClient) throws RemoteException {
		if(clients.size() > 1 && clients.get(0).getTag() == thisClient.getTag())
			return clients.get(1);
		else
			return clients.get(0);
	}

	public void setRecentClientActionDescription(String s) throws RemoteException {
		recentClientTag = turnTag;
		recentClientActionDescription = s;
	}

	public String getRecentClientActionDescription() throws RemoteException {
		return recentClientActionDescription;
	}
	
	public void disconnect(ClientInfo ci) throws RemoteException {
		for(int i = 0; i < clients.size(); i++)
			if(clients.get(i).getTag() == ci.getTag())
				clients.remove(i);
		
	}
	
	public int getRecentClientTag() throws RemoteException {
		return recentClientTag;
	}
}
