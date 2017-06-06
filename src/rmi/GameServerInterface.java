package rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import cards.Card;

public interface GameServerInterface extends Remote, Serializable{
	String[] getData(String[] args) throws RemoteException;												//Tester method.
	String getRecentClientName() throws RemoteException;
	String getName() throws RemoteException;															//Tester method.
	void receiveRecentCardChanges(String clientName, ArrayList<Card> cardList) throws RemoteException;
	void connect(ClientInfo gc) throws RemoteException;
	ArrayList<Card> getRecentCardsList() throws RemoteException;
	ArrayList<ClientInfo> getGameClients() throws RemoteException;
	int getConnections() throws RemoteException;
}
