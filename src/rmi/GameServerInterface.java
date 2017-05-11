package rmi;

import java.awt.event.ActionListener;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.GameClient;

public interface GameServerInterface extends Remote {
	void connect(ActionListener l) throws RemoteException;
	String[] getData(String[] args) throws RemoteException;
	void postCommands(GameClient gc, ArrayList<ClientCommand> commandList) throws RemoteException;
	ArrayList<ClientCommand> getCommands() throws RemoteException;
}
