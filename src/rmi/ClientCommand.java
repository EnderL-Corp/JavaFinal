package rmi;

import java.io.Serializable;

import main.Game;

public class ClientCommand implements Serializable{
	private CommandEnum command;
	
	public ClientCommand(CommandEnum c) {
		command = c;
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
