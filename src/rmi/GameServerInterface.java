package rmi;

import java.awt.event.ActionListener;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.GameClient;

public interface GameServerInterface extends Remote {
	void connect(ActionListener l);
	String[] getData(String[] args) throws RemoteException;
	ArrayList<ClientCommand> getCommmands(GameClient gc, ArrayList<ClientCommand> commandList) throws RemoteException;
}
