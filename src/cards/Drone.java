package cards;

public class Drone extends Troop
{
	public Drone(int posX, int posY, int tag, TroopEnum enumName)
	{
		super(tag);
		rootName = "Drone";
		if(enumName == TroopEnum.X)
		{
			apCost = 1;
			cpCost = 1;
			attack = 1;
			health = 2;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			name = "Drone";
			description = "Santi has to do this later";
		}
		else
		{
			apCost = 1;
			cpCost = 1;
			attack = 1;
			health = 1;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			name = "Drone";
			description = "Santi has to do this later";
		}
	}
	
	public String getTroopType() {
		return "Drone";
	}
}

