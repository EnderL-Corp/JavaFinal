package cards;

import main.Game;

public class Troop extends Entity
{
	protected boolean[] abilities; //in this order: {provoke, deflect, blast, range, mirror, void}
	protected boolean deflectTime, mirrorTime;
	protected int currentApCost; //for the speed boost gear

	public Troop(int tag) 
	{
		super(tag);
		
		if(abilities[1] == true)
			deflectTime = true;
		if(abilities[4] == true)
			mirrorTime = true;
		
		currentApCost = apCost;
	}
	

	/**
	 * @param abs - the index of the ability i.e. 1 for deflect, or 5 for void (see above)
	 * @return boolean - whether or not the ability was added
	 */
	public boolean addAbilities(int abs)
	{
		if(abs == 1)
			deflectTime = true; // in order to reset the abilities each turn, you just have to addAbilites(1 or 4) at the beginning of the phase
		if(abs == 4)
			mirrorTime = true;
		
		if(abilities[abs] == false)
		{
			if((abilities[2] == true || abilities[3] == true) && (abs == 2 || abs == 3)) // makes sure you dont have blast and range
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
	
	public boolean hasAbility(int num)
	{
		return abilities[num];
	}
	
	public void attack(Entity defender)
	{
		boolean canAttack = true;
		for(int i = xCoordinate - 1; i < 2; i++)
		{
			for(int j = yCoordinate - 1; j < 2; j++)
			{
				if((i != xCoordinate && j != yCoordinate) && Game.board[xCoordinate][yCoordinate].hasAbility(0) == true)
				{				
					canAttack = false;
				}
			}
		}
		
		//has to check own abilities first
		
		if(canAttack = false)
		{
			if(defender.hasAbility(0))
			{
				dealDamage(defender);
			}
		}
		else
		{
			dealDamage(defender);
		}
	}
	
	public void kill() 
	{
		Game.game.addToGraveyard(this);
	}
	
	public int getCurrentApCost() {
		return currentApCost;
	}

	public boolean[] getAbilities() {
		return abilities;
	}
	
}

