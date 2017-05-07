package main;

public class Game extends GameClient{
	public static void main(String[] args) {
		try{
			System.out.println("" + java.net.InetAddress.getLocalHost().getHostAddress());
		} catch(Exception e) {
			System.out.println(e);
		}
		//connectToServer();
	}
}
