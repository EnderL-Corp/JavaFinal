package rmi;

public class ClientCommand {
	private CommandEnum command;
	
	public ClientCommand(CommandEnum c) {
		command = c;
	}
	
	public CommandEnum getCommand() {
		return command;
	}
}
