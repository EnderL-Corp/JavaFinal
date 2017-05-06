package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI extends Remote {
	public String[] getData(String[] args) throws RemoteException;
}
