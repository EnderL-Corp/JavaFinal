package cards;

import java.io.Serializable;

public abstract class Card implements Serializable			//DO NOT REMOVE SERIALIZABLE. THIS IS NEEDED FOR SENDING OVER NETWORK
{
	protected String name, description;
	
	public Card(String nm, String desc)
	{
		name = nm;
		description = desc;
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
