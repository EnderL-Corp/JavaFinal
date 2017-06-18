package graphics;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import cards.Card;
import cards.Commander;
import cards.Entity;
import cards.Structure;
import cards.Technique;
import cards.Troop;
/**
 * 
 * @author Santiago Callegari
 *
 */
public class InfoPanel extends JPanel {
	private JTextPane stats;

	public InfoPanel() {
		stats = new JTextPane();
		stats.setEditable(false);
		add(stats);
	}
/**
 * 
 * this checks to see what card it takes in and then displays for people to see, descriptions were edited by me to work properly
 */
	public void newDisplay(Card c) {
		if (c instanceof Troop) {
			stats.setText("Name : " + c.getName() + "\n CP : " + ((Entity) c).getCpCost() + "\n AP : "
					+ ((Troop) c).getApCost() + "\n Health : " + ((Entity) c).getCurrentHealth() + "\n Attack : "
					+ ((Entity) c).getCurrentAttack() + "\n Description : " + c.getDescription());
		} else if (c instanceof Commander) {
			stats.setText("Name : " + c.getName() + "\n CP : " + ((Entity) c).getCpCost() + "\n AP : "
					+ ((Entity) c).getCurrentHealth() + "\n Attack : " + ((Entity) c).getCurrentAttack()
					+ "\n Description : " + c.getDescription());
		} else if (c instanceof Technique) {
			stats.setText("Name : " + c.getName() + "\n TP : " + ((Technique) c).getTpCost() + "\n Description : "
					+ c.getDescription());
		} else if (c instanceof Structure) {
			stats.setText("Name : " + c.getName() + "\n Description : " + c.getDescription());
		}

	}
}
