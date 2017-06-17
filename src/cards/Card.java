package cards;

import java.awt.Color;
import java.io.Serializable;

import main.Audio;
import main.Game;

public abstract class Card implements Serializable			//DO NOT REMOVE SERIALIZABLE. THIS IS NEEDED FOR SENDING OVER NETWORK
{
	private static final long serialVersionUID = 1L;
	protected Color teamColor;
	protected String name, description;
	
	protected Audio fx;
	
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
	
	public Color getTeamColor()
	{
		return teamColor;
	}
}
