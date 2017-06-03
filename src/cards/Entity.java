package cards;

public abstract class Entity extends Card
{
	protected int apCost;
	protected int cpCost;
	protected int attack;
	protected int health;
	protected int currentAttack;
	protected int currentHealth;
	
	public Entity(String nm, String desc, int ap, int cp, int atk, int hp)
	{
		super(nm, desc);
		apCost = ap;
		cpCost = cp;
		attack = atk;
		health = hp;
		currentAttack = atk;
		currentHealth = hp;
	}
	
	public abstract boolean[] getAbilities();
	
	public abstract boolean canDeflect();
	
	public abstract boolean canMirror();
	
	public abstract void kill();

	public void modify(int hp, int atk)
	{
		currentAttack += atk;
		currentHealth += hp;	
		if(getCurrentHealth() <= 0)
			kill();
	}
	
	public void dealDamage(Entity defender)
	{
		if(defender.getAbilities() != null)
		{
			if(defender.canMirror())
				currentHealth -= defender.getCurrentAttack() / 2;
			if(defender.canDeflect())
				return;
		}
		defender.modify(0, -attack);
	}
	
	public abstract void attack();
	
	public int getCurrentAttack()
	{ return currentAttack;	}
	
	public int getCurrentHealth()
	{ return currentHealth;	}
	
	public int getAttack()
	{ return attack; }
	
	public int getHealth()
	{ return health; }
	
	public int getCpCost()
	{ return cpCost; }
	
	public int getApCost()
	{ return apCost; }

}
