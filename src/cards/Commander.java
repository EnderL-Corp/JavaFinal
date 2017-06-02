package cards;

public class Commander extends Entity
{
	private int classType;
	public Commander(String nm, String desc, int ap, int cp, int atk, int hp, int ct) {
		super(nm, desc, 2, -1, 1, 25); //Check these values
		classType = ct;
	}

	public void kill()
	{
		//end game
	}
	
	public void modifyHealth(int val, Card originator)
	{
		/*health += val;
	 	if (health > MAX_HEALTH)
	 		health = MAX_HEALTH;*/		
		health = health + val > maxHealth ? maxHealth : health + val;
	}
}
