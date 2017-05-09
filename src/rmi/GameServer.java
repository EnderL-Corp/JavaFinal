package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class GameServer extends UnicastRemoteObject implements GameServerInterface{
	private static String name;
	
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
	
	/*private static void unbind() {
		try {
			reg.unbind("server");
		}
		catch (Exception e) {
			System.out.println("this GameServer.unbind() : " + e);
		}
	}*/
}
