package cards;

import java.io.Serializable;

import cards.Deck.DeckEnum;
import main.Game;

/**
 * @author André
 */
public class Commander extends Entity implements Serializable {
	private static final long serialVersionUID = -2645359046089708291L;

	private DeckEnum classType;
	// class order : {Ravager, swarm, DJ}
	// remember Abilities in this order: {provoke, deflect, blast, range,
	// mirror, void}

	/**
	 * Creates a Commander. The Commander is the MOST important thing in the
	 * game. If a Player's Commander dies, they lose
	 * 
	 * @param nm
	 *            - Name
	 * @param desc
	 *            - Description
	 * @param ct
	 *            - The class (Ravager, swarm, or DJ)
	 * @param posX
	 *            - The x position of the Commander
	 * @param posY
	 *            - The y position of the Commander
	 */
	public Commander(String nm, String desc, DeckEnum ct, int posX, int posY) {
		super(nm, desc, 2, 0, 1, 25, posX, posY); // Check these values
		classType = ct;
	}

	/**
	 * <code>attack(defender)</code> attempts to make the Commander attack the
	 * defender. The method checks to see if the defender is at most 1 tile away
	 * and if any troop adjacent to the Commander has provoke. If the attack is
	 * valid, this method then calls <code>dealDamage(defender)</code>
	 * 
	 * @param Entity
	 *            - The Entity to Attack
	 */
	public void attack(Entity defender) {
		if (Math.abs(defender.getPosX() - xCoordinate) == 1 && Math.abs(defender.getPosY() - yCoordinate) == 1)
			return; // if not in range, don't attack (also cant attack self)

		boolean canAttack = true;
		for (int i = xCoordinate - 1; i < xCoordinate + 1; i++) {
			for (int j = yCoordinate - 1; j < yCoordinate + 1; j++) {
				if ((i != xCoordinate && j != yCoordinate) && Game.game.getEntityAt(i, j) != null
						&& Game.game.getEntityAt(i, j).hasAbility(0) == true) {
					canAttack = false;
				}
			}
		}
		if (canAttack == false) {
			if (defender.hasAbility(0)) {
				dealDamage(defender);
			}
		} else {
			dealDamage(defender);
		}
	}

	public boolean canDeflect() {
		return false;
	}

	public boolean canMirror() {
		return false;
	}

	public boolean[] getAbilities() {
		return null;
	}

	public boolean hasAbility(int num) {
		return false;
	}

	/**
	 * Since the Player's Commander has died, end the game in a loss
	 */
	public void kill(Entity killed) {
		Game.game.endGame(false);
	}

	public DeckEnum getClassType() {
		return classType;
	}

	@Override
	public void updateDescription() {
		description = description;
	}
}
