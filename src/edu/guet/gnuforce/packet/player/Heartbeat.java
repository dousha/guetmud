package edu.guet.gnuforce.packet.player;

import java.net.InetAddress;

public class Heartbeat {

	public Heartbeat(InetAddress addr, int number){
		_addr = addr;
		_number = number;
	}

	public int getNumber(){
		return _number;
	}

	private final InetAddress _addr;
	private final int _number;
}
