package edu.guet.gnuforce.packet.player;

import java.net.InetAddress;

public class VelocityChange {

	public VelocityChange(InetAddress addr, byte direction, short map, int x, int y){
		_addr = addr;
		_direction = direction;
		_map = map;
		_x = x;
		_y = y;
	}

	private final InetAddress _addr;
	private final byte _direction;
	private final short _map;
	private final int _x, _y;
}
