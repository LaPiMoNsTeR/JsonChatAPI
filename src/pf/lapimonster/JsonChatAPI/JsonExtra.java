package pf.lapimonster.JsonChatAPI;

import java.lang.reflect.InvocationTargetException;

import pf.lapimonster.JsonChatAPI.tools.JsonClickEventType;
import pf.lapimonster.JsonChatAPI.tools.JsonColor;
import pf.lapimonster.JsonChatAPI.tools.JsonFormat;
import pf.lapimonster.JsonChatAPI.tools.JsonHoverEventType;


public class JsonExtra 
{

	private String extra;
	
	public JsonExtra(String text) 
	{
		this.extra = "text: \""+text+"\", ";
	}
	
	public JsonExtra(String text, JsonColor color) 
	{
		this.extra = "text: \""+text+"\", color: \""+color.work()+"\", ";
	}
	
	public JsonExtra(String text, JsonFormat format) 
	{
		this.extra = "text: \""+text+"\", "+format.work()+": true, ";
	}
	
	public JsonExtra(String text, JsonFormat[] formats) 
	{
		String format = "";
		for(JsonFormat fs : formats) format += fs.work()+": true, ";
		this.extra = "text: \""+text+"\", "+format+"";
	}
	
	public JsonExtra(String text, JsonColor color, JsonFormat format) 
	{
		this.extra = "text: \""+text+"\", color: \""+color.work()+"\", "+format.work()+": true, ";
	}
	
	public JsonExtra(String text, JsonColor color, JsonFormat[] formats) 
	{
		String format = "";
		for(JsonFormat fs : formats) format += fs.work()+": true, ";
		this.extra = "text: \""+text+"\", color:\""+color.work()+"\", "+format+"";
	}
	
	
	
	public JsonExtra hover(JsonHoverEventType jhe, String value) 
	{
		this.extra += "hoverEvent: {action:\""+jhe.work()+"\", value:\""+value+"\"}, ";
		return this;
	}
	
	public JsonExtra click(JsonClickEventType jce, String value) 
	{
		this.extra += "clickEvent: {action:\""+jce.work()+"\", value:\""+value+"\"}, ";
		return this;
	}
	
	public Object work() 
	{
		this.extra = this.extra.substring(0, this.extra.length()-2);
		String extra = "{text: \"\", extra: [{"+this.extra+"}]}";
		
		Object cbc = null;
		try 
		{
			cbc = JsonChatAPI.getInstance().getChatSerializerClass().getMethod("a", String.class).invoke(null, extra);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) 
		{
			e.printStackTrace();
		}
		return cbc;
	}
	
}
