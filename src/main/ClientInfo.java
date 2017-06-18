package main;

import cards.Commander;

/**
 * Class that provides some more specificity to ClientInfo by including player
 * cp, ap, tp, commander and name
 * @author Srihari Subramanian
 */
public class ClientInfo {
	private static final long serialVersionUID = -3083097241395170738L;
	private int tag, cp, ap, tp;
	private Commander commander;
	private String name;

	/**
	 * Construct a SpecificClientInfo
	 * @param clientName Name of this client.
	 * @param clientCommander this client's commander
	 * @param ta tag of this client
	 * @param c cp of this client
	 * @param a ap of this client
	 * @param t tp of this client.
	 */
	public ClientInfo(String clientName, Commander clientCommander, int ta, int c, int a, int t) {
		tag = ta;
		name = clientName;
		commander = clientCommander;
		tag = ta;
		cp = c;
		ap = a;
		tp = t;
	}

	public int getTag() {
		return tag;
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
