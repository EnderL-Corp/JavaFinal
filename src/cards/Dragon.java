package cards;

public class Dragon extends Troop
{
	public Dragon(int posX, int posY, int tag, TroopEnum enumName)
	{
		super("Dragon", enumName, posX, posY, tag, new boolean[]{false, false, true, false, false, false});
	}
}

