package rmi;

import java.io.Serializable;

public class ClientCommand implements Serializable{
	private CommandEnum command;
	
	public ClientCommand(CommandEnum c) {
		command = c;
	}
	
	public CommandEnum getCommand() {
		return command;
	}
	
	public void performAction() {
		//Basically a switch case for every enum type
	}
}
