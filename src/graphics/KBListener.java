package graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;

/**
 * 
 * @author Luke Letourneau
 *
 */
public class KBListener implements KeyListener 
{
	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
		Game.game.setCurrentPlayerAction(KeyEvent.getKeyText(e.getKeyCode()).toCharArray()[0]);
		System.out.println("keyPressed="+Game.game.getCurrentPlayerAction());
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
	}
}
