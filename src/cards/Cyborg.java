package cards;

public class Cyborg extends Troop
{
	public Cyborg(int posX, int posY, int tag, TroopEnum enumName)
	{
		super("Cyborg", enumName, posX, posY, tag, new boolean[]{false, false, false, false, false, false});
	}
}
