package edu.guet.gnuforce;

public class LivingEntity {

	protected LivingEntity(String name, short map, int x, int y, int vx, int vy){
		_name = name;
		_map = map;
		_x = x;
		_y = y;
		_vx = vx;
		_vy = vy;
	}

	private String _name;
	private short _map;
	private int _x, _y, _vx, _vy;
}
