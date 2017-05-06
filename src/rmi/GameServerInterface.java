package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameServerInterface extends Remote {
	public String[] getData(String[] args) throws RemoteException;
}
