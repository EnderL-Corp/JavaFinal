package cards;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import main.Game;
import main.Utilities;

/**
 * @author André, Luke
 */
public class Troop extends Entity
{
	protected boolean[] abilities; //in this order: {provoke, deflect, blast, range, mirror, void} 
	protected boolean deflectTime, mirrorTime;
	protected int currentApCost; //for the speed boost gear
	protected static ArrayList<Gear> gearArray = new ArrayList<Gear>(); 
	protected TroopEnum te;
	protected String rootName;

	/**
	 * Creates a troop, the most common unit in the game, placed
	 * onto the board from the Player's hand
	 * @param tag - The tag of the Troop
	 */
	public Troop(int tag) 
	{
		super(tag);
		abilities = new boolean[6];
		if(abilities[1] == true)
			deflectTime = true;
		else
			deflectTime = false;
		
		if(abilities[4] == true)
			mirrorTime = true;
		else
			mirrorTime = false;
		
		currentApCost = apCost;
	}
	
	/**
	 * @return ImageIcon - The path to both this Troop and its overlaying troop type.
	 */
	public ImageIcon getIcon() {
		if(te != null) {
			return Utilities.combineImages(rootName + ".png", name + ".png");
		}
		else
			return new ImageIcon("Sprites/" + rootName + ".png");
	}
	
	/**
	 * @param abs - The index of the ability (i.e. 1 for deflect, or 5 for void [see above])
	 * @return boolean - Whether or not the ability was added
	 */
	public boolean addAbilities(int abs)
	{
		if(abs == 1)
			deflectTime = true; // in order to reset the abilities each turn, you just have to addAbilites(1 or 4) at the beginning of the phase
		if(abs == 4)
			mirrorTime = true;
		
		if(abilities[abs] == false)
		{
			if((abilities[2] == true || abilities[3] == true) && (abs == 2 || abs == 3)) // makes sure you dont have blast and range
				return false;
			abilities[abs] = true;
			return true;
		}
		return false;
	}

	/**
	 * <code>canDeflect()</code> determines whether or not a Troop can deflect an attack.
	 * A troop can only deflect once per turn, so if the troop can deflect, it will,
	 * and its ability to deflect is set to false until next turn
	 * @return boolean - Whether or not the troop can deflect at the moment
	 */
	public boolean canDeflect()
	{
		if(deflectTime == true)
		{
			deflectTime = false;
			return true;
		}
		return false;
	}
	
	/**
	 * <code>canMirror()</code> determines whether or not a Troop can mirror an attack.
	 * A troop can only mirror once per turn, so if the troop can mirror, it will,
	 * and its ability to mirror is set to false until next turn
	 * @return boolean - Whether or not the troop can mirror at the moment
	 */
	public boolean canMirror()
	{
		if(mirrorTime == true)
		{
			mirrorTime = false;
			return true;
		}
		return false;
	}
	
	/**
	 * @param int - the ability to check for (i.e. 1 for deflect, or 5 for void [see above])
	 * @return boolean - Whether or not the troop has the specified ability
	 */
	public boolean hasAbility(int num)
	{
		return abilities[num];
	}
	
	/**
	 * <code>attack(defender)</code> attempts to make the Troop attack the defender. 
	 * The method checks to see if the defender is at most 1 tile away (unless the
	 * attacker has range) and if any troop adjacent to the attacker has provoke.
	 * If the attack is valid, this method then calls <code>dealDamage(defender)</code>
	 * or <code>blastAttack(defender)</code> if the attacker has blast.
	 * @param Entity - The Entity to Attack
	 */
	public void attack(Entity defender)
	{
		boolean canAttack = true;
		
		if((abilities[3] == false && abilities[2] == false) && (Math.abs(defender.getPosX() - xCoordinate) > 1 || Math.abs(defender.getPosY() - yCoordinate) > 1))
			return; //if not in range, don't attack (also cant attack self)
		
		for(int i = xCoordinate - 1; i <= xCoordinate + 1; i++)
		{
			for(int j = yCoordinate - 1; j <= yCoordinate + 1; j++)
			{
				if(Game.game.getEntityAt(i,j) != null && Game.game.getEntityAt(i,j) != this && Game.game.getEntityAt(i,j).hasAbility(0))
				{				
					canAttack = false; //checks to see if there is any provoke troops adjacent to attacker
				}
			}
		}

		if(canAttack == false) // if something had provoke...
		{
			if(Math.abs(defender.getPosX() - xCoordinate) <= 1 && Math.abs(defender.getPosY() - yCoordinate) <= 1 && defender.hasAbility(0))
			{  //if the defender is in range, and has provoke. attack it
				if(abilities[2]) //but if it has blast, also attack everything else in row/col up until void troop
				{
					blastAttack(defender);	
				}
				else if(abilities[0] || abilities[1] || abilities[3] || abilities[4])
				{
					dealDamage(defender);
				}
			}
		}
		else //if there is nothing with provoke, you can just attack 
		{
			if(abilities[2])  //if it has blast attack everything else in row/col up until void troop
			{
				blastAttack(defender);
			}
			else if(abilities[0] || abilities[1] || abilities[3] || abilities[4])
			{
				dealDamage(defender);
			}
		}
	}

	/**
	 * <code> kill(killed) </code> moves a specified "dead" entity to the graveyard.
	 * The code then moves any gear that troop possessed to the graveyard as well.
	 * @param Entity - The killed Entity
	 */
	public void kill(Entity killed) 
	{
		Game.game.addToGraveyard((Card)killed);
		Game.game.getBoard()[killed.xCoordinate][killed.yCoordinate] = null;
		
		for(int i = gearArray.size(); i > 0; i --)
		{
			if(gearArray.get(i) != null)
			{
				Game.game.addToGraveyard((Card)(new Gear(gearArray.get(i).getGearEnum())));
				gearArray.remove(i);
			}
		}
	}
	
	public int getCurrentApCost() {
		return currentApCost;
	}

	public void changeApCost(int ap) {
		currentApCost += ap;
	}
	
	public boolean[] getAbilities() {
		return abilities;
	}
	
	/**
	 * Places a Troop on the board at its position
	 * @param Troop - The Troop to place
	 * @param int - The amount of Creature Points the Player has
	 * @return int - The amount of Creature Points the Player has left
	 */
	public static int placeOnBoard(Troop placed, int cp)
	{
		if(cp < placed.getCpCost() || Game.game.getEntityAt(placed.getPosX(), placed.getPosY()) != null)
			return cp;
		Game.game.placeEntity(placed);
		Game.game.getHand().remove(placed);
		return cp - placed.getCpCost();
	}
	
	/**
	 * Adds gear onto a troop
	 * @param gear - The gear to add
	 */
	public static void equipGear(Gear gear)
	{
		gearArray.add(gear);
	}
	
	/**
	 *  <code> blastAttack(defender) </code> performs a "blast attack". First, it looks for where the defender 
	 *  is in relation to itself. If the defender is at a diagonal, it can't blast attack diagonally, and so will
	 *  either attack normally if defender is in range, or not attack at all if its not. For the actual blast
	 *  attack, it looks at whether the defender is in a different row or a different column as the attacker. Then,
	 *  It does damage to everything in that row/column (including those which are on the attackers side) starting 
	 *  from the attacker (but not including it), and going until it hits the end of the array or a void troop.
	 * @param defender
	 */
	public void blastAttack(Entity defender)
	{
		int rowChange = 0;
		int colChange = 0;
		if(defender.getPosX() > xCoordinate)
			rowChange = 1;
		if(defender.getPosX() < xCoordinate)
			rowChange = -1;
		if(defender.getPosY() > yCoordinate)
			colChange = 1;
		if(defender.getPosY() < yCoordinate)
			colChange = -1;
		//checking to see where in relation the defender is too attacker (direction wise)
		
		if(rowChange != 0 && colChange != 0) //this means that the defender is diagonal, and you cant blastAttack diagonal, so perform a regular attack
		{
			if(Math.abs(defender.getPosX() - xCoordinate) == 1 && Math.abs(defender.getPosY() - yCoordinate) == 1)
				dealDamage(defender); //if its in melee range, attack normally
			else
				return; //otherwise do nothing
		}
		
		if(rowChange == 1 || rowChange == -1)
		{
			for(int row = xCoordinate; row < 15 && row > -1; row += rowChange)
			{
				if(Game.game.getEntityAt(row, yCoordinate)!= null && Game.game.getEntityAt(row, yCoordinate)!= this)
				{
					if(Game.game.getEntityAt(row, yCoordinate).hasAbility(5) == false)
					{
						dealDamage(Game.game.getEntityAt(row, yCoordinate));
					}
					else
					{
						row = 15;
					}
				}
			}
		}
		else if(colChange == 1 || colChange == -1)
		{
			for(int col = yCoordinate; col < 15 && col > -1; col += colChange)
			{
				if(Game.game.getEntityAt(xCoordinate, col)!= null && Game.game.getEntityAt(xCoordinate, col)!= this)
				{
					if(Game.game.getEntityAt(xCoordinate, col).hasAbility(5) == false)
					{
						dealDamage(Game.game.getEntityAt(xCoordinate, col));
					}
					else
					{
						col = 15;
					}
				}
			}
		}
	}
	
	public String getTroopType()
	{
		return null;
	}
	
	public void updateDescription()
	{
		description = getTroopType() + " with: ";
		if(abilities[0])
			description += "provoke, ";
		if(abilities[1])
			description += "deflect, ";
		if(abilities[2])
			description += "blast, ";
		if(abilities[3])
			description += "range, ";
		if(abilities[4])
			description += "mirror, ";
	}
}

