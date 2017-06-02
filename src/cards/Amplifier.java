package cards;

public class Amplifier extends Structure
{
	protected String effect;
	
	public Amplifier(String nm, String desc, String eff) 
	{
		super(nm, desc);
		effect = eff;
	}

}
