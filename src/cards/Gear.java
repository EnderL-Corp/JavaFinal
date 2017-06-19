package cards;

import main.Game;

/**
 * @author André, Luke
 */
public class Gear extends Structure {
	// remember Abilities in this order: {provoke, deflect, blast, range,
	// mirror, void}
	protected GearEnum gearEnum;

	public enum GearEnum {
		MIRROR, LAUNCHER, EXPLOSIVES, ELECTROMAGNET, SHIELD, SPEED_BOOST;
	}

	/**
	 * Gear is a boost placed on a troop to enhance it.
	 * 
	 * @param gEnum
	 *            - The type of gear to create
	 */
	public Gear(GearEnum gEnum) {
		super("" + gEnum, "");
		gearEnum = gEnum;

	}

	/**
	 * <code>effect(effected)</code> adds the specified effect onto the effected
	 * Troop.
	 * 
	 * @param Troop
	 *            - The troop on which to place the gear
	 * @return boolean - Whether or not the ability was added
	 */
	public boolean effect(Troop effected) {
		switch (gearEnum) {
		case MIRROR: {
			Game.game.getHand().remove(this);
			return effected.addAbilities(4);
		}
		case LAUNCHER: {
			Game.game.getHand().remove(this);
			return effected.addAbilities(3);
		}
		case EXPLOSIVES: {
			Game.game.getHand().remove(this);
			return effected.addAbilities(2);
		}
		case ELECTROMAGNET: {
			Game.game.getHand().remove(this);
			return effected.addAbilities(0);
		}
		case SHIELD: {
			Game.game.getHand().remove(this);
			return effected.addAbilities(1);
		}
		case SPEED_BOOST: {
			Game.game.getHand().remove(this);
			effected.changeApCost(-2);
			effected.updateDescription();
			return true;
		}
		}
		return false;
	}

	public GearEnum getGearEnum() {
		return gearEnum;
	}

	@Override
	public void updateDescription() {
		switch (gearEnum) {
		case MIRROR: {
			description = "Gives a troop mirror. \nMirror does 1/2 the damage \ndone to the defender to the attacker "
					+ "\nonce per the opponents turn.";
			break;
		}
		case LAUNCHER: {
			description = "Gives a troop range. Range \nallows a troop to attack \nanywhere on the board but "
					+ "\nit cannot attack the commander.";
			break;
		}
		case EXPLOSIVES: {
			description = "Gives a troop blast. Range \nallows a troop to attack a whole row \nregardless of team, stopping at void.";
			break;
		}
		case ELECTROMAGNET: {
			description = "Gives a troop provoke. Provoke \nforces adjacent entities to attack \nthis troop.";
			break;
		}
		case SHIELD: {
			description = "Gives a troop deflect. \nThis troop takes no damage the first time \nit is attacked per opponent turn.";
			break;
		}
		case SPEED_BOOST: {
			description = "Reduces a troop's AP cost by 2.";
			break;
		}
		default: {
			description = null;
		}
		}
	}
}
