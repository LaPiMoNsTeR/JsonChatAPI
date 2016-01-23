package pf.lapimonster.JsonChatAPI;

import org.bukkit.plugin.java.JavaPlugin;

import pf.lapimonster.JsonChatAPI.utils.NMSUtils;

public class JsonChatAPI extends JavaPlugin 
{ 
	private static JsonChatAPI instance;
	
	private Class<?> iChatBaseComponentClass;
	private Class<?> chatSerializerClass;
	private Class<?> packetPlayOutChatClass;
	private Class<?> packetClass;
	private Class<?> titleClass;
	private Class<?> actionsClass;
	
	@Override
	public void onEnable() 
	{
		instance = this;
		
		try 
		{
			this.iChatBaseComponentClass = NMSUtils.getNMS("IChatBaseComponent");
			this.chatSerializerClass = NMSUtils.getNMS(this.iChatBaseComponentClass.getDeclaredClasses(), "ChatSerializer");
			this.packetPlayOutChatClass = NMSUtils.getNMS("PacketPlayOutChat");
			this.packetClass = NMSUtils.getNMS("Packet");
			this.titleClass = NMSUtils.getNMS("PacketPlayOutTitle");
			this.actionsClass = NMSUtils.getNMS("PacketPlayOutTitle$EnumTitleAction");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static JsonChatAPI getInstance()
	{
		return instance;
	}
	
	public Class<?> getIChatBaseComponentClass()
	{
		return this.iChatBaseComponentClass;
	}
	
	public Class<?> getChatSerializerClass()
	{
		return this.chatSerializerClass;
	}
	
	public Class<?> getPacketPlayOutChatClass()
	{
		return this.packetPlayOutChatClass;
	}
	
	public Class<?> getPacketClass()
	{
		return this.packetClass;
	}
	
	public Class<?> getTitleClass()
	{
		return this.titleClass;
	}
	
	public Class<?> getTitleActionClass()
	{
		return this.actionsClass;
	}
}
