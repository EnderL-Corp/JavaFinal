package cards;

public class Dragon extends Troop
{
	//abilities in this order: {provoke, deflect, blast, range, mirror, void}
	public Dragon(int posX, int posY, int tag, TroopEnum enumName)
	{
		super(tag);
		abilities = new boolean[6];
		apCost = 10;
		cpCost = 15;
		attack = 10;
		health = 10;
		currentAttack = attack;
		currentHealth = health;
		xCoordinate = posX;
		yCoordinate = posY;
		abilities[2] = true;
		name = "Dragon";
		description = "Santi has to do this later";
	}
	
	public String getTroopType() {
		return "Dragon";
	}
}

