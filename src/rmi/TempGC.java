package rmi;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.Game;

public class TempGC /*extends UnicastRemoteObject */implements Serializable {
	//, Remote {//, Runnable {
	
	//TODO implement ActionListener for when the new set of commands is received.
	
	private static final long serialVersionUID = 1L;
	
	protected boolean connected = false;
	
	private int tag = 2, serverPort = 1099;
	
	private GameClientInterface remoteServer;
	
	protected String name, serverIP, otherName;
	private ArrayList<GameClientInterface> clients;
	private ArrayList<ClientCommand> currentMoves;
	protected static Registry clientRegistry;
	
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
			TempGC client = new TempGC(0, "127.0.0.1", 1099, null, null);
			client.connectToServer();
		} catch(Exception e) {
			e.printStackTrace();	
		}
	}
	
	public TempGC() throws RemoteException {
		
	}
	
	public TempGC(int port, String refTag) throws RemoteException {
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
	public TempGC(int tag, String serverIP, int serverPort) throws RemoteException {
		this.serverPort = serverPort;
		this.tag = tag;
		this.serverIP = serverIP;
	}
	
	/**
	 * 
	 * @param tag
	 * @param serverIP IP of other client
	 * @param port Port to connect to on other device
	 * @param refTag Only has to be filled out if testing is carried out on same device. Make note of the refTag when using it. 
	 * 			Null if multiple devices
	 * @throws RemoteException
	 */
	public TempGC(int tag, String serverIP, int otherPort, String refTag, String otherRefTag) throws RemoteException{
		this(otherPort, refTag);
		this.tag = tag;
		this.serverIP = serverIP;
		if(otherRefTag != null) {
			otherName = "Client @" + serverIP + "," + otherRefTag;
		}
		else {
			otherName = "Client @" + serverIP;
		}
		System.out.println("Name:" + name + "   OtherName:" + otherName);
	}
	
	public boolean connectToServer() {
		try {
			System.out.println("Other: " + otherName);
			clientRegistry = LocateRegistry.getRegistry(serverIP, serverPort);
			System.out.println("Looking for " + otherName);
			remoteServer = (GameClientInterface) clientRegistry.lookup(otherName);
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
			a = remoteServer.getName(new String("5"));
			System.out.println(a);
			
			String[] t = remoteServer.getData(new String[]{"Test1", "Test2"});
			System.out.println(t[0] + t[1]);
			
			String[] b = remoteServer.getData(new String[]{"a1", "b2", "c3"});
			System.out.println(b[0] + b[1]);
			
			
			ArrayList<ClientCommand> cc = new ArrayList<ClientCommand>();
			/*cc.add(new ClientCommand(CommandEnum.MOVE_DOWN));
			cc.add(new ClientCommand(CommandEnum.MOVE_UP));
			cc.add(new ClientCommand(e));*/
			cc.add(new ClientCommand(CommandEnum.values()[tag]));
			
			remoteServer.receiveRecentCommands(cc);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}	
	}
	
	public boolean isConnected() {
		return connected;
	}
}
