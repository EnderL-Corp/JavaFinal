package cards;

public class Technique extends Card
{
	protected String effect;
	protected int tpCost;
	protected int numberOfTargets; // 0 if doesnt target anything i.e. draw two cards; -1 if targets all enemy troops
	protected int remainingTargets;
	
	public Technique(String nm, String desc, int tp, int numTargets, String eff) 
	{
		super(nm, desc);
		effect = eff;
		tpCost = tp;
		numberOfTargets = numTargets;
		remainingTargets = numTargets;
	}
	
	public void cast()
	{
		if(numberOfTargets == -1)
		{} // effect everything
		else if(numberOfTargets == 0)
		{} //just do whatever its suppose to do
		else
		{
			if(remainingTargets > 0)
			{
			//perform action
			remainingTargets --;
			cast(); // yay recursion  (going to have to use an action listener to get targets)
			}
			remainingTargets = numberOfTargets;
		} //does stuff n number of times
	}
}
