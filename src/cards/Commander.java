package cards;

import cards.Deck.DeckEnum;
import main.Game;

public class Commander extends Entity
{
	private DeckEnum classType;
	//class order : {Ravager, swarm, DJ}
	//remember Abilities in this order: {provoke, deflect, blast, range, mirror, void}
	
	public Commander(String nm, String desc, DeckEnum ct, int posX, int posY, int tag)
	{
		super(nm, desc, 2, 0, 1, 25, posX, posY, tag); //Check these values
		classType = ct;
	}

	public void attack(Entity defender)
	{
		if(Math.abs(defender.getPosX() - xCoordinate) == 1 && Math.abs(defender.getPosY() - yCoordinate) == 1)
			return; //if not in range, don't attack (also cant attack self)
		
		boolean canAttack = true;
		for(int i = xCoordinate - 1; i < xCoordinate + 1; i++)
		{
			for(int j = yCoordinate - 1; j < yCoordinate + 1; j++)
			{
				if((i != xCoordinate && j != yCoordinate) && Game.game.getEntityAt(i,j) != null && Game.game.getEntityAt(i,j).hasAbility(0) == true)
				{				
					canAttack = false;
				}
			}
		}
		if(canAttack == false)
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
		Game.game.endGame(false);
	}
	
	public DeckEnum getClassType()
	{
		return classType;			
	}
}
