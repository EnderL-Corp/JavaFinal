package rmi;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import cards.Card;
import main.ClientInfo;

import javax.swing.Timer;

/**
 * Abstract class representing any GameClient to a GameServer.
 * @author Srihari Subramanian
 */
public abstract class GameClient implements Serializable, ActionListener {
	private static final long serialVersionUID = -6238246696085178736L;
	protected boolean connected = false;
	private int tag = 2, serverPort = 1099;
	protected GameServerInterface remoteServer;
	private String name, serverIP, serverName;
	protected static Registry serverRegistry;
	protected Timer timer;
	protected ClientInfo clientInfo, otherClientInfo;

	/**
	 * Required no-args constructor for RMI.
	 * @throws RemoteException
	 */
	public GameClient() throws RemoteException {

	}

	/**
	 * Use this constructor for multiple computer connection
	 * 
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
		// myClient = new ClientInfo(tag, serverIP, serverPort, serverName);
	}

	/**
	 * Method used to connect this GameClient to a host GameServer.
	 * @return whether there was a successful connection or not.
	 */
	public boolean connectToServer(ClientInfo clientInfo) {
		try {
			System.out.println("Server: " + serverName);
			serverRegistry = LocateRegistry.getRegistry(serverIP, serverPort);
			System.out.println("Looking for " + serverName);
			remoteServer = (GameServerInterface) serverRegistry.lookup(serverName);
			remoteServer.connect(clientInfo);
			System.out.println("Connected to server.");
			connected = true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (connected) {
			timer = new Timer(1000, this);
			timer.start();
		}
		return connected;
	}

	/**
	 * Get the tag of this client.
	 * @return tag of this client
	 */
	public int getTag() {
		return tag;
	}

	/**
	 * Get connectivity status
	 * @return connectivity
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * @return the name of this client
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the IP of this client
	 */
	public String getIP() {
		return serverIP;
	}
	
	/**
	 * Disconnects from the server.
	 */
	public void disconnect() {
		try {
			remoteServer.disconnect(clientInfo);
			timer.stop();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
