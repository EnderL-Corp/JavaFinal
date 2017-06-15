package rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import cards.Card;
import cards.Entity;

public interface GameServerInterface extends Remote, Serializable{
	String getRecentClientName() throws RemoteException;
	void connect(GameClient gc) throws RemoteException;
	ArrayList<GameClient> getGameClients() throws RemoteException;
	int getConnections() throws RemoteException;
	
	Entity[][] getBoard() throws RemoteException;
	
	/**
	 * method to update the server board.
	 * @param gc the invoking GameClient
	 * @param updated updated 2d array of entities
	 * @return whether the value has been successfully updated or not.
	 * 			will not successfully update if it is not your GameClient's turn.
	 * @throws RemoteException
	 */
	boolean updateBoard(GameClient gc, Entity[][] updated) throws RemoteException;
	void endMyTurn() throws RemoteException;
	int getTurnTag() throws RemoteException;
}
