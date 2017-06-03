package cards;

public abstract class Card 
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
