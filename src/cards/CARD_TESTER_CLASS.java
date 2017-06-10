package cards;

import main.Game;

public class CARD_TESTER_CLASS
{
		public static void main(String[] args)
		{
			int tagNum = 1;
			Entity troop1 = new Drone(4, 4, tagNum, TroopEnum.X);
			tagNum++;
			Entity troop2 = new Dragon(3, 4, tagNum, null);
			tagNum++;
			Entity troop3 = new Mech(3, 3, tagNum, TroopEnum.DUMMY);
			
			Game.board[troop1.getPosX()][troop1.getPosY()] = troop1;
			Game.board[troop2.getPosX()][troop2.getPosY()] = troop2;
			Game.board[troop3.getPosX()][troop3.getPosY()] = troop3;
			
			System.out.println(troop1.getCurrentHealth() + " " + troop3.getCurrentHealth());

			troop2.attack(troop1);
			
			System.out.println(troop1.getCurrentHealth() + " " + troop3.getCurrentHealth());
			
			troop2.attack(troop3);
			
			System.out.println(troop1.getCurrentHealth() + " " + troop3.getCurrentHealth());
		}
}
