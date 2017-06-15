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
	
	public void effect(Troop effected)
	{
		switch(gearEnum) 
		{
			case MIRROR:
			{
				effected.addAbilities(4);
				break;
			}
			case LAUNCHER:
			{
				effected.addAbilities(3);
				break;
			}
			case EXPLOSIVES:			
			{
				effected.addAbilities(2);
				break;
			}
			case ELECTROMAGNET:			
			{
				effected.addAbilities(0);
				break;
			}
			case SHIELD:			
			{
				effected.addAbilities(1);
				break;
			}
			case SPEED_BOOST:			
			{
				effected.changeApCost(-2);
				break;
			}
		}
	}
	
	public GearEnum getGearEnum()
	{
		return gearEnum;
	}
}
