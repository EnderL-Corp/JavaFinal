package cards;

public abstract class Entity extends Card
{
	protected int apCost;
	protected int cpCost;
	protected int attack;
	protected int health;
	protected final int MAX_HEALTH;
	
	public Entity(String nm, String desc, int ap, int cp, int atk, int hp)
	{
		super(nm, desc);
		apCost = ap;
		cpCost = cp;
		attack = atk;
		health = hp;
		MAX_HEALTH = hp;
	}
	
	public abstract void kill();

	public abstract void modifyHealth(int val);
	
	public int dealDamage()
	{
		return attack;
	}
}
