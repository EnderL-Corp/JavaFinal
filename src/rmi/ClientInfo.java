package rmi;

import cards.Commander;

public class ClientInfo implements java.io.Serializable {
	private static final long serialVersionUID = 3067743304887724584L;
	private int tag;
	
	public ClientInfo(int tag) {
		this.tag = tag;
	}

	public int getTag() {
		return tag;
	}	
}
