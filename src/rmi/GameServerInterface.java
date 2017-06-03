package rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import cards.Card;

public interface GameServerInterface extends Remote, Serializable{
	//void connect(GameClientInterface l) throws RemoteException;
	String[] getData(String[] args) throws RemoteException;
	void receiveRecentCommands(String clientName, ArrayList<ClientCommand> commandList) throws RemoteException;
	String getRecentClientName() throws RemoteException;
	ArrayList<ClientCommand> getCommands() throws RemoteException;
	String getName() throws RemoteException;
	void receiveRecentCardChanges(String clientName, ArrayList<Card> cardList) throws RemoteException;
}
