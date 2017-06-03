package cards;

public class Amplifier extends Structure
{
	protected String effect;
	protected int numberNeededForReq;
	
	public Amplifier(String nm, String desc, String eff, int numNeeded) 
	{
		super(nm, desc);
		effect = eff;
		numberNeededForReq = numNeeded;
	}
	
	public void effectBoard()
	{
		if(reqMet())
		{} //does effect
	}

	public boolean reqMet()
	{
		if(numberNeededForReq == 1)
			return true;
		else
		{
			//search other amplifiers for another one with same effect
			// if its there return true
		}
		return false;
	}
	
	public void sacrifice()
	{
		//put in graveyard
	}
}
