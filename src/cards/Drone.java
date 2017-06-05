package cards;

public class Drone extends Troop
{
	public Drone(int posX, int posY, int tag, TroopEnum enumName)
	{
		super("Drone", enumName, posX, posY, tag, new boolean[]{false, false, false, false, false, false});
	}
}
