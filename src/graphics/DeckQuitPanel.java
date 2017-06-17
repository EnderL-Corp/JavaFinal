package graphics;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Game;

public class DeckQuitPanel extends JPanel implements ActionListener {
	JButton quit;
	JLabel deckSize;

	private DeckQuitPanel() {
		setLayout(new FlowLayout());

		deckSize = new JLabel(Game.game.getDeck().size() + " / 26");
		add(deckSize);

		quit = new JButton("Quit Game");
		quit.setActionCommand("quit");
		quit.addActionListener(this);
		add(quit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("am quiting");
		System.exit(0);
	}

	public void deckRefresh() {
		deckSize.setText(Game.game.getDeck().size() + " / 26");
		deckSize.setVisible(false);
		deckSize.setVisible(true);
	}

}
