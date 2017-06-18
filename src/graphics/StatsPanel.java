package graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import main.Game;

/**
 * 
 * @author Santiago Callegari
 *
 */
public class StatsPanel extends JPanel {
	JTextPane stats;

	public StatsPanel() {
		/**
		 * stats pane changes key pane will never change, it only shows which
		 * keystrokes do what
		 */
		stats = new JTextPane();
		stats.setEditable(false);
		stats.setText("Your HP :" + Game.game.getCommander().getHealth() + "\nFoe's HP :" + "no" + "\nCP :"
				+ Game.game.getCP() + "\nTP :" + Game.game.getTP() + "\nAP :" + Game.game.getAP());

		JTextPane key = new JTextPane();
		key.setEditable(false);
		key.setText("A : Toggles attack mode" + "\nM : Toggles move mode" + "\nP : Toggles play mode for troops"
				+ "\nG : Toggles play mode for gear" + "\nT : Toggles play mode for techniques"
				+ "\nS : Toggles play mode for amplifiers" + " \nE : Cancel command and \nclear selection");

		add(stats);
		add(key);
	}

	public void statRefresh() {
		stats.setText("Your HP :" + Game.game.getCommander().getHealth() + "\nFoe's HP :"
				+ Game.game.getOtherClient().getCommander().getHealth() + "\nCP :" + Game.game.getCP() + "\nTP :"
				+ Game.game.getTP() + "\nAP :" + Game.game.getAP());
		repaint();
	}

}