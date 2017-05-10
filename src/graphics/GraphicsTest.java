package graphics;
import java.util.Scanner;  

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class GraphicsTest 
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		Scanner kb = new Scanner(System.in);
		
		frame.setSize(1000,1000);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
		BoardPanel panel = new BoardPanel();
		frame.add(panel);
		frame.setVisible(true);		
		
		boolean close = false;
		
		while(close == false)
		{

			System.out.println("Enter a command:");  
			String command = kb.next();
			
			switch(command)
			{
				case "CngComPos":
					System.out.println("Enter X cordinate:");  
					int x = kb.nextInt(); //obviously check preconditions
					System.out.println("Enter Y cordinate:");  
					int y = kb.nextInt(); //same for this one
					panel.changeCommanderPos(x,y);
					break;
					
				case "CngMapSize":
					System.out.println("Enter map Type"); 
					String type = kb.next(); //precondition check
					panel.changeMapSize(type);
					break;
					
				case "GetComX":
					System.out.println(panel.getCommanderX());
					break;
					
				case "GetComY":
					System.out.println(panel.getCommanderY());
					break;
					
				case "Help":
					System.out.println("To change commander position, type \"CngComPos\"");
					System.out.println("To change map size, type \"CngMapSize\"");
					System.out.println("To get commander coordinates, type \"GetComX\" and \"GetComY\" for their respective values");
					System.out.println("To end this loop, type \"Close\"");
					break;
					
				case "Close":
					close = true;
					break;
					
				default:
					System.out.println("Invalid command, type \"Help\" for list of valid commands");
					break;
			}
			
			frame.setVisible(true);
		}
	}
}
