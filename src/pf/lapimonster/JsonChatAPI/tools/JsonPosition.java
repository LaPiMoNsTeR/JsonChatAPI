package pf.lapimonster.JsonChatAPI.tools;

public enum JsonPosition 
{

	CHAT((byte) 0),
	SYSTEM_MESSAGE((byte) 1),
	ACTION_BAR((byte) 2);
	
	private byte position;
	
	private JsonPosition(byte position) 
	{
		this.position = position;
	}
	
	public byte work() 
	{ 
		return this.position; 
	}
	
}
