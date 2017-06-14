package rmi;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import cards.Card;
import javax.swing.Timer;

/**
 * Abstract class representing any GameClient to a GameServer.
 * @author Srihari Subramanian
 *
 */
public abstract class GameClient implements Serializable, ActionListener {
	
	private static final long serialVersionUID = -6238246696085178736L;

	protected boolean connected = false;
	
	private int tag = 2, serverPort = 1099;
	
	/**
	 * Please do not change the value of remoteServer or  
	 * set to null. It is protected for ease of access.
	 */
	protected GameServerInterface remoteServer;
	
	private String name, serverIP, serverName;
	
	/**
	 * Please do not change the value of serverRegistry or  
	 * set to null. It is protected for ease of access.
	 */
	protected static Registry serverRegistry;
	
	protected Timer timer;
	//protected ClientInfo myClient;
	
	public GameClient() throws RemoteException {
		
	}
	
	/**
	 * Constructor for testing purposes.
	 * @param refTag reference for this client
	 * @throws RemoteException
	 */
	public GameClient(String refTag) throws RemoteException {
		try {
			if(refTag == null)
				serverIP = java.net.InetAddress.getLocalHost().getHostAddress(); 
			else {
				serverIP = "127.0.0.1";
				name = "Client @" + serverIP + "," + refTag;
				return;
			}
		} catch (UnknownHostException e) {
			serverIP = null;
			e.printStackTrace();
			return;
		}
		name = "Client @" + serverIP;
	}
	
	/**
	 * Use this constructor for multiple computer connection
	 * @param tag Tag of this client
	 * @param serverIP IP of other client
	 * @param otherPort Port of other client
	 * @throws RemoteException
	 */
	public GameClient(int tag, String serverIP, int serverPort) throws RemoteException {
		this.serverPort = serverPort;
		this.tag = tag;
		this.serverIP = serverIP;
		serverName = "Server @" + serverIP;
		//myClient = new ClientInfo(tag, serverIP, serverPort, serverName);
	}
	
	/**
	 * Constructor for testing purposes
	 * @param tag
	 * @param serverIP IP of other client
	 * @param port Port to connect to on other device
	 * @param refTag Only has to be filled out if testing is carried out on same device. Make note of the refTag when using it. 
	 * 			Null if multiple devices
	 * @throws RemoteException
	 */
	public GameClient(int tag, String serverIP, String refTag) throws RemoteException{
		this(refTag);
		this.tag = tag;
		this.serverIP = serverIP;
		/*if(refTag != null) {
			serverName = "Server @" + serverIP + "," + refTag;
		}
		else {*/
			serverName = "Server @" + serverIP;
		//}
		System.out.println("Name:" + name + "   ServerName:" + serverName);
	}
	
	/**
	 * Method used to connect this GameClient to a host GameServer.  
	 * @return whether there was a successful connection or not.
	 */
	public boolean connectToServer() {
		try {
			System.out.println("Server: " + serverName);
			serverRegistry = LocateRegistry.getRegistry(serverIP, serverPort);
			System.out.println("Looking for " + serverName);
			remoteServer = (GameServerInterface) serverRegistry.lookup(serverName);
			remoteServer.connect(this);
			System.out.println("Connected to server.");
			connected = true;
			test();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}

		if(connected) {
			timer = new Timer(1000, this);
			timer.start();
		}
		return connected;
	}
	
	/**
	 * Will provide the GameServer with the card that is changed.
	 * @param cardChanged The card to send over the network
	 */
	public void sendOverNetwork(Card cardChanged) {
		try {
			remoteServer.receiveRecentCardChange(name, cardChanged);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Temporary method used to test the connection, as the name suggests.
	 */
	public void test() {
		String a;
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public int getTag() {
		return tag;
	}
	
	public boolean isConnected() {
		return connected;
	}
	/*
	public ClientInfo getClientInfo() {
		return myClient;
	}
	*/
	public String getName() {
		return name;
	}
	public String getIP() {
		return serverIP;
	}
}
