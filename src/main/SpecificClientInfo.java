package main;

import cards.Commander;

public class SpecificClientInfo extends rmi.ClientInfo {
	private static final long serialVersionUID = -3083097241395170738L;
	private int tag, cp, ap, tp;
	private Commander commander;
	private String name;
	
	public SpecificClientInfo(String clientName, Commander clientCommander, int ta, int c, int a, int t) {
		super(ta);
		name = clientName;
		commander = clientCommander;
		tag = ta;
		cp = c;
		ap = a;
		tp = t;
	}
	
	public int getCP() {
		return cp;
	}

	public int getAP() {
		return ap;
	}

	public int getTP() {
		return tp;
	}
	
	public Commander getCommander() {
		return commander;
	}
	
	public String getName() {
		return name;
	}

}
