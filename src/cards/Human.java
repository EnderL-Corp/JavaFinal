package cards;

/**
 * @author André
 */
public class Human extends Troop {
	// abilities in this order: {provoke, deflect, blast, range, mirror, void}

	/**
	 * Creates an human with correct values based off of starting ability
	 * 
	 * @param posX
	 *            - x Pos to spawn at
	 * @param posY
	 *            - y Pos to spawn at
	 * @param tag
	 *            - the tag of the Troop
	 * @param enumName
	 *            - the ability to give this troop on spawn. See
	 *            <code> TroopEnum.java </code>
	 */
	public Human(int posX, int posY, TroopEnum enumName) {
		rootName = "Human";
		if (enumName == TroopEnum.RANGER) {
			apCost = 3;
			cpCost = 3;
			attack = 2;
			health = 2;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities[3] = true;
			name = formattedName = "HumanRange";
			description = "";
		} else if (enumName == TroopEnum.DEFLECTOR) {
			apCost = 4;
			cpCost = 3;
			attack = 3;
			health = 1;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities[1] = true;
			name = formattedName = "HumanDeflect";
			description = "";
		} else if (enumName == TroopEnum.DUMMY) {
			apCost = 3;
			cpCost = 3;
			attack = 0;
			health = 4;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			abilities[0] = true;
			name = formattedName = "HumanProvoke";
			description = "";
		} else {
			apCost = 2;
			cpCost = 2;
			attack = 2;
			health = 2;
			currentAttack = attack;
			currentHealth = health;
			xCoordinate = posX;
			yCoordinate = posY;
			name = formattedName = "Human";
			description = "";
		}
		te = enumName;
		if (abilities[1] == true)
			deflectTime = true;
		else
			deflectTime = false;

		if (abilities[4] == true)
			mirrorTime = true;
		else
			mirrorTime = false;
	}

	/**
	 * @return - The type of troop
	 */
	public String getTroopType() {
		return "Human";
	}
}
