package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameServerInterface extends Remote {
	public String[] getData(String[] args) throws RemoteException;
	public ArrayList<ClientCommand> getCommmands(ArrayList<ClientCommand> commandList) throws RemoteException;
}
