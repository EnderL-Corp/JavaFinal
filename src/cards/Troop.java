package cards;

import java.util.List;

public class Troop extends Entity
{
	protected List<String> abilities;

	public Troop(String nm, String desc, int ap, int cp, int atk, int hp, List<String> abs) 
	{
		super(nm, desc, ap, cp, atk, hp);
		abilities = abs;
	}
	
	public List<String> getAbilities()
	{
		return abilities;
	}
	
	/**
	 * @param name - The ability to add
	 * @return boolean - whether or not the ability was added
	 */
	public boolean addAbilities(String name)
	{
		for(String s : abilities)
		{
			if(s.equals(name))
			{
				return false;
			}
		}
		abilities.add(name);
		return true;
	}

	public void kill() 
	{
		//Send to discard pile
	}

	public void modifyHealth(int val, Card originator) 
	{
		if(val < 0 && originator instanceof Entity)
		{
			for(String s : abilities)
			{
				if(s.equals("Mirror"))
				{
					((Entity) originator).modifyHealth(val / 2, null);
				}
			}
		}
		
		health = health + val > maxHealth ? maxHealth : health + val;
	}
}
