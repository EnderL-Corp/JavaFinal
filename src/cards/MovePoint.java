package cards;

public class MovePoint extends Card {
	private int xPos, yPos;
	
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
}
