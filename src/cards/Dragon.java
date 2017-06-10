package cards;

public class Dragon extends Troop
{
	//abilities in this order: {provoke, deflect, blast, range, mirror, void}
	public Dragon(int posX, int posY, int tag, TroopEnum enumName)
	{
		super(tag);
		apCost = 10;
		cpCost = 15;
		attack = 10;
		health = 10;
		currentAttack = attack;
		currentHealth = health;
		xCoordinate = posX;
		yCoordinate = posY;
		abilities = new boolean[]{false, false, true, false, false, false};
		name = "Dragon";
		description = "Santi has to do this later";
	}
}

