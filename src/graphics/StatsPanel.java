package graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import main.Game;

public class StatsPanel extends JPanel {
	JTextPane stats;

	public StatsPanel() {
		// add a phat layout
		stats = new JTextPane();
		stats.setEditable(false);
		
		stats.setText("Your HP :" + 26 + "\nFoe's HP :" + 0 + "\nCP :" + Game.game.getCP() + "\nTP :" + Game.game.getTP()
		+ "\nAP :" + Game.game.getAP());

		JLabel key1 = new JLabel("A : toggels attack");
		JLabel key2 = new JLabel("M : toggels move");
		JLabel key3 = new JLabel("P : toggels play");

		add(stats);

		add(key1);
		add(key2);
		add(key3);
	}

	public void statRefresh() {
		stats.setText("Your HP :" + 26 + "\nFoe's HP :" + 0 + "\nCP :" + Game.game.getCP() + "\nTP :" + Game.game.getTP()
				+ "\nAP :" + Game.game.getAP());
	}

}
