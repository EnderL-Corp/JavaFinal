package graphics;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Audio;
import main.Game;

/**
 * 
 * @author Santiago Callegari
 *
 */
public class DeckQuitPanel extends JPanel implements ActionListener {
	JButton quit;
	JLabel deckSize;

	public DeckQuitPanel() {

		setLayout(new FlowLayout());

		deckSize = new JLabel("Deck size : " + Game.game.getDeck().size() + " / 24");
		add(deckSize);

		quit = new JButton("Quit Game");
		quit.setActionCommand("quit");
		quit.addActionListener(this);
		add(quit);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Game.game.getGameMainMenu().getFrame().setVisible(true);
		Game.game.disconnect();
		new Audio("MainMenu");
		Game.game.getGameMenu().getFrame().setVisible(false);
		Game.game.getGameMenu().getFrame().dispose();
	}

	/**
	 * this is used to make the current card count accurate
	 */
	public void deckRefresh() {
		deckSize.setText("Deck size : " + Game.game.getDeck().size() + " / 24");
		deckSize.setVisible(false);
		deckSize.setVisible(true);
	}

}