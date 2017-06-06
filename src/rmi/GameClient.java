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

public abstract class GameClient implements Serializable, ActionListener {
	
	private static final long serialVersionUID = -6238246696085178736L;

	protected boolean connected = false;
	
	private int tag = 2, serverPort = 1099;
	
	/**
	 * Please do not change the value of remoteServer or  
	 * set to null. It is protected for ease of access.
	 */
	protected GameServerInterface remoteServer;
	
	/**
	 * Please access through clientInfo.
	 */
	protected String name, serverIP, serverName;
	
	/**
	 * Please do not change the value of serverRegistry or  
	 * set to null. It is protected for ease of access.
	 */
	protected static Registry serverRegistry;
	
	protected Timer timer;
	protected ClientInfo myClient;
	
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
		myClient = new ClientInfo(tag, serverIP, serverPort, serverName);
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
	
	public boolean connectToServer() {
		try {
			System.out.println("Server: " + serverName);
			serverRegistry = LocateRegistry.getRegistry(serverIP, serverPort);
			System.out.println("Looking for " + serverName);
			remoteServer = (GameServerInterface) serverRegistry.lookup(serverName);
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
	
	public void sendOverNetwork(Card cardChanged) {
		ArrayList<Card> al = new ArrayList<Card>();
		al.add(cardChanged);
		
		try {
			remoteServer.receiveRecentCardChanges(name, al);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void connectClientInfo() {
		try {
			remoteServer.connect(myClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void test() {
		String a;
		try {
			a = remoteServer.getName();
			System.out.println(a);
			
			String[] t = remoteServer.getData(new String[]{"Test1", "Test2"});
			System.out.println(t[0] + t[1]);
			
			String[] b = remoteServer.getData(new String[]{"a1", "b2", "c3"});
			System.out.println(b[0] + b[1]);
			/*
			ArrayList<ClientInfo> gcs = remoteServer.getGameClients();
			if(gcs.get(0).getTag() == this.tag)
				System.out.println("Other client: " + gcs.get(0).getTag());
			else
				System.out.println("Other client: " + gcs.get(1).getTag());
			*/
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}	
	}
	
	public int getTag() {
		return tag;
	}
	
	public boolean isConnected() {
		return connected;
	}
	public ClientInfo getClientInfo() {
		return myClient;
	}
}
