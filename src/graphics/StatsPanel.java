package graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Game;

public class StatsPanel extends JPanel {
	JLabel yourHP;
	JLabel otherHP;
	JLabel cp;
	JLabel tp;
	JLabel ap;

	public StatsPanel() {
		// add a phat layout
		yourHP = new JLabel("Your HP :" + 26);
		otherHP = new JLabel("Foe's HP :" + 0);
		cp = new JLabel("CP :" + "Game.game.getCP()");
		tp = new JLabel("TP :" + "Game.game.getTP()");
		ap = new JLabel("AP :" + "Game.game.getAP()");

		JLabel key1 = new JLabel("A : toggels attack");
		JLabel key2 = new JLabel("M : toggels move");
		JLabel key3 = new JLabel("P : toggels play");

		add(yourHP);
		add(otherHP);
		add(cp);
		add(tp);
		add(ap);

		add(key1);
		add(key2);
		add(key3);
	}

	public void statRefresh() {
		yourHP.setText("Your HP :" + 26);
		otherHP.setText("Foe's HP :" + 0);
		cp.setText("CP :" + "Game.game.getCP()");
		tp.setText("TP :" + "Game.game.getTP()");
		ap.setText("AP :" + "Game.game.getAP()");
	}

}
