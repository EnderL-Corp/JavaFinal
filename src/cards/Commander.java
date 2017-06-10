package cards;

import main.Game;

public class Commander extends Entity
{
	private int classType;
	//remember Abilities in this order: {provoke, deflect, blast, range, mirror, void}
	
	public Commander(String nm, String desc, int ct, int posX, int posY, int tag)
	{
		super(nm, desc, 2, 0, 1, 25, posX, posY, tag); //Check these values
		classType = ct;
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
	
	public boolean canDeflect()
	{ 
		return false; 
	}
	
	public boolean canMirror()
	{ 
		return false; 
	}
	
	public boolean[] getAbilities()
	{ 
		return null; 
	}
	
	public boolean hasAbility(int num)
	{
		return false;
	}
	
	public void kill(Entity killed)
	{
		Game.game.endGame((Commander)killed);
	}
	
}
