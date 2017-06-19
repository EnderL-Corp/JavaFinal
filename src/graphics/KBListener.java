package graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;

/**
 * 
 * @author Luke Letourneau
 *
 */
public class KBListener implements KeyListener {
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		Game.game.setCurrentPlayerAction(KeyEvent.getKeyText(e.getKeyCode()).toCharArray()[0]);
		Game.game.checkPlayerActionQueue();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
