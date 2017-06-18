package cards;

/**
 * @author André
 */
public class Drone extends Troop
{
	/**
	 * Creates an drone with correct values based off of starting ability
	 * 
	 * @param posX - x Pos to spawn at
	 * @param posY - y Pos to spawn at
	 * @param tag - the tag of the Troop
	 * @param enumName - the ability to give this troop on spawn. See <code> TroopEnum.java </code>
	 */
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
			name = formattedName = "Drone";
			description = "";
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
			name = formattedName = "Drone";
			description = "";
		}
	}
	
	/**
	 * @return - The type of troop
	 */
	public String getTroopType() {
		return "Drone";
	}
}

