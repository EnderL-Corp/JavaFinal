package cards;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> deck; 
	/*CP : 16; AP : 20; TP : 4; territory : 1
	Troops:
	Dragon - 2x
	Mech - 2x 
	Blaster Mech - 2x
	Ranger Mech - 1x
	Dummy Mech - 1x
	Android - 2x
	Deflector Android - 2x
	Dummy Android - 2x
	Blast Android - 1x
	Gear:
	Speed Boost - 1x
	Launcher - 2x
	Mirror - 2x
	Shield - 2x
	Techniques:
	Cannon - 2x
	Amplifiers:
	None
	 */
	
	private int cp, ap, tp, territory;
	
	public enum Decks {
		RAVAGER,
		SWARM,
		DJ;
	}
	
	public Deck(Decks deckEnum) {
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
			/*
			CP : 8; AP : 16; TP : 12; territory : 2
			Troops:
			Human - 2x
			Ranger Human - 1x
			Dummy Human - 2x
			Cyborg - 2x
			Deflector Cyborg - 1x
			Android - 2x
			Gear:
			Electromagnet - 1x
			Shield - 1x
			Techniques:
			Grape Shot - 2x
			Call - 1x
			Chain Shot - 1x
			Drain - 2x
			Cannon - 2x
			Amplifiers:
			Healing wave - 2x
			Short Circuit - 2x
			*/

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
			/*
			CP : 12; AP : 12; TP : 4; territory : 3
			Troops:
			Drone - 2x
			Human - 2x
			Deflector Human - 2x
			Dummy Human - 2x
			Cyborg - 2x
			Deflector Cyborg - 2x
			Ranger Cyborg - 2x
			Blaster Cyborg - 1x
			Gear:
			Launcher - 2x
			Electromagnet - 1x
			Mirror - 1x
			Techniques:
			Call - 1x
			Amplifiers:
			Dronesday Device - 2x
			Overdraw -  2x
			Healing wave - 2x
			*/
			
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
}
