package cards;

public class Technique extends Card
{
	protected String effect;
	
	public Technique(String nm, String desc, String eff) 
	{
		super(nm, desc);
		effect = eff;
	}
}
