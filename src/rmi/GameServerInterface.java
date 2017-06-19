package rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import cards.Card;
import cards.Entity;
import main.ClientInfo;

/**
 * Interface so that the client can "interface" with the running server.
 * @author Srihari Subramanian
 *
 */
public interface GameServerInterface extends Remote, Serializable{
	
	/**
	 * method to add this GameClient to the list of the server's known clients
	 * @param gc the GameClient to be connected
	 * @throws RemoteException
	 */
	void connect(ClientInfo gc) throws RemoteException;
	
	/**
	 * Get the number of connections.
	 * @return the number of connections.
	 * @throws RemoteException
	 */
	int getConnections() throws RemoteException;
	
	/**
	 * Method to get the current board
	 * @return the current version of the board of entities
	 * @throws RemoteException
	 */
	Entity[][] getBoard() throws RemoteException;
	
	/**
	 * Method to update the server board.
	 * @param gc the invoking GameClient
	 * @param updated updated 2d array of entities
	 * @return whether the value has been successfully updated or not.
	 * 			will not successfully update if it is not your GameClient's turn.
	 * @throws RemoteException
	 */
	boolean updateBoard(ClientInfo gc, Entity[][] updated) throws RemoteException;
	
	/**
	 * Ends the invoking client's turn.
	 * @throws RemoteException
	 */
	void endMyTurn() throws RemoteException;
	
	/**
	 * 
	 * @return The tag of the client whose turn it currently is.
	 * @throws RemoteException
	 */
	int getTurnTag() throws RemoteException;
	
	/**
	 * Get the winner of the game.
	 * @return The winner of the game
	 * @throws RemoteException
	 */
	ClientInfo getWinner() throws RemoteException;
	
	/**
	 * Will trigger the game to finish by setting a variable to true.
	 * @param gc the ClientInfo of the losing client
	 * @throws RemoteException
	 */
	void gameOver(ClientInfo gc) throws RemoteException;
	
	/**
	 * Will update the info of the newInfo client.
	 * @param newInfo the updated version of the clientInfo
	 * @throws RemoteException
	 */
	void updateInfo(ClientInfo newInfo) throws RemoteException;
	
	/**
	 * Will return the information of the other client.
	 * @param thisClient the client that wants the information
	 * @return the ClientInfo of the other client 
	 * @throws RemoteException
	 */
	ClientInfo getOtherClient(ClientInfo thisClient) throws RemoteException;
	
	/**
	 * Will update the recent moves of a client whose turn it is in the server.
	 * @param s the description to save
	 */
	void setRecentClientActionDescription(String s) throws RemoteException;
	
	/**
	 * Gets the recent description of change that the other client made to board.
	 * @return the summary of the change
	 */
	String getRecentClientActionDescription() throws RemoteException;
	
	/**
	 * Disconnects client from server.
	 * @param ci the Client represented by ClientInfo to disconnect
	 * @throws RemoteException
	 */
	void disconnect(ClientInfo ci) throws RemoteException;
	
	int getRecentClientTag() throws RemoteException;
}
