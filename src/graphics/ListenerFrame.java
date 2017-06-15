package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class ListenerFrame extends JFrame implements KeyListener, ActionListener
{
	public ListenerFrame(String name)
	{
		super(name);
		
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
			char c = e.getKeyChar();
			switch(c)
			{
				case 'm':
					//move
				case 'a':
					//attack
				case 'p':
					//play mode (Techniques & gear)
				case 's':
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
