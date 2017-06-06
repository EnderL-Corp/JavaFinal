package rmi;

import java.io.Serializable;

import cards.Card;
import main.Game;

public class ClientCommand implements Serializable{
	private static final long serialVersionUID = -625879757800036729L;
	private CommandEnum command;
	private Card cardToChange;
	
	public ClientCommand(CommandEnum c, Card card) {
		command = c;
		cardToChange = card;
	}
	
	public void performAction(Game game) {
		//Basically a switch case for every enum type
		System.out.println(this + " for Commander.");
		switch(command) {
		case MOVE_DOWN:
			game.moveCommDown();
			break;
		case MOVE_UP:
			game.moveCommUp();
			break;
		case MOVE_LEFT:
			game.moveCommLeft();
			break;
		case MOVE_RIGHT:
			game.moveCommRight();
			break;
		}
	}
	
	public CommandEnum getCommandEnum() {
		return command;
	}
	
	public String toString() {
		return command.name() + ", Commander!";
	}
}
