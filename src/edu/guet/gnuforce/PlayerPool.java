package edu.guet.gnuforce;

import java.net.InetAddress;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerPool {

	PlayerPool(Cylinders cylinder) {
		_cylinder = cylinder;
	}

	public void refresh(){

	}

	public boolean login(InetAddress addr, String uname, String pwd){
		return false;
	}

	private static ConcurrentHashMap<InetAddress, Player> _pool = new ConcurrentHashMap<>();
	private static Cylinders _cylinder;
}
