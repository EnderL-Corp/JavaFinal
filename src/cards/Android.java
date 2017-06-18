package cards;

/**
 * @author André
 */
public class Android extends Troop
{
	//abilities in this order: {provoke, deflect, blast, range, mirror, void}
	
	/**
	 * Creates an android with correct values based off of starting ability
	 * 
	 * @param posX - x Pos to spawn at
	 * @param posY - y Pos to spawn at
	 * @param tag - the tag of the Troop
	 * @param enumName - the ability to give this troop on spawn. See <code> TroopEnum.java </code>
	 */
	public Android(int posX, int posY, int tag, TroopEnum enumName)
	{
		super(tag);
		rootName = "Android";
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
			abilities[2] = true;
			name = "AndroidBlaster";
			description = "";
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
			abilities[1] = true;
			name = "AndroidDeflect";
			description = "";
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
			abilities[0] = true;
			name = "AndroidProvoke";		//Dummy android?
			description = "";
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
			name = "Android";
			description = "";
		}
		te = enumName;
		if(abilities[1] == true)
			deflectTime = true;
		else
			deflectTime = false;
		
		if(abilities[4] == true)
			mirrorTime = true;
		else
			mirrorTime = false;
	}
	
	/**
	 * @return - The type of troop
	 */
	public String getTroopType() {
		return "Android";
	}
}

