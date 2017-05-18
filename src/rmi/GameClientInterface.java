package rmi;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameClientInterface extends Remote, Serializable{
	//void connect(GameClientInterface l) throws RemoteException;
	String[] getData(String[] args) throws RemoteException;
	void receiveRecentCommands(GameClient gc, ArrayList<ClientCommand> commandList) throws RemoteException;
	ArrayList<ClientCommand> getCommands() throws RemoteException;
	String getName(String modifier) throws RemoteException;
}
