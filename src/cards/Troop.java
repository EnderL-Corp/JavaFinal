package cards;

public class Troop extends Entity
{
	protected boolean[] abilities; //{provoke, deflect, blast, range, mirror, void}
	protected boolean deflectTime = false;
	protected boolean mirrorTime = false;

	public Troop(String nm, String desc, int ap, int cp, int atk, int hp, int posX, int posY, int tag, boolean[] abs) 
	{
		super(nm, desc, ap, cp, atk, hp, posX, posY, tag);
		abilities = abs;
		if(abilities[1] == true)
			deflectTime = true;
		if(abilities[4] == true)
			mirrorTime = true;
	}

	public boolean[] getAbilities()
	{
		return abilities;
	}
	
	/**
	 * @param abs - the index of the ability i.e. 1 for deflect, or 5 for void
	 * @return boolean - whether or not the ability was added
	 */
	public boolean addAbilities(int abs)
	{
		if(abs == 1)
			deflectTime = true;
		if(abs == 4)
			mirrorTime = true;
		
		if(abilities[abs] == false)
		{
			if((abilities[2] == true || abilities[3] == true) && (abs == 2 || abs == 3))
				return false;
			abilities[abs] = true;
			return true;
		}
		return false;
	}

	public boolean canDeflect()
	{
		if(deflectTime == true)
		{
			deflectTime = false;
			return true;
		}
		return false;
	}
	
	public boolean canMirror()
	{
		if(mirrorTime == true)
		{
			mirrorTime = false;
			return true;
		}
		return false;
	}
		
	public void attack()
	{
		//were going to have to make a position or something
	}
	
	public void kill() 
	{
		//Send to discard pile
	}
}
