package cards;

public class Cyborg extends Troop
{
	//abilities in this order: {provoke, deflect, blast, range, mirror, void}
	public Cyborg(int posX, int posY, int tag, TroopEnum enumName)
	{
		super(tag);
		if(enumName == TroopEnum.RANGER)
		{
			apCost = 5;
			cpCost = 5;
			attack = 3;
			health = 3;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities = new boolean[]{false, false, false, true, false, false};
			name = "Ranger Cyborg";
			description = "Santi has to do this later";
		}
		else if(enumName == TroopEnum.DEFLECTOR)
		{
			apCost = 6;
			cpCost = 6;
			attack = 4;
			health = 2;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities = new boolean[]{false, true, false, false, false, false};
			name = "Deflector Cyborg";
			description = "Santi has to do this later";
		}
		else if(enumName == TroopEnum.BLASTER)
		{
			apCost = 5;
			cpCost = 6;
			attack = 2;
			health = 4;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities = new boolean[]{true, false, false, false, false, false};
			name = "Blaster Cyborg";
			description = "Santi has to do this later";
		}
		else
		{
			apCost = 4;
			cpCost = 4;
			attack = 3;
			health = 3;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities = new boolean[]{false, false, false, false, false, false};
			name = "Cyborg";
			description = "Santi has to do this later";
		}
	}
}

