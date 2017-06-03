package cards;

public abstract class Entity extends Card
{
	protected int apCost, cpCost, attack, health, currentAttack, currentHealth, xCoordinate, yCoordinate;
	protected final int TAG;
	
	public Entity(String nm, String desc, int ap, int cp, int atk, int hp, int startPosX, int startPosY, int tag)
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

	public Entity(String troopType, TroopEnum enumName, int startPosX, int startPosY, int tag)
	{
		super("" + enumName + troopType, "Who even reads this anyway?");
		xCoordinate = startPosX;
		yCoordinate = startPosY;
		TAG = tag;
		
		if(troopType == "Drone")
		{
			if(enumName == TroopEnum.X)
			{
				apCost = 1;
				cpCost = 1;
				attack = 1;
				health = 2;
				currentAttack = attack;
				currentHealth = health;
			}
			else
			{
				apCost = 1;
				cpCost = 1;
				attack = 1;
				health = 1;
				currentAttack = attack;
				currentHealth = health;
			}
		}
		
		else if(troopType == "Human")
		{
			if(enumName == TroopEnum.RANGER)
			{
				apCost = 3;
				cpCost = 3;
				attack = 2;
				health = 2;
				currentAttack = attack;
				currentHealth = health;
			}
			else if(enumName == TroopEnum.DEFLECTOR)
			{
				apCost = 4;
				cpCost = 3;
				attack = 3;
				health = 1;
				currentAttack = attack;
				currentHealth = health;
			}
			else if(enumName == TroopEnum.DUMMY)
			{
				apCost = 3;
				cpCost = 3;
				attack = 0;
				health = 4;
				currentAttack = attack;
				currentHealth = health;
			}
			else
			{
				apCost = 2;
				cpCost = 2;
				attack = 2;
				health = 2;
				currentAttack = attack;
				currentHealth = health;
			}

		}
		
		else if(troopType == "Cyborg")
		{
			if(enumName == TroopEnum.RANGER)
			{
				apCost = 5;
				cpCost = 5;
				attack = 3;
				health = 3;
				currentAttack = attack;
				currentHealth = health;
			}
			else if(enumName == TroopEnum.DEFLECTOR)
			{
				apCost = 6;
				cpCost = 6;
				attack = 4;
				health = 2;
				currentAttack = attack;
				currentHealth = health;
			}
			else if(enumName == TroopEnum.BLASTER)
			{
				apCost = 5;
				cpCost = 6;
				attack = 2;
				health = 4;
				currentAttack = attack;
				currentHealth = health;
			}
			else
			{
				apCost = 4;
				cpCost = 4;
				attack = 3;
				health = 3;
				currentAttack = attack;
				currentHealth = health;
			}
		}	
		
		else if(troopType == "Android")
		{
			if(enumName == TroopEnum.BLASTER)
			{
				apCost = 6;
				cpCost = 6;
				attack = 3;
				health = 5;
				currentAttack = attack;
				currentHealth = health;
			}
			else if(enumName == TroopEnum.DEFLECTOR)
			{
				apCost = 6;
				cpCost = 5;
				attack = 5;
				health = 3;
				currentAttack = attack;
				currentHealth = health;
			}
			else if(enumName == TroopEnum.DUMMY)
			{
				apCost = 7;
				cpCost = 5;
				attack = 1;
				health = 7;
				currentAttack = attack;
				currentHealth = health;
			}
			else
			{
				apCost = 5;
				cpCost = 4;
				attack = 4;
				health = 4;
				currentAttack = attack;
				currentHealth = health;
			}

		}
		
		else if(troopType == "Mech")
		{
			if(enumName == TroopEnum.BLASTER)
			{
				apCost = 6;
				cpCost = 7;
				attack = 4;
				health = 6;
				currentAttack = attack;
				currentHealth = health;
			}
			else if(enumName == TroopEnum.RANGER)
			{
				apCost = 6;
				cpCost = 6;
				attack = 5;
				health = 5;
				currentAttack = attack;
				currentHealth = health;
			}
			else if(enumName == TroopEnum.DUMMY)
			{
				apCost = 8;
				cpCost = 6;
				attack = 2;
				health = 8;
				currentAttack = attack;
				currentHealth = health;
			}
			else
			{
				apCost = 5;
				cpCost = 5;
				attack = 5;
				health = 5;
				currentAttack = attack;
				currentHealth = health;
			}
		}
		
		else
		{
			apCost = 10;
			cpCost = 15;
			attack = 10;
			health = 10;
			currentAttack = attack;
			currentHealth = health;
		}

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
