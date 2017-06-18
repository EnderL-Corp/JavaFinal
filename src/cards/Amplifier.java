package cards;

import main.Game;

/**
 * @author Andr�
 */
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
	
	/**
	 * An Amplifier gives a boost to your team in various different ways. These ways are determined AmpEnum
	 * @param ampEnum - ampEnum determines the type of the amplifier from the ampEnum list.
	 */
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
	
	/**
	 * <code> effectBoard() </code> is how the Amplifier effects the game. When the method is called, it is first checked as to
	 *  whether the required number of the amplifier exists, since some require 2 to be placed. The method
	 *  then loops through the different AmpEnums and does the appropriate action
	 */
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
						placed = Game.game.placeEntity((Entity)(new Drone((int)(Math.random() * 15), (int)(Math.random() * 15), Game.game.getTag(), TroopEnum.X)) );
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
				default:
				{
					break;
				}
			}
		}
	}

	/**
	 * <code> reqMet() </code> Determines whether or not the amplifier has the correct amount of it required
	 * to run
	 * @return True or False - The Amplifier has the correct number to run
	 */
	public boolean reqMet()
	{
		int numberFound = 0;
		if(numberNeededForReq == 1)
			return true;
		else
		{
			for(int i = 0; i < 5; i++)
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
	
	/**
	 * <code> sacrifice() </code> moves the selected amplifier to the Graveyard and then updates the Amplifier Panel
	 * to hold an empty Amplifier (For selection purposes)
	 */
	public void sacrifice()
	{
		Game.game.addToGraveyard((Card)this);
		Game.game.updateAmpPanel(null, true);
		Game.game.updateAmpPanel(new Amplifier(AmpEnum.NONE),  false);
	}
	
	/** 
	 * @return AmpEnum - The type of Amplifer
	 */
	public AmpEnum getAmpType()
	{
		return currentEnum;
	}
}
