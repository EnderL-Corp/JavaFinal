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
		System.out.println(this);
	}
	
	public CommandEnum getCommandEnum() {
		return command;
	}
	
	public String toString() {
		return command.name() + ", Commander!";
	}
}
