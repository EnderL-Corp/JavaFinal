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

		stats.setText("Your HP :" + Game.game.getCommander().getHealth() + "\nFoe's HP :"
				+ "none" + "\nCP :"
				+ Game.game.getCP() + "\nTP :" + Game.game.getTP() + "\nAP :" + Game.game.getAP());

		JLabel key1 = new JLabel("A : Toggles attack mode");
		JLabel key2 = new JLabel("M : Toggles move mode");
		JLabel key3 = new JLabel("P : Toggles play mode for troops");
		JLabel key4 = new JLabel("G : Toggles play mode for gear");
		JLabel key5 = new JLabel("T : Toggles play mode for techniques");
		JLabel key6 = new JLabel("S : Toggles play mode for structures");

		add(stats);

		add(key1);
		add(key2);
		add(key3);
		add(key4);
		add(key5);
		add(key6);
	}

	public void statRefresh() {
		stats.setText("Your HP :" + Game.game.getCommander().getHealth() + "\nFoe's HP :"
				+ Game.game.getOtherClient().getCommander().getHealth() + "\nCP :"
				+ Game.game.getCP() + "\nTP :" + Game.game.getTP() + "\nAP :" + Game.game.getAP());
		repaint();
	}

}
