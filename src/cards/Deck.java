package cards;

import java.io.Serializable;
import java.util.ArrayList;

import cards.Amplifier.AmpEnum;
import cards.Gear.GearEnum;
import cards.Technique.TechEnum;

/**
 * Stores the deck of the game
 * @author Srihari Subramanian
 *
 */
public class Deck implements Serializable{
	private static final long serialVersionUID = -3276572470774611765L;

	private ArrayList<Card> deck; 
	
	private int cp, ap, tp, territory;
	public DeckEnum classType;
	
	public enum DeckEnum {
		RAVAGER,
		SWARM,
		DJ;
	}
	
	/**
	 * Creats a deck based on the selected DeckEnum
	 * @param deckEnum - The type of Deck to create
	 */
	public Deck(DeckEnum deckEnum) {
		classType = deckEnum;
		switch(deckEnum) {
		case RAVAGER:
			deck = new ArrayList<Card>();
			deck.add(new Dragon(0,0,0,null));
			deck.add(new Dragon(0,0,0,null));
			deck.add(new Mech(0,0,0,null));
			deck.add(new Mech(0,0,0,null));
			deck.add(new Mech(0,0,0,TroopEnum.BLASTER));
			deck.add(new Mech(0,0,0,TroopEnum.BLASTER));
			deck.add(new Mech(0,0,0,TroopEnum.RANGER));
			deck.add(new Mech(0,0,0,TroopEnum.DUMMY));
			deck.add(new Android(0,0,0,TroopEnum.DEFLECTOR));
			deck.add(new Android(0,0,0,TroopEnum.DEFLECTOR));
			deck.add(new Android(0,0,0,TroopEnum.DUMMY));
			deck.add(new Android(0,0,0,TroopEnum.DUMMY));
			deck.add(new Android(0,0,0,TroopEnum.BLASTER));
			deck.add(new Gear(GearEnum.SPEED_BOOST));
			deck.add(new Gear(GearEnum.LAUNCHER));
			deck.add(new Gear(GearEnum.LAUNCHER));
			deck.add(new Gear(GearEnum.MIRROR));
			deck.add(new Gear(GearEnum.MIRROR));
			deck.add(new Gear(GearEnum.SHIELD));
			deck.add(new Gear(GearEnum.SHIELD));
			deck.add(new Technique(TechEnum.CANNON));
			deck.add(new Technique(TechEnum.CANNON));
			
			cp = 16;
			ap = 20;
			tp = 4;
			territory = 1;
			
			break;
		case DJ:
			deck = new ArrayList<Card>();
			deck.add(new Human(0,0,0,null));
			deck.add(new Human(0,0,0,null));
			deck.add(new Human(0,0,0,TroopEnum.RANGER));
			deck.add(new Human(0,0,0,TroopEnum.RANGER));
			deck.add(new Human(0,0,0,TroopEnum.DUMMY));
			deck.add(new Cyborg(0,0,0,null));
			deck.add(new Cyborg(0,0,0,null));
			deck.add(new Cyborg(0,0,0,TroopEnum.DEFLECTOR));
			deck.add(new Android(0,0,0,null));
			deck.add(new Android(0,0,0,null));
			deck.add(new Gear(GearEnum.ELECTROMAGNET));
			deck.add(new Gear(GearEnum.SHIELD));
			deck.add(new Technique(TechEnum.GRAPE_SHOT));
			deck.add(new Technique(TechEnum.GRAPE_SHOT));
			deck.add(new Technique(TechEnum.CALL));
			deck.add(new Technique(TechEnum.CHAIN_SHOT));
			deck.add(new Technique(TechEnum.DRAIN));
			deck.add(new Technique(TechEnum.CANNON));
			deck.add(new Technique(TechEnum.CANNON));
			deck.add(new Technique(TechEnum.DRAIN));
			deck.add(new Amplifier(AmpEnum.HEALING_WAVE));
			deck.add(new Amplifier(AmpEnum.HEALING_WAVE));
			deck.add(new Amplifier(AmpEnum.SHORT_CIRCUIT));
			deck.add(new Amplifier(AmpEnum.SHORT_CIRCUIT));
			
			cp = 8;
			ap = 16;
			tp = 12;
			territory = 2;
		
			break;
		case SWARM:			
			deck = new ArrayList<Card>();
			deck.add(new Drone(0,0,0,null));
			deck.add(new Drone(0,0,0,null));
			deck.add(new Human(0,0,0,null));
			deck.add(new Human(0,0,0,null));
			deck.add(new Human(0,0,0,TroopEnum.DEFLECTOR));
			deck.add(new Human(0,0,0,TroopEnum.DEFLECTOR));
			deck.add(new Human(0,0,0,TroopEnum.DUMMY));
			deck.add(new Human(0,0,0,TroopEnum.DUMMY));
			deck.add(new Cyborg(0,0,0,null));
			deck.add(new Cyborg(0,0,0,null));
			deck.add(new Cyborg(0,0,0,TroopEnum.DEFLECTOR));
			deck.add(new Cyborg(0,0,0,TroopEnum.DEFLECTOR));
			deck.add(new Cyborg(0,0,0,TroopEnum.RANGER));
			deck.add(new Cyborg(0,0,0,TroopEnum.RANGER));
			deck.add(new Cyborg(0,0,0,TroopEnum.BLASTER));
			deck.add(new Gear(GearEnum.LAUNCHER));
			deck.add(new Gear(GearEnum.LAUNCHER));
			deck.add(new Gear(GearEnum.ELECTROMAGNET));
			deck.add(new Gear(GearEnum.MIRROR));
			deck.add(new Technique(TechEnum.CALL));
			deck.add(new Amplifier(AmpEnum.DRONESDAY_DEVICE));
			deck.add(new Amplifier(AmpEnum.DRONESDAY_DEVICE));
			deck.add(new Amplifier(AmpEnum.OVERDRAW));
			deck.add(new Amplifier(AmpEnum.OVERDRAW));
			deck.add(new Amplifier(AmpEnum.HEALING_WAVE));
			deck.add(new Amplifier(AmpEnum.HEALING_WAVE));
			
			cp = 12;
			ap = 12;
			tp = 4;
			territory = 3;
		
			break;
		}
	}
	
	public int getAP() {
		return ap;
	}
	public int getCP() {
		return cp;
	}
	public int getTP() {
		return tp;
	}
	public int getTerritory() {
		return territory;
	}
	public ArrayList<Card> getDeck() {
		return deck;
	}
	public DeckEnum getClassType() {
		return classType;
	}
}
