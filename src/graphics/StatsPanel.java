package graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsPanel  extends JPanel{
	JLabel yourHP;
	JLabel otherHP;
	JLabel cp;
	JLabel tp;
	JLabel ap;
	
	private StatsPanel() {
		//add a phat layout
		yourHP = new JLabel("Your HP :" + commanerHP.get());
		otherHP = new JLabel("Foe's HP :" + otherCommanerHP.get());
		cp = new JLabel("Cp :" + commanercp.get());
		tp = new JLabel("TP :" + commanertp.get());
		ap = new JLabel("AP :" + commanerap.get());
		
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
	public void statRefresh(){
		yourHP.setText("Your HP :" + commanerHP.get());
		yourHP.setVisible(false);
		yourHP.setVisible(true);
		otherHP.setText("Foe's HP :" + otherCommanerHP.get());
		otherHP.setVisible(false);
		otherHP.setVisible(true);		
		cp.setText("Cp :" + commanercp.get());
		cp.setVisible(false);
		cp.setVisible(true);		
		tp.setText("TP :" + commanertp.get());
		tp.setVisible(false);
		tp.setVisible(true);		
		ap.setText("AP :" + commanerap.get());
		ap.setVisible(false);
		ap.setVisible(true);
	}
	
}
