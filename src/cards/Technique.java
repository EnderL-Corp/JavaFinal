package cards;

import main.Game;

/**
 * 
 * @author Luke Letourneau, André Artaud
 *
 */
public class Technique extends Card
{
	protected TechEnum techEnum;
	protected int tpCost;
	private int numberOfTargets, remainingTargets; /* 0 if doesn't target anything i.e. draw two cards; -1 if targets all enemy troops*/

	public enum TechEnum
	{
		GRAPE_SHOT,
		CALL,
		CHAIN_SHOT,
		DRAIN,
		BOOSTER,
		CANNON;
	}
	
	public Technique(TechEnum eff) 
	{
		super("" + eff, "Santi has to do this later");
		
		techEnum = eff;
		
		switch(eff)
		{
			case GRAPE_SHOT:
				tpCost = 6;
				numberOfTargets = -1;
				remainingTargets = numberOfTargets;
				break;
			case CHAIN_SHOT:
				tpCost = 8;
				numberOfTargets = 4;
				remainingTargets = numberOfTargets;
				break;
			case CANNON:
				tpCost = 4;
				numberOfTargets = -1;
				remainingTargets = numberOfTargets;
				break;
			case BOOSTER:
				tpCost = 3;
				numberOfTargets = 1;
				remainingTargets = numberOfTargets;
				break;
			case DRAIN:
				tpCost = 7;
				numberOfTargets = 1;
				remainingTargets = numberOfTargets;
				break;
			case CALL:
				tpCost = 4;
				numberOfTargets = 0;
				remainingTargets = numberOfTargets;
				break;
		}
	}
	
	public boolean canCast(int tp)
	{
		return tp >= tpCost;
	}
	
	/**
	 * 
	 * @param target - The troop to target
	 * @return - True if the spell is used up, false otherwise
	 */
	public boolean cast(Troop target)
	{	
		if(numberOfTargets == -1)
		{
			for(Entity[] row : Game.game.getBoard())
			{
				for(Entity e : row)
				{
					if(e instanceof Troop)
					{
						effect((Troop)e, techEnum);
					}
				}
			}
			return true;
		}
		else if(numberOfTargets == 0)
		{
			effect(target, techEnum);
			return true;
		}
		else
		{
			if(remainingTargets > 0)
			{
				effect(target, techEnum);
				remainingTargets--;
				return false;
			}
		}
		return true;
	}

	private void effect(Troop e, TechEnum eff) 
	{
		switch(eff)
		{
			case GRAPE_SHOT:
				if(e.teamColor != Game.game.getColor())
				{
					e.modify(-1, 0);
				}
				break;
				
			case CHAIN_SHOT:
				if(e.teamColor != Game.game.getColor())
				{
					e.modify(-2, 0);
					remainingTargets--;
				}
				break;
				
			case CANNON:
				if(e.teamColor != Game.game.getColor())
				{
					e.modify(-6, 0);
					remainingTargets--;
				}
				break;
				
			case BOOSTER:
				if(e.teamColor == Game.game.getColor())
				{
					e.modify(1, 1);
					remainingTargets--;
				}
				break;
				
			case DRAIN:
				e.modify(-4, 0);
				Game.game.getCommander().modify(4, 0);
				break;
				
			case CALL:
				Game.game.drawCard();
				Game.game.drawCard();
				break;
		}
	}
	
	public int getTpCost()
	{
		return tpCost;
	}
	
	public int getNumTargets()
	{
		return numberOfTargets;
	}
}
