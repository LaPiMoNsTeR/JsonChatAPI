package pf.lapimonster.tools;

public enum JsonFormat 
{
	OBFUSCATED("obfuscated"),
	BOLD("bold"),
	STRIKETHROUGH("strikethrough"),
	UNDERLINE("underline"),
	ITALIC("italic");
	
	private String name;
	
	private JsonFormat(String name) 
	{
		this.name = name;
	}
	
	public String work() 
	{ 
		return this.name; 
	}
}
