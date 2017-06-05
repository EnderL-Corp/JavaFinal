package cards;

public class Mech extends Troop
{
	public Mech(int posX, int posY, int tag, TroopEnum enumName)
	{
		super("Mech", enumName, posX, posY, tag, new boolean[]{false, false, false, false, false, false});
	}
}
