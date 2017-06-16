package graphics;




import javax.swing.JPanel;
import javax.swing.JLabel;

import cards.Card;
import cards.Commander;
import cards.Entity;
import cards.Structure;
import cards.Technique;
import cards.Troop;


public class InfoPanel  extends JPanel {
	private JLabel stats;


	public InfoPanel(){

	}
	public void newDisplay(Card c){
		if(c instanceof Troop){
			stats = new JLabel("Name : " + c.getName() + "\n CP : " + ((Entity) c).getCpCost() + "\n AP : " + ((Troop) c).getCurrentApCost() + "\n Health : " + 
		((Entity) c).getCurrentHealth() + "\n Attack : " + ((Entity) c).getCurrentAttack() + "\n Description : " + c.getDescription());
		}
		else if(c instanceof Commander){
			stats = new JLabel("Name : " + c.getName() + "\n CP : " + ((Entity) c).getCpCost() + "\n AP : " + 
					((Entity) c).getCurrentHealth() + "\n Attack : " + ((Entity) c).getCurrentAttack() + "\n Description : " + c.getDescription());			
		}
		else if(c instanceof Technique){
			stats = new JLabel("Name : " + c.getName() + "\n TP : " + ((Technique) c).getTpCost() + "\n Description : " + c.getDescription());
	    }
		else if(c instanceof Structure){
			stats = new JLabel("Name : " + c.getName() + "\n Description : " + c.getDescription());
	    }

}
}
