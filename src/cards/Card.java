package cards;

import java.awt.Color;
import java.io.Serializable;

import main.Game;

public abstract class Card implements Serializable			//DO NOT REMOVE SERIALIZABLE. THIS IS NEEDED FOR SENDING OVER NETWORK
{
	private static final long serialVersionUID = 1L;
	protected Color teamColor;
	protected String name, description;
	
	public Card(String nm, String desc)
	{
		name = nm;
		description = desc;
		teamColor = Game.game.getColor();
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
}
