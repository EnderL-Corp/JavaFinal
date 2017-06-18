package cards;

public class Mech extends Troop
{
	//abilities in this order: {provoke, deflect, blast, range, mirror, void}
	public Mech(int posX, int posY, int tag, TroopEnum enumName)
	{
		super(tag);
		abilities = new boolean[6];
		if(enumName == TroopEnum.BLASTER)
		{
			apCost = 6;
			cpCost = 7;
			attack = 4;
			health = 6;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities[2] = true; 
			name = "MechBlaster";
			description = "Santi has to do this later";
		}
		else if(enumName == TroopEnum.RANGER)
		{
			apCost = 6;
			cpCost = 6;
			attack = 5;
			health = 5;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities[3] = true; 
			name = "MechRange";
			description = "Santi has to do this later";
		}
		else if(enumName == TroopEnum.DUMMY)
		{
			apCost = 8;
			cpCost = 6;
			attack = 2;
			health = 8;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities[0] = true;
			name = "MechProvoke";
			description = "Santi has to do this later";
		}
		else
		{
			apCost = 5;
			cpCost = 5;
			attack = 5;
			health = 5;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			name = "Mech";
			description = "Santi has to do this later";
		}
		te = enumName;
	}
}

