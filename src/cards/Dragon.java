package cards;

/**
 * @author André
 */
public class Dragon extends Troop
{
	//abilities in this order: {provoke, deflect, blast, range, mirror, void}
	
	/**
	 * Creates an dragon with correct values based off of starting ability
	 * 
	 * @param posX - x Pos to spawn at
	 * @param posY - y Pos to spawn at
	 * @param tag - the tag of the Troop
	 * @param enumName - the ability to give this troop on spawn. See <code> TroopEnum.java </code>
	 */
	public Dragon(int posX, int posY, int tag, TroopEnum enumName)
	{
		super(tag);
		rootName = "Dragon";
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
	
	/**
	 * @return - The type of troop
	 */
	public String getTroopType() {
		return "Dragon";
	}
}

