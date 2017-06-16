package cards;

import main.Game;

public class Amplifier extends Structure
{
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
						placed = Game.game.placeEntity(new Drone(0, 0, Game.game.getTag(), TroopEnum.X), (int)(Math.random() * 15), (int)(Math.random() * 15));
					}
					break;
				}
				case OVERDRAW:
				{

					break;
				}
				case HEALING_WAVE:			
				{

					break;
				}
				case SHORT_CIRCUIT:		
				{

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
		Game.game.addToGraveyard(this);
		Game.game.updateAmpPanel(null, true);
		Game.game.updateAmpPanel(new Amplifier(AmpEnum.NONE),  false);
	}
	
	public AmpEnum getAmpType()
	{
		return currentEnum;
	}
}
