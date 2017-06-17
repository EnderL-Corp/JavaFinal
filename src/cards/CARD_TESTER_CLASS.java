package cards;

import java.rmi.RemoteException;

import cards.Deck.DeckEnum;
import cards.Technique.TechEnum;
import main.Game;

public class CARD_TESTER_CLASS
{
	public Commander commander; 
	public Deck deck;								
																							
	public static int ap; 																		
	public static int cp;																			
	public static int tp;																			
	public static int territory; 																
	
	CARD_TESTER_CLASS()
	{
		commander =  new Commander("Jimmy", "He was a good boy", DeckEnum.RAVAGER, 7, 2, -1);    
		deck = new Deck(commander.getClassType());
		ap = 12;
		cp = 12;
		tp = 4;
		territory = 3;
	}
	
	public static void main(String[] args)
	{
		CARD_TESTER_CLASS cardTesterClass = new CARD_TESTER_CLASS();
		try {
			Game.game = new Game();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//CP Testing
		
		
		Entity troop1 = new Android(4, 4, 0, TroopEnum.DUMMY);
		Entity troop2 = new Dragon(3, 4, 1, null);
		
		System.out.println(cardTesterClass.getCp());
		//the cp that the player has should be 12 to start out
		cardTesterClass.remainingCp(Troop.placeOnBoard((Troop)troop1, cardTesterClass.getCp()));

		System.out.println(cardTesterClass.getCp());
		//a Dummy Android costs 5 cp, so player's cp should now be at 7
		cardTesterClass.remainingCp(Troop.placeOnBoard((Troop)troop2, cardTesterClass.getCp()));
		
		System.out.println(cardTesterClass.getCp());
		//a Dragon costs 15 cp, so player's cp would be at -8. Because of this, the dragon should not place, and cp should remain at 7
		
		System.out.println("");
		
		
		
		
		//AP Testing
		
		
		cardTesterClass.remainingCp(15);
		Troop.placeOnBoard((Troop)troop2, cardTesterClass.getCp());
		// have to place Dragon in order to do ap testing
		
		System.out.println(cardTesterClass.getAp());
		//the ap that the player has should be 12 to start out
		cardTesterClass.remainingAp(Entity.move(troop1, cardTesterClass.getAp(), 4, 5));

		System.out.println(cardTesterClass.getAp());
		//a Dummy Android costs 7 ap to move, so player's ap should now be at 5
		cardTesterClass.remainingAp(Entity.move(troop2, cardTesterClass.getAp(), 4, 4));
		
		System.out.println(cardTesterClass.getAp());
		//a Dragon costs 10 ap to move, so player's cp would be at -5. Because of this, the dragon should not move, and ap should remain at 5
		
		cardTesterClass.remainingAp(10);
		cardTesterClass.remainingAp(Entity.move(troop2, cardTesterClass.getAp(), 4, 5));
		
		System.out.println(cardTesterClass.getAp());
		//a Dragon costs 10 ap to move, and i just gave the player 10 cp, so player's cp would be at 0, 
		//but because the dragon is trying to move into an occupied space the dragon should not move, and ap should remain at 10

		System.out.println("");
		
		
		
		
		//TP Testing

		
		Technique technique1 = new Technique(TechEnum.CANNON);
		//makes a "Call" technique 
			
		System.out.println(cardTesterClass.getTp());
		//the tp that the player has should be 4 to start out

		if(technique1.canCast(cardTesterClass.getTp()))
		{
			cardTesterClass.remainingTp(cardTesterClass.getTp() - technique1.getTpCost());
			technique1.cast((Troop)troop1);
		}
		
		System.out.println( cardTesterClass.getTp());
		//"Call" costs 4 tp to cast, so player's tp should now be at 0;	
	}
	
	
	
	public int getAp()
	{
		return ap;
	}
	
	public void remainingAp(int AP)
	{
		ap = AP;
	}
	
	public int getTp()
	{
		return tp;
	}
	
	public void remainingTp(int TP)
	{
		tp = TP;
	} 
	
	public int getCp()
	{
		return cp;
	}
	
	public void remainingCp(int CP)
	{
		cp = CP;
	}
}
