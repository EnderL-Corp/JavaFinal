package cards;

public abstract class Entity extends Card
{
	protected int apCost, cpCost, attack, health, currentAttack, currentHealth, xCoordinate, yCoordinate;
	protected final int TAG;
	
	public Entity(String nm, String desc, int ap, int cp, int atk, int hp, int startPosX, int startPosY, int tag) //for commander
	{
		super(nm, desc);
		apCost = ap;
		cpCost = cp;
		attack = atk;
		health = hp;
		currentAttack = atk;
		currentHealth = hp;
		xCoordinate = startPosX;
		yCoordinate = startPosY;
		TAG = tag;
	}

	public Entity(int tag) // for troop
	{
		super();
		TAG = tag;
	}
	
	public abstract boolean[] getAbilities();
	
	public abstract boolean hasAbility(int num);
	
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
	
	public abstract void attack(Entity defender);
	
	public int getCurrentAttack() {
		return currentAttack;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public int getAttack() {
		return attack; 
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getCpCost() {
		return cpCost;
	}
	
	public int getApCost() {
		return apCost;
	}
	
	public int getTag() {
		return TAG;
	}
	
	public int getPosX() {
		return xCoordinate;
	}
	
	public int getPosY() {
		return yCoordinate;
	}
}
