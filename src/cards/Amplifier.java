package cards;

import main.Game;

public class Amplifier extends Structure implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	protected AmpEnum currentEnum;
	protected int numberNeededForReq;
	
	public enum AmpEnum
	{
		DRONESDAY_DEVICE,
		OVERDRAW,
		HEALING_WAVE,
		SHORT_CIRCUIT,
		NONE;
	}
	
	public Amplifier(AmpEnum ampEnum) 
	{
		super("" + ampEnum, "Santi has to do this later");

		switch(ampEnum) 
		{
			case DRONESDAY_DEVICE:
			{
				numberNeededForReq = 1;
				currentEnum = ampEnum;
				break;
			}
			case OVERDRAW:
			{
				numberNeededForReq = 1;
				currentEnum = ampEnum;
				break;
			}
			case HEALING_WAVE:			
			{
				numberNeededForReq = 2;
				currentEnum = ampEnum;
				break;
			}
			case SHORT_CIRCUIT:		
			{
				numberNeededForReq = 2;
				currentEnum = ampEnum;
				break;
			}
			case NONE:
			{
				numberNeededForReq = 0;
				currentEnum = ampEnum;
				break;
			}
		}
		
		Game.game.updateAmpPanel(this, false);
	}
	
	public void effectBoard()
	{
		if(reqMet())
		{
			switch(currentEnum) 
			{
				case DRONESDAY_DEVICE:
				{
					boolean placed = false;
					while(placed == false)
					{
						placed = Game.game.placeEntity( (Entity)(new Drone((int)(Math.random() * 15), (int)(Math.random() * 15), Game.game.getTag(), TroopEnum.X)) );
					}
					break;
				}
				case OVERDRAW:
				{
					Game.game.drawCard();
					break;
				}
				case HEALING_WAVE:			
				{
					for(Entity[] row : Game.game.getBoard())
					{
						for(Entity e : row)
						{
							if(e.getTeamColor() == Game.game.getColor() && !(e instanceof Commander))
							{
								e.modify(1, 0);
							}
						}
					}
					break;
				}
				case SHORT_CIRCUIT:		
				{
					for(Entity[] row : Game.game.getBoard())
					{
						for(Entity e : row)
						{
							if(e.getTeamColor() != Game.game.getColor() && !(e instanceof Commander)) 
							{
								e.modify(-1, 0);
							}
						}
					}					
					break;
				}
			}
		}
	}

	public boolean reqMet()
	{
		int numberFound = 0;
		if(numberNeededForReq == 1)
			return true;
		else
		{
			for(int i = 0; i < 4; i++)
			{
				if(Game.game.getAmpAt(i).getName() == this.getName())
				{
					numberFound ++;
				}
			}
			if(numberFound >= 2)
			{
				return true;
			}
		}
		return false;
	}
	
	public void sacrifice()
	{
		Game.game.addToGraveyard((Card)this);
		Game.game.updateAmpPanel(null, true);
		Game.game.updateAmpPanel(new Amplifier(AmpEnum.NONE),  false);
	}
	
	public AmpEnum getAmpType()
	{
		return currentEnum;
	}
}
