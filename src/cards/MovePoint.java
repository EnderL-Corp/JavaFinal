package cards;

/**
 * @author Luke
 */
public class MovePoint extends Card {
	private int xPos, yPos;
	
	/**
	 * Creates a move point with a specified location. It is temporary and
	 * serves as a way to store just location in a Card form
	 * @param x - x position
	 * @param y - y position
	 */
	public MovePoint(int x, int y)
	{
		super("MovePoint", "A Temporary MovePoint");
		xPos = x;
		yPos = y;
	}
	
	public int getX()
	{
		return xPos;
	}
	
	public int getY()
	{
		return yPos;
	}
	
	public String toString()
	{
		return xPos + ", " + yPos;
		
	}

	@Override
	public void updateDescription() {
		
	}
}
