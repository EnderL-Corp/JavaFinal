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

import main.GameClient;

public class GameServer extends UnicastRemoteObject implements GameServerInterface, Serializable{
	private static String name, lhIP;
	private int port;
	private ArrayList<ActionListener> clients;
	private ArrayList<ClientCommand> currentMoves;
	
	public GameServer() throws RemoteException {
		
	}	
	
	public GameServer(String serverName, String lhIP, int port) throws RemoteException{
		name = serverName;
		this.port = port;
		this.lhIP = lhIP;
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
	public void postCommands(GameClient gc, ArrayList<ClientCommand> commandList) throws RemoteException {
		currentMoves = commandList;
		fireActionPerformed(new ActionEvent(gc, ActionEvent.ACTION_PERFORMED, null));
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
			gs = new GameServer("server", IP, 1099);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		gs.createRegistry();
	}
	
	public void createRegistry() {
		try {
			Registry reg;
			try {
				reg = LocateRegistry.getRegistry(1099);
				System.out.println("Server has started properly.");
			} catch(Exception e) {
				reg = LocateRegistry.createRegistry(/*lhIP,*/ 1099);
				System.out.println("GameServer.main(String[] args) : Nothing currently running at port, registry created.");
				e.printStackTrace();
			}
			reg.rebind(name, this);
			System.out.println("Server has started.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void connect(ActionListener l) throws RemoteException {
		clients.add(l);
	}
	
	private void fireActionPerformed(ActionEvent e) {
		for(ActionListener l : clients) {
			l.actionPerformed(e);
		}
	}
}
