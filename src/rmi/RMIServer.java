package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements GameServerInterface{
	private static String name;
	
	public RMIServer() throws RemoteException {
		
	}
	
	public RMIServer(String serverName) throws RemoteException{
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
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("server", new RMIServer());
			System.out.println("Server has started.");
		} catch(Exception e) {
			System.out.println("RMIServer.main(String[] args) : " + e);
		}
	}
	
	/*private static void unbind() {
		try {
			reg.unbind("server");
		}
		catch (Exception e) {
			System.out.println("this RMIServer.unbind() : " + e);
		}
	}*/
}
