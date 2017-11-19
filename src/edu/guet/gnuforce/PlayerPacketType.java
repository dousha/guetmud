package edu.guet.gnuforce;

public enum PlayerPacketType {

	HEARTBEAT("Heartbeat", 0),
	VELOCITY_CHANGE("Velocity change", 1),
	CHAT("Chat", 2);

	@Override
	public String toString(){
		return this._name;
	}

	private PlayerPacketType(String name, int code){
		this._code = code;
		this._name = name;
	}

	private PlayerPacketType(int code, String name){
		this._code = code;
		this._name = name;
	}

	private String _name;
	private int _code;
}
