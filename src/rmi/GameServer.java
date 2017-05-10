package rmi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import main.GameClient;

public class GameServer extends UnicastRemoteObject implements GameServerInterface{
	private static String name;
	private List<ActionListener> clients;
	
	public GameServer() throws RemoteException {
		
	}
	
	public GameServer(String serverName) throws RemoteException{
		name = serverName;
	}
	
	public String[] getData(String[] args) {
		if(args.length >= 3)
			return new String[] {args[0] + args[1], args[1] + args[2]};
		else
			return new String[] {args[0] + args[1], "No position 2"};
	}
	
	public ArrayList<ClientCommand> getCommmands(GameClient gc, ArrayList<ClientCommand> commandList) throws RemoteException {
		ActionEvent e = new ActionEvent(gc, ActionEvent.ACTION_PERFORMED, null);
		return commandList;
	}
	
	public static void main(String[] args) {
		try {
			Registry reg;
			/*try {
				reg = LocateRegistry.getRegistry(1099);
			} catch(Exception e) {*/
				reg = LocateRegistry.createRegistry(1099);
				System.out.println("GameServer.main(String[] args) : Nothing currently running at port, registry created. \n"/* + e*/);
			//}
			reg.rebind("server", new GameServer());
			System.out.println("Server has started.");
		} catch(Exception e) {
			System.out.println("GameServer.main(String[] args) : " + e);
		}
	}
	
	public void connect(ActionListener l) {
		clients.add(l);
	}
}
