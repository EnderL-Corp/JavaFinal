package rmi;

public class ClientCommand {
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
