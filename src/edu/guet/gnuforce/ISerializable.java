package edu.guet.gnuforce;

public interface ISerializable {
	public String serialize();
	public Object deserialize(String data);
}
