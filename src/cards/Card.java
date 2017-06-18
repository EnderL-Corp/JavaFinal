package cards;

import java.awt.Color;
import java.io.Serializable;

import main.Audio;
import main.Game;

/** 
 * @author Luke, André
 */
public abstract class Card implements Serializable			//DO NOT REMOVE SERIALIZABLE. THIS IS NEEDED FOR SENDING OVER NETWORK
{
	private static final long serialVersionUID = 1L;
	protected Color teamColor;
	protected String name, description;
	
	protected Audio fx;
	
	/**
	 * A card is the most basic object in the game. A card is anything from a troop
	 * to a spell to an amplifier. Every card has a name, a description, and a color
	 * for the "Team" it is on
	 * @param nm - The name of the card
	 * @param desc - The card's description
	 */
	public Card(String nm, String desc)
	{
		name = nm;
		description = desc;
		try {
			teamColor = Game.game.getColor();
		} catch(NullPointerException npe) {
			System.out.println(npe);
		}
	}
	
	/**
	 * Special call for <code> Troop.java </code>
	 */
	public Card()
	{
		return;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public abstract void updateDescription();
	
	public Color getTeamColor()
	{
		return teamColor;
	}
}
