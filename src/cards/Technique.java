package cards;

public class Technique extends Card
{
	protected String effect;
	protected int cpCost;
	protected int numberOfTargets; // 0 if doesnt target anything i.e. draw two cards; -1 if targets all enemy troops
	
	public Technique(String nm, String desc, int cp, int numTargets, String eff) 
	{
		super(nm, desc);
		effect = eff;
		cpCost = cp;
		numberOfTargets = numTargets;
	}
	
	public void cast()
	{
		if(numberOfTargets == -1)
		{}
			// effect everything
		else if(numberOfTargets == 0)
		{}	
			//just do whatever its suppose to do
		else
		{}	
			//does stuff n number of times
	}
}
