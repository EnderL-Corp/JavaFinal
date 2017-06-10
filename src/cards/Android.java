package cards;

public class Android extends Troop
{
	//abilities in this order: {provoke, deflect, blast, range, mirror, void}
	public Android(int posX, int posY, int tag, TroopEnum enumName)
	{
		super(tag);
		if(enumName == TroopEnum.BLASTER)
		{
			apCost = 6;
			cpCost = 6;
			attack = 3;
			health = 5;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities = new boolean[]{false, false, true, false, false, false};
			name = "Blaster Android";
			description = "Santi has to do this later";
		}
		else if(enumName == TroopEnum.DEFLECTOR)
		{
			apCost = 6;
			cpCost = 5;
			attack = 5;
			health = 3;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities = new boolean[]{false, true, false, false, false, false};
			name = "Deflector Android";
			description = "Santi has to do this later";
		}
		else if(enumName == TroopEnum.DUMMY)
		{
			apCost = 7;
			cpCost = 5;
			attack = 1;
			health = 7;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities = new boolean[]{true, false, false, false, false, false};
			name = "Dummy Android";
			description = "Santi has to do this later";
		}
		else
		{
			apCost = 5;
			cpCost = 4;
			attack = 4;
			health = 4;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities = new boolean[]{false, false, false, false, false, false};
			name = "Android";
			description = "Santi has to do this later";
		}

	}
}

