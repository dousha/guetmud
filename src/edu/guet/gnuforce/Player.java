package edu.guet.gnuforce;

import java.net.InetAddress;

public class Player extends LivingEntity implements ISerializable {

	public Player(String uname){
		super(uname, (short) 0, 0, 0, 0, 0);
	}

	public int heartbeat(int beat){
		int delta = beat - _lastHeartbeat;
		_lastHeartbeat = beat;
		return delta;
	}

	private int _lastHeartbeat;

	@Override
	public String serialize() {
		return null;
	}

	@Override
	public Object deserialize(String data) {
		return null;
	}
}
