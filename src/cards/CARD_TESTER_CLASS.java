package cards;

import main.Game;

public class CARD_TESTER_CLASS
{
		public static void main(String[] args)
		{
			Entity troop2 = new Dragon(3, 4, 1, null);
			
			System.out.println("" + troop2.getCurrentHealth() + " " + troop2.getCurrentAttack());
			//should return 10 10, as a dragon has 10 health and 10 attack

			troop2.modify(1, 2);
			
			System.out.println("" + troop2.getCurrentHealth() + " " + troop2.getCurrentAttack()); 
			//should now return 11 12, as a dragon has 10 health and 10 attack + 1 health and + 2 attack makes 11 health and 12 attack
			
			troop2.modify(3, -4);
			
			System.out.println("" + troop2.getCurrentHealth() + " " + troop2.getCurrentAttack()); 
			//should now return 14 8, as the dragon had 11 health and 12 attack + 3 health and - 4 attack makes 14 health and 8gg attack
		}
}
