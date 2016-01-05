package pf.lapimonster;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pf.lapimonster.tools.JsonColor;
import pf.lapimonster.tools.JsonFormat;
import pf.lapimonster.tools.JsonPosition;
import pf.lapimonster.utils.JsonChatAPI.NMSUtils;

public class JsonMessage 
{
	private Object base;
	private Object u;
	private byte position;
	
	public JsonMessage(JsonPosition position) 
	{
		try 
		{
			this.base = JsonChatAPI.getInstance().getChatSerializerClass().getMethod("a", String.class).invoke(null, "{text: \"\"}");
			this.position = position.work();
		} 
		catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) 
		{
			e.printStackTrace();
		}
	}
	
	public JsonMessage text(String text) 
	{
		try 
		{
			this.u = JsonChatAPI.getInstance().getChatSerializerClass().getMethod("a", String.class).invoke(null, "{text: \""+text+"\"}");
			this.base.getClass().getMethod("addSibling", JsonChatAPI.getInstance().getIChatBaseComponentClass()).invoke(this.base, this.u);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) 
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public JsonMessage text(String text, JsonColor color) 
	{
		try 
		{
			this.u = JsonChatAPI.getInstance().getChatSerializerClass().getMethod("a", String.class).invoke(null, "{text: \""+text+"\", color:\""+color.work()+"\"}");
			this.base.getClass().getMethod("addSibling", JsonChatAPI.getInstance().getIChatBaseComponentClass()).invoke(this.base, this.u);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) 
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public JsonMessage text(String text, JsonFormat format) 
	{
		try 
		{
			this.u = JsonChatAPI.getInstance().getChatSerializerClass().getMethod("a", String.class).invoke(null, "{text: \""+text+"\", "+format.work()+": true}");
			this.base.getClass().getMethod("addSibling", JsonChatAPI.getInstance().getIChatBaseComponentClass()).invoke(this.base, this.u);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) 
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public JsonMessage text(String text, JsonFormat[] formats) 
	{
		String format = "";
		for(JsonFormat fs : formats) format += fs.work()+": true, ";
		format = format.substring(0, format.length()-2);
		
		try 
		{
			this.u = JsonChatAPI.getInstance().getChatSerializerClass().getMethod("a", String.class).invoke(null, "{text: \""+text+"\", "+format+"}");
			this.base.getClass().getMethod("addSibling", JsonChatAPI.getInstance().getIChatBaseComponentClass()).invoke(this.base, this.u);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) 
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public JsonMessage text(String text, JsonColor color, JsonFormat format) 
	{
		try 
		{
			this.u = JsonChatAPI.getInstance().getChatSerializerClass().getMethod("a", String.class).invoke(null, "{text: \""+text+"\", color: \""+color.toString()+"\", "+format.work()+": true}");
			this.base.getClass().getMethod("addSibling", JsonChatAPI.getInstance().getChatSerializerClass()).invoke(this.base, this.u);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) 
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public JsonMessage text(String text, JsonColor color, JsonFormat[] formats) 
	{
		String format = "";
		for(JsonFormat fs : formats) format += fs.work()+": true, ";
		format = format.substring(0, format.length()-2);
		
		try 
		{
			this.u = JsonChatAPI.getInstance().getChatSerializerClass().getMethod("a", String.class).invoke(null, "{text: \""+text+"\", "+format+", color: \""+color.work()+"\"}");
			this.base.getClass().getMethod("addSibling", JsonChatAPI.getInstance().getIChatBaseComponentClass()).invoke(this.base, this.u);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) 
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public JsonMessage extra(Object JsonExtra) 
	{
		try 
		{
			this.u = JsonChatAPI.getInstance().getChatSerializerClass().getMethod("a", String.class).invoke(null, JsonExtra);
			this.base.getClass().getMethod("addSibling", JsonChatAPI.getInstance().getIChatBaseComponentClass()).invoke(this.base, this.u);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) 
		{
			e.printStackTrace();
		}
		return this;
	}
	
	
	public void send(Player p) 
	{
		try 
		{
			Object packet = JsonChatAPI.getInstance().getPacketPlayOutChatClass().getConstructor(JsonChatAPI.getInstance().getIChatBaseComponentClass(), byte.class).newInstance(this.base, this.position);
			Object pConnection = NMSUtils.getNMSPlayer(p).getClass().getField("playerConnection").get(NMSUtils.getNMSPlayer(p));
			pConnection.getClass().getMethod("sendPacket", JsonChatAPI.getInstance().getPacketClass()).invoke(pConnection, packet);
		} 
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException
				| InvocationTargetException | NoSuchMethodException | InstantiationException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void sendToAll() 
	{
		for(Player p : Bukkit.getServer().getOnlinePlayers())
			send(p);
	}
	
}
