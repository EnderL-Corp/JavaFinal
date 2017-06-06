package rmi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import cards.Card;

public class GameClientPeerToPeer extends UnicastRemoteObject implements Serializable, GameServerInterface, ActionListener {
	private static final long serialVersionUID = 2869608632508745852L;
	protected boolean connected = false;
	protected static Registry clientRegistry;
	protected static Registry myRegistry;
	protected int tag;
	protected String myIP = "127.0.0.1";
	protected int otherPort;
	
	private GameServerInterface remoteClient;
	
	protected String name, otherIP, otherName;
	private ArrayList<GameServerInterface> clients;
	//private ArrayList<ClientCommand> currentMoves;
	
	/*protected Thread thread;
	
	private boolean running;
	
	private void startThread() {
		if(running){
			return;
		}
		else {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void run() {
		while(running) {
			
		}
	}*/
	
	/**
	 * DO NOT USE
	 */
	public static void main(String[] args) {
		try {
			GameClientPeerToPeer client = new GameClientPeerToPeer(0, "127.0.0.1", 1099, null, null);
			client.connectToOther();
		} catch(Exception e) {
			e.printStackTrace();	
		}
	}
	
	public GameClientPeerToPeer() throws RemoteException {
		otherPort = 1099;
	}
	
	public GameClientPeerToPeer(int port, String refTag) throws RemoteException {
		otherPort = port;
		String clientIP;
		try {
			if(refTag == null)
				clientIP = java.net.InetAddress.getLocalHost().getHostAddress(); 
			else {
				clientIP = "127.0.0.1";
				name = "Client @" + clientIP + "," + refTag;
				return;
			}
		} catch (UnknownHostException e) {
			clientIP = null;
			e.printStackTrace();
			return;
		}
		name = "Client @" + clientIP;
	}
	
	/**
	 * Use this constructor for multiple computer connection
	 * @param tag Tag of this client
	 * @param otherIP IP of other client
	 * @param otherPort Port of other client
	 * @throws RemoteException
	 */
	public GameClientPeerToPeer(int tag, String otherIP, int otherPort) throws RemoteException {
		this.otherPort = otherPort;
		this.tag = tag;
		this.otherIP = otherIP;
	}
	
	/**
	 * 
	 * @param tag
	 * @param otherIP IP of other client
	 * @param port Port to connect to on other device
	 * @param refTag Only has to be filled out if testing is carried out on same device. Make note of the refTag when using it. 
	 * 			Null if multiple devices
	 * @throws RemoteException
	 */
	public GameClientPeerToPeer(int tag, String otherIP, int otherPort, String refTag, String otherRefTag) throws RemoteException{
		this(otherPort, refTag);
		this.tag = tag;
		this.otherIP = otherIP;
		if(otherRefTag != null) {
			otherName = "Client @" + otherIP + "," + otherRefTag;
		}
		else {
			otherName = "Client @" + otherIP;
		}
		System.out.println("Name:" + name + "   OtherName:" + otherName);
	}
	
	public boolean connectToOther() {
		try {
			System.out.println("Other: " + otherName);
			clientRegistry = LocateRegistry.getRegistry(otherIP, otherPort);
			System.out.println("Looking for " + otherName);
			remoteClient = (GameServerInterface) clientRegistry.lookup(otherName);
			System.out.println("Connected to peer.");
			connected = true;
			test();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return connected;
	}
	
	public void test() {
		String a;
		try {
			a = remoteClient.getName();
			System.out.println(a);
			
			String[] t = remoteClient.getData(new String[]{"Test1", "Test2"});
			System.out.println(t[0] + t[1]);
			
			String[] b = remoteClient.getData(new String[]{"a1", "b2", "c3"});
			System.out.println(b[0] + b[1]);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * REFER TO http://docs.oracle.com/javase/7/docs/technotes/guides/rmi/hello/hello-world.html
	 * 
	 * @param port the port that I want to bind MY registry to
	 */
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
		/*
		 try {
			GameServerInterface stub;
			try {
				myRegistry = LocateRegistry.getRegistry(myIP, port);
				System.out.println("Registry present, connected.");
				stub = (GameServerInterface)UnicastRemoteObject.exportObject(this, port);
				myRegistry.rebind(name, stub);
				System.out.println(name + " has started.");
			} catch(Exception e) {
				myRegistry = LocateRegistry.createRegistry(port);
				System.out.println("Registry created, connected.");
				stub = (GameServerInterface)UnicastRemoteObject.exportObject(this, port);
				myRegistry.rebind(name, stub);
				System.out.println(name + " has started.");
				return;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		 */
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public int getTag() {
		return tag;
	}
	public String getName() {
		return new String(name);
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
	/*public void receiveRecentCommands(String clientName, ArrayList<ClientCommand> commandList) {
		//currentMoves = commandList;
		System.out.println("In " + name + ":");
		for(int i = 0; i < commandList.size(); i++) {
			if(this instanceof Game) {
				commandList.get(i).performAction((Game)this);			//TODO return to this if doing p2p connection
			}
		}			
		
		//fireActionPerformed(new ActionEvent(gc, ActionEvent.ACTION_PERFORMED, null));
	}
*/	
	@Override
	public String getRecentClientName() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void receiveRecentCardChanges(String clientName, ArrayList<Card> cardList) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Card> getRecentCardsList() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connect(ClientInfo gc) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ClientInfo> getGameClients() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getConnections() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
}
