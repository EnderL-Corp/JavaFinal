package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import rmi.ClientCommand;
import rmi.GameClientInterface;

public class GameClient extends UnicastRemoteObject implements ActionListener, Serializable, GameClientInterface {//, Runnable {
	private static final long serialVersionUID = 1L;
	
	protected boolean connected = false;
	protected Registry clientRegistry;
	protected int tag;
	protected String myIP = "127.0.0.1";
	protected int otherPort;
	
	private String name, otherIP, otherName;
	private ArrayList<ActionListener> clients;
	private ArrayList<ClientCommand> currentMoves;
	
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
			GameClient client = new GameClient(0, "127.0.0.1", 1099, null, null);
			client.connectToOther();
		} catch(Exception e) {
			e.printStackTrace();	
		}
	}
	
	public GameClient() throws RemoteException {
		otherPort = 1099;
	}
	
	public GameClient(int port, String refTag) throws RemoteException {
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
	 * 
	 * @param tag
	 * @param otherIP IP of other client
	 * @param port Port to connect to on other device
	 * @param refTag Only has to be filled out if testing is carried out on same device. Make note of the refTag when using it. 
	 * 			Null if multiple devices
	 * @throws RemoteException
	 */
	public GameClient(int tag, String otherIP, int otherPort, String refTag, String otherRefTag) throws RemoteException{
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
		int line = 0;
		try {
			line++;
			clientRegistry = LocateRegistry.getRegistry(otherIP, otherPort);
			line++;
			GameClientInterface remoteClient = (GameClientInterface) clientRegistry.lookup(otherName);
			line++;
			System.out.println(otherName);
			remoteClient.connect(this);
			line++;
			System.out.println("Connected to server.");
			line++;
			connected = true;
			line++;
			String[] text = remoteClient.getData(new String[]{"Hello ", "my name is ", "Srihari"});
			String text2 = text[0] + text[1];
			System.out.println(text2);
		} catch(Exception e) {
			System.out.println("connectToServer() " + line+ ":");
			e.printStackTrace();
		}
		return connected;
	}
	
	/**
	 * 
	 * @param port the port that I want to bind MY registry to
	 */
	public void createMyRegistry(int port) {
		try {
			Registry reg;
			try {
				reg = LocateRegistry.createRegistry(1099);
				System.out.println("Server has started properly.");
			} catch(Exception e) {
				reg = LocateRegistry.getRegistry(myIP, port);
				System.out.println("GameServer.main(String[] args) : Nothing currently running at port, registry created.");
				return;
			}
			reg.rebind(name, this);
			System.out.println("Server has started.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public int getTag() {
		return tag;
	}
	public String getName() {
		return name;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof GameClient && (((GameClient)e.getSource()).getTag() != tag)) {
			try {
				for(ClientCommand c : ((GameClientInterface) clientRegistry).getCommands()) {
					c.performAction();
				}
			} catch (RemoteException e1) {
				System.out.println("actionPerformed() : " + e);
			}
		}
	}
	
	public String[] getData(String[] args) {
		if(args.length >= 3)
			return new String[] {args[0] + args[1], args[1] + args[2]};
		else
			return new String[] {args[0] + args[1], "No position 2"};
	}
	
	public void connect(GameClient l) throws RemoteException {
		clients.add((ActionListener)l);
	}
	
	/**
	 * @return the list of commands for the one that did not fire commandList
	 */
	public void postCommands(GameClient gc, ArrayList<ClientCommand> commandList) throws RemoteException {
		currentMoves = commandList;
		fireActionPerformed(new ActionEvent(gc, ActionEvent.ACTION_PERFORMED, null));
	}
	
	public ArrayList<ClientCommand> getCommands() {
		return currentMoves;
	}
	
	private void fireActionPerformed(ActionEvent e) {
		for(ActionListener l : clients) {
			l.actionPerformed(e);
		}
	}
}
