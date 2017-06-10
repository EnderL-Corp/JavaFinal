package cards;

public class Human extends Troop	
{
	//abilities in this order: {provoke, deflect, blast, range, mirror, void}
	public Human(int posX, int posY, int tag, TroopEnum enumName)
	{
		super(tag);
		if(enumName == TroopEnum.RANGER)
		{
			apCost = 3;
			cpCost = 3;
			attack = 2;
			health = 2;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities = new boolean[]{false, false, false, true, false, false};
			name = "Ranger Human";
			description = "Santi has to do this later";
		}
		else if(enumName == TroopEnum.DEFLECTOR)
		{
			apCost = 4;
			cpCost = 3;
			attack = 3;
			health = 1;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities = new boolean[]{false, true, false, false, false, false};
			name = "Deflector Human";
			description = "Santi has to do this later";
		}
		else if(enumName == TroopEnum.DUMMY)
		{
			apCost = 3;
			cpCost = 3;
			attack = 0;
			health = 4;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities = new boolean[]{true, false, false, false, false, false};
			name = "Dummy Human";
			description = "Santi has to do this later";
		}
		else
		{
			apCost = 2;
			cpCost = 2;
			attack = 2;
			health = 2;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities = new boolean[]{false, false, false, false, false, false};
			name = "Human";
			description = "Santi has to do this later";
		}

	}
}

