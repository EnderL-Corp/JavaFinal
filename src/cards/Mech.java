package cards;

public class Mech extends Troop
{
	//abilities in this order: {provoke, deflect, blast, range, mirror, void}
	public Mech(int posX, int posY, int tag, TroopEnum enumName)
	{
		super(tag);
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
			abilities = new boolean[]{false, false, true, false, false, false};
			name = "Blaster Mech";
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
			abilities = new boolean[]{false, false, false, true, false, false};
			name = "Ranger Mech";
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
			abilities = new boolean[]{true, false, false, false, false, false};
			name = "Dummy Mech";
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
			abilities = new boolean[]{false, false, false, false, false, false};
			name = "Mech";
			description = "Santi has to do this later";
		}
	}
}

