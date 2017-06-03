package cards;

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
		//does effect to effected
		//goes to graveyard
	}
}
