package graphics;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class GraphicsTest 
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		
		frame.setSize(1000,1000);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
		BoardPanel panel = new BoardPanel();
		frame.add(panel);
		frame.setVisible(true);		
		
		//add actionListener
		/*
		panel.changeCommanderPos(5, 2);
		panel.changeMapSize("medium");
		frame.setVisible(true);
		*/
	}
}
