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
	
	public CARD_TESTER_CLASS()
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
		//mileStone1();
		//mileStone2();
		mileStone3();
		//mileStone4();
	}

	public static void mileStone1()
	{
		Entity troop2 = new Dragon(3, 4, 1, null);
		
		System.out.println("" + troop2.getCurrentHealth() + " " + troop2.getCurrentAttack());
		//should return 10 10, as a dragon has 10 health and 10 attack
		
		troop2.modify(1, 2);
		
		System.out.println("" + troop2.getCurrentHealth() + " " + troop2.getCurrentAttack());
		//should return 11 12, as a dragon has 10 health and 10 attack + 1 health and + 2 attack makes 11 health and 12 attack
		
		troop2.modify(3, -4);
		
		System.out.println("" + troop2.getCurrentHealth() + " " + troop2.getCurrentAttack());
		//should return 10 10, as the dragon had 11 health and 12 attack  + 3 health and - 4 attack makes 14 health and 8 attack
	}
	
	public static void mileStone2()
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
	
	public static void mileStone3()
	{
		CARD_TESTER_CLASS cardTesterClass = new CARD_TESTER_CLASS();
		try {
			Game.game = new Game();
		} catch (RemoteException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game.game.init(DeckEnum.RAVAGER);
		
		//Blast and Provoke testing
		
		Entity drone1 = new Drone(2, 5, 1, null);
		Entity drone2 = new Drone(3, 5, 2, null);
		Entity drone3 = new Drone(3, 6, 3, TroopEnum.X);
		Entity drone4 = new Drone(3, 7, 4, TroopEnum.X);
		Entity drone5 = new Drone(1, 6, 5, null);
		Entity troop1 = new Android(4, 4, 6, TroopEnum.DUMMY);
		Entity troop2 = new Dragon(3, 4, 7, null);
		
		Game.game.placeEntity(drone1);
		Game.game.placeEntity(drone2);
		Game.game.placeEntity(drone3);
		Game.game.placeEntity(drone4);
		Game.game.placeEntity(drone5);
		Game.game.placeEntity(troop1);
		Game.game.placeEntity(troop2);
		
		System.out.println(Game.game.getEntityAt(2, 5) + "\t" + Game.game.getEntityAt(3, 5) + "\t" + Game.game.getEntityAt(3, 6)
		+ "\t" + Game.game.getEntityAt(3, 7) + "\t" + Game.game.getEntityAt(1, 6) + "\t" + Game.game.getEntityAt(4, 4) + "\t" + Game.game.getEntityAt(3, 4));
		//should display Drone, Drone, Drone, Drone, Drone, Android, Dragon
		
		troop2.attack(drone3);
		
		System.out.println(Game.game.getEntityAt(2, 5) + "\t" + Game.game.getEntityAt(3, 5) + "\t" + Game.game.getEntityAt(3, 6)
		+ "\t" + Game.game.getEntityAt(3, 7) + "\t" + Game.game.getEntityAt(1, 6) + "\t" + Game.game.getEntityAt(4, 4) + "\t" + Game.game.getEntityAt(3, 4));
		//should display the same thing, because the dragon cant attack the drone due to the android's provoke
		
		troop2.attack(troop1);
		
		System.out.println(Game.game.getEntityAt(2, 5) + "\t" + Game.game.getEntityAt(3, 5) + "\t" + Game.game.getEntityAt(3, 6)
		+ "\t" + Game.game.getEntityAt(3, 7) + "\t" + Game.game.getEntityAt(1, 6) + "\t" + Game.game.getEntityAt(4, 4) + "\t" + Game.game.getEntityAt(3, 4));
		//should display the same thing, except the android is replaced by null, because it was killed by the dragon
		
		troop2.attack(drone3);
		
		System.out.println(Game.game.getEntityAt(2, 5) + "\t" + Game.game.getEntityAt(3, 5) + "\t" + Game.game.getEntityAt(3, 6)
		+ "\t" + Game.game.getEntityAt(3, 7) + "\t" + Game.game.getEntityAt(1, 6) + "\t" + Game.game.getEntityAt(4, 4) + "\t" + Game.game.getEntityAt(3, 4));
		//should display Drone, null, null, null, Drone, null, dragon, because the dragon's blast had nothing to provoke it, and could kill all thre drones in one attack
		
		troop2.attack(drone1);
		
		System.out.println(Game.game.getEntityAt(2, 5) + "\t" + Game.game.getEntityAt(3, 5) + "\t" + Game.game.getEntityAt(3, 6)
		+ "\t" + Game.game.getEntityAt(3, 7) + "\t" + Game.game.getEntityAt(1, 6) + "\t" + Game.game.getEntityAt(4, 4) + "\t" + Game.game.getEntityAt(3, 4));
		//should display the same as above, but first drone replaced with null, because the dragon can attack diagonally is in melee range (with regular attack, not blast attack)
		
		troop2.attack(drone5);
		
		System.out.println(Game.game.getEntityAt(2, 5) + "\t" + Game.game.getEntityAt(3, 5) + "\t" + Game.game.getEntityAt(3, 6)
		+ "\t" + Game.game.getEntityAt(3, 7) + "\t" + Game.game.getEntityAt(1, 6) + "\t" + Game.game.getEntityAt(4, 4) + "\t" + Game.game.getEntityAt(3, 4));
		//should be the same as above, because the dragon cant attack diagonally if out of range
		
		System.out.println("\n");
		//Range and Provoke testing
		
		Entity troop3 = new Android(3, 6, 1, TroopEnum.DUMMY);
		Entity troop4 = new Cyborg(2, 7, 2, TroopEnum.RANGER);
		
		Game.game.placeEntity(drone1);
		Game.game.placeEntity(drone4);
		Game.game.placeEntity(troop3);
		Game.game.placeEntity(troop4);
		
		System.out.println(Game.game.getEntityAt(2, 5) + "\t" + Game.game.getEntityAt(2, 7) + "\t" + Game.game.getEntityAt(3, 6)
		+ "\t" + Game.game.getEntityAt(3, 7) + "\t" + Game.game.getEntityAt(1, 6) + "\t" + Game.game.getEntityAt(3, 4));
		//should display Drone, Cyborg, Android, Drone, Drone, Dragon
		
		troop4.attack(drone1);
		
		System.out.println(Game.game.getEntityAt(2, 5) + "\t" + Game.game.getEntityAt(2, 7) + "\t" + Game.game.getEntityAt(3, 6)
		+ "\t" + Game.game.getEntityAt(3, 7) + "\t" + Game.game.getEntityAt(1, 6) + "\t" + Game.game.getEntityAt(3, 4));
		//should display the same thing as above, because the cyborg can't attack with the android's provoke
		
		troop2.attack(troop3);
		
		System.out.println(Game.game.getEntityAt(2, 5) + "\t" + Game.game.getEntityAt(2, 7) + "\t" + Game.game.getEntityAt(3, 6)
		+ "\t" + Game.game.getEntityAt(3, 7) + "\t" + Game.game.getEntityAt(1, 6) + "\t" + Game.game.getEntityAt(3, 4));
		//used the dragon to kill the provoke, and it also took out a drone. so it should now display Drone, Cyborg, null, null, Drone, Dragon
		
		troop4.attack(drone1);
		
		System.out.println(Game.game.getEntityAt(2, 5) + "\t" + Game.game.getEntityAt(2, 7) + "\t" + Game.game.getEntityAt(3, 6)
		+ "\t" + Game.game.getEntityAt(3, 7) + "\t" + Game.game.getEntityAt(1, 6) + "\t" + Game.game.getEntityAt(3, 4));
		//because the cyborg has range, it can attack and kill the drone, making the display null, Cyborg, null, null, Drone, Dragon
		
		troop4.attack(drone5);
		
		System.out.println(Game.game.getEntityAt(2, 5) + "\t" + Game.game.getEntityAt(2, 7) + "\t" + Game.game.getEntityAt(3, 6)
		+ "\t" + Game.game.getEntityAt(3, 7) + "\t" + Game.game.getEntityAt(1, 6) + "\t" + Game.game.getEntityAt(3, 4));
		//should display null, Cyborg, null, null, null, Dragon, because Dragon and Cyborg are the only onees left
	}
	
	public static void mileStone4()
	{
		CARD_TESTER_CLASS cardTesterClass = new CARD_TESTER_CLASS();
		try {
			Game.game = new Game();
		} catch (RemoteException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
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
