package cards;

public class Android extends Troop
{
	public Android(int posX, int posY, int tag, TroopEnum enumName)
	{
		super("Android", enumName, posX, posY, tag, new boolean[]{false, false, false, false, false, false});
	}
}

