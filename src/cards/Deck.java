package cards;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> deck; 
	
	private int cp, ap, tp, territory;
	public DeckEnum classType;
	
	public enum DeckEnum {
		RAVAGER,
		SWARM,
		DJ;
	}
	
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
			deck.add(new Gear("Speed Boost", null, null));
			deck.add(new Gear("Launcher", null, null));
			deck.add(new Gear("Launcher", null, null));
			deck.add(new Gear("Mirror", null, null));
			deck.add(new Gear("Mirror", null, null));
			deck.add(new Gear("Shield", null, null));
			deck.add(new Gear("Shield", null, null));
			deck.add(new Technique("Cannon", null, 0, 0, null));
			deck.add(new Technique("Cannon", null, 0, 0, null));
			
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
			deck.add(new Gear("Electromagnet", null, null));
			deck.add(new Gear("Shield", null, null));
			deck.add(new Technique("Grape Shot", null, 0, 0, null));
			deck.add(new Technique("Grape Shot", null, 0, 0, null));
			deck.add(new Technique("Call", null, 0, 0, null));
			deck.add(new Technique("Chain Shot", null, 0, 0, null));
			deck.add(new Technique("Drain", null, 0, 0, null));
			deck.add(new Technique("Cannon", null, 0, 0, null));
			deck.add(new Technique("Cannon", null, 0, 0, null));
			deck.add(new Technique("Drain", null, 0, 0, null));
			deck.add(new Amplifier("Healing Wave", null, null, 0));
			deck.add(new Amplifier("Healing Wave", null, null, 0));
			deck.add(new Amplifier("Short Circuit", null, null, 0));
			deck.add(new Amplifier("Short Circuit", null, null, 0));
			
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
			deck.add(new Gear("Launcher", null, null));
			deck.add(new Gear("Launcher", null, null));
			deck.add(new Gear("Electromagnet", null, null));
			deck.add(new Gear("Mirror", null, null));
			deck.add(new Technique("Call", null, 0, 0, null));
			deck.add(new Amplifier("Dronesday Device", null, null, 0));
			deck.add(new Amplifier("Dronesday Device", null, null, 0));
			deck.add(new Amplifier("Overdraw", null, null, 0));
			deck.add(new Amplifier("Overdraw", null, null, 0));
			deck.add(new Amplifier("Healing Wave", null, null, 0));
			deck.add(new Amplifier("Healing Wave", null, null, 0));
			
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
