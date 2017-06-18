package cards;

import java.util.ArrayList;

import main.Game;

public class Gear extends Structure
{
	//remember Abilities in this order: {provoke, deflect, blast, range, mirror, void}
	protected GearEnum gearEnum;
	
	public enum GearEnum
	{
		MIRROR,
		LAUNCHER,
		EXPLOSIVES,
		ELECTROMAGNET,
		SHIELD,
		SPEED_BOOST;
	}
	
	/**
	 * Gear is a boost placed on a troop to enhance it.
	 * @param gEnum - The type of gear to create
	 */
	public Gear(GearEnum gEnum) 
	{
		super("" + gEnum, "Santi can do this");
		gearEnum = gEnum;
	}
	
	/**
	 * <code>effect(effected)</code> adds the specified effect onto the effected Troop. 
	 * @param Troop - The troop on which to place the gear
	 * @return boolean - Whether or not the ability was added
	 */
	public boolean effect(Troop effected)
	{
		switch(gearEnum) 
		{
			case MIRROR:
			{
				return effected.addAbilities(4);
			}
			case LAUNCHER:
			{
				return effected.addAbilities(3);
			}
			case EXPLOSIVES:			
			{
				return effected.addAbilities(2);
			}
			case ELECTROMAGNET:			
			{
				return effected.addAbilities(0);
			}
			case SHIELD:			
			{
				return effected.addAbilities(1);
			}
			case SPEED_BOOST:			
			{
				effected.changeApCost(-2);
				return true;
			}
		}
		return false;
	}
	
	public GearEnum getGearEnum()
	{
		return gearEnum;
	}
}
