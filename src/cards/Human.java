package cards;

public class Human extends Troop	
{
	public Human(int posX, int posY, int tag, TroopEnum enumName)
	{
		super("Human", enumName, posX, posY, tag, new boolean[]{false, false, false, false, false, false});
	}
}

