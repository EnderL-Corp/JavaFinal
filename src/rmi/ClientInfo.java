package rmi;

import java.io.Serializable;

public class ClientInfo implements Serializable{
	private static final long serialVersionUID = 675766158362600535L;
	private int tag, port;
	private String ip;
	
	public ClientInfo(int tag, String ip, int port) {
		this.tag = tag;
		this.port = port;
		this.ip = ip;
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
}
