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
	
	public Gear(GearEnum gEnum) 
	{
		super("" + gEnum, "Santi can do this");
		gearEnum = gEnum;
	}
	
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
