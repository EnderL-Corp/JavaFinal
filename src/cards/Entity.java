package cards;

public abstract class Entity extends Card
{
	protected int apCost;
	protected int cpCost;
	protected int attack;
	protected int health;
	protected int maxHealth;
	
	public Entity(String nm, String desc, int ap, int cp, int atk, int hp)
	{
		super(nm, desc);
		apCost = ap;
		cpCost = cp;
		attack = atk;
		health = hp;
		maxHealth = hp;
	}
	
	public abstract void kill();

	public abstract void modifyHealth(int val, Card originator);
	
	public int dealDamage()
	{
		return attack;
	}
}
