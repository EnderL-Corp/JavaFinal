package rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import cards.Card;

public interface GameServerInterface extends Remote, Serializable{
	String getRecentClientName() throws RemoteException;
	void receiveRecentCardChange(String clientName, Card card) throws RemoteException;
	void connect(GameClient gc) throws RemoteException;
	Card getRecentCard() throws RemoteException;
	ArrayList<GameClient> getGameClients() throws RemoteException;
	int getConnections() throws RemoteException;
}
