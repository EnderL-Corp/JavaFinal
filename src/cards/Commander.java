package cards;

public class Commander extends Entity
{
	private int classType;
	public Commander(String nm, String desc, int ap, int cp, int atk, int hp, int ct, int posX, int posY, int tag)
	{
		super(nm, desc, 2, 0, 1, 25, posX, posY, tag); //Check these values
		classType = ct;
	}

	public void attack()
	{
		//checks everything around
		//dealDamage(defender)
	}
	
	public boolean canDeflect()
	{ return false; }
	
	public boolean canMirror()
	{ return false; }
	
	public boolean[] getAbilities()
	{ return null; }
	
	public void kill()
	{
		//end game
	}
	
}
