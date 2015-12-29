package pf.lapimonster.tools;

public enum JsonClickEventType 
{
	OPEN_URL,
	RUN_COMMAND,
	SUGGEST_COMMAND;
	
	public String work() 
	{ 
		return this.toString().toLowerCase(); 
	}
}
