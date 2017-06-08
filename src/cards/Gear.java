package cards;

import main.Game;

public class Gear extends Structure
{
	protected String ability;
	
	public Gear(String nm, String desc, String ab) 
	{
		super(nm, desc);
		ability = ab;
	}
	
	public void effect(Entity effected)
	{
		//effect
		Game.game.addToGraveyard(this);
	}
}
