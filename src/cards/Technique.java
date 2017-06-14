package cards;

import main.Game;

public class Technique extends Card
{
	protected String eff;
	protected int tpCost, numberOfTargets/* 0 if doesn't target anything i.e. draw two cards; -1 if targets all enemy troops*/, remainingTargets;

	
	public Technique(String nm, String desc, int tp, int numTargets, String eff) 
	{
		super(nm, desc);
		tpCost = tp;
		numberOfTargets = numTargets;
		remainingTargets = numTargets;
	}
	
	public void cast(Entity target)
	{
		if(numberOfTargets == -1)
		{
			for(Entity[] row : Game.game.getBoard())
			{
				for(Entity e : row)
				{
					effect(e, name);
				}
			}
		}
		else if(numberOfTargets == 0)
		{
			effect(null, name);
		}
		else
		{
			if(remainingTargets > 0)
			{
				effect(target, name);
			}
			remainingTargets = numberOfTargets;
		} //Only this case still needs work
	}

	private void effect(Entity e, String eff) 
	{
		switch(eff)
		{
			case "GrapeShot":
				if(e.teamColor != Game.game.getColor())
				{
					e.modify(-1, 0);
				}
				break;
				
			case "ChainShot":
				if(e.teamColor != Game.game.getColor())
				{
					e.modify(-2, 0);
					remainingTargets--;
				}
				break;
				
			case "Cannon":
				if(e.teamColor != Game.game.getColor())
				{
					e.modify(-6, 0);
					remainingTargets--;
				}
				break;
				
			case "Booster":
				if(e.teamColor == Game.game.getColor())
				{
					e.modify(1, 1);
					remainingTargets--;
				}
				break;
				
			case "Drain":
				e.modify(-4, 0);
				Game.game.getCommander().modify(4, 0);
				break;
				
			case "Call":
				Game.game.drawCard();
				Game.game.drawCard();
				break;
		}
	}
}
