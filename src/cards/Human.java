package cards;

public class Human extends Troop	
{
	//abilities in this order: {provoke, deflect, blast, range, mirror, void}
	public Human(int posX, int posY, int tag, TroopEnum enumName)
	{
		super(tag);
		abilities = new boolean[6];
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
			abilities[3] = true;
			name = "HumanRange";
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
			abilities[1] = true; 
			name = "HumanDeflect";
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
			abilities[0] = true;
			name = "HumanProvoke";
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
			name = "Human";
			description = "Santi has to do this later";
		}

	}
	
	public String getTroopType() {
		return "Human";
	}
}

