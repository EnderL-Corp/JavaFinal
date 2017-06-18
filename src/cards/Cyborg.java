package cards;

/**
 * @author André
 */
public class Cyborg extends Troop
{
	//abilities in this order: {provoke, deflect, blast, range, mirror, void}
	
	/**
	 * Creates an cyborg with correct values based off of starting ability
	 * 
	 * @param posX - x Pos to spawn at
	 * @param posY - y Pos to spawn at
	 * @param tag - the tag of the Troop
	 * @param enumName - the ability to give this troop on spawn. See <code> TroopEnum.java </code>
	 */
	public Cyborg(int posX, int posY, int tag, TroopEnum enumName)
	{
		super(tag);
		rootName = "Cyborg";
		if(enumName == TroopEnum.RANGER)
		{
			apCost = 5;
			cpCost = 5;
			attack = 3;
			health = 3;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities[3] = true;
			name = formattedName = "CyborgRange";
			description = "";
		}
		else if(enumName == TroopEnum.DEFLECTOR)
		{
			apCost = 6;
			cpCost = 6;
			attack = 4;
			health = 2;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities[1] =true;
			name = formattedName = "CyborgDeflect";
			description = "";
		}
		else if(enumName == TroopEnum.BLASTER)
		{
			apCost = 5;
			cpCost = 6;
			attack = 2;
			health = 4;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities[0] = true;
			name = formattedName = "CyborgBlast";
			description = "";
		}
		else
		{
			apCost = 4;
			cpCost = 4;
			attack = 3;
			health = 3;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			name = formattedName = "Cyborg";
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
		return "Cyborg";
	}
}

