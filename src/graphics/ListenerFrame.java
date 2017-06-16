package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class ListenerFrame extends JFrame
{
	public ListenerFrame()
	{
		KeyListener listener = new KBListener();
		addKeyListener(listener);
		setFocusable(true);
	}

	public void keyTyped(KeyEvent e) 
	{
		changeAction(e);
		//Handle key Presses
	}
	
	private void changeAction(KeyEvent e)
	{
		
		int keyID = e.getID();
		if(keyID == KeyEvent.KEY_TYPED)
		{
			System.out.println("Got Here");
			char c = e.getKeyChar();
			switch(c)
			{
				case 'm':
					System.out.println("Move");
					//move
				case 'a':
					System.out.println("Attack");
					//attack
				case 'p':
					System.out.println("Play");
					//play mode (Techniques & gear)
				case 's':
					System.out.println("Structure");
					//structure mode
			}
		}
	}
	
	public void keyPressed(KeyEvent e) 
	{
		//Don't Touch
	}

	public void keyReleased(KeyEvent e) 
	{
		//Don't Touch
	}
	

	public void actionPerformed(ActionEvent e) 
	{
		
	}
}
