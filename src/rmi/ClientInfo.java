package rmi;

import java.io.Serializable;

//TODO Delete unused class (may be used, but truly unneeded).
public class ClientInfo implements Serializable{
	private static final long serialVersionUID = 675766158362600535L;
	private int tag, port;
	private String ip, name;
	
	public ClientInfo(int tag, String ip, int port, String name) {
		this.tag = tag;
		this.port = port;
		this.ip = ip;
		this.name = name;
	}
	
	public int getTag() {
		return tag;
	}
	public int getPort() {
		return port;
	}
	public String getIP() {
		return ip;
	}
	public String getName() {
		return name;
	}
}
