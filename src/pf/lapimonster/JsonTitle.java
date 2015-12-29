package pf.lapimonster;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import pf.lapimonster.utils.NMSUtils;

public class JsonTitle 
{
	private String title = "";
	private ChatColor titleColor = ChatColor.WHITE;
	private String subtitle = "";
	private ChatColor subtitleColor = ChatColor.WHITE;
	private int fadeInTime = -1;
	private int stayTime = -1;
	private int fadeOutTime = -1;
	private boolean ticks = false;

	public JsonTitle(String title) 
	{
		this.title = title;
	}

	public JsonTitle(String title, String subtitle) 
	{
		this.title = title;
		this.subtitle = subtitle;
	}

	public JsonTitle(JsonTitle title) 
	{
		this.title = title.title;
		this.subtitle = title.subtitle;
		this.titleColor = title.titleColor;
		this.subtitleColor = title.subtitleColor;
		this.fadeInTime = title.fadeInTime;
		this.fadeOutTime = title.fadeOutTime;
		this.stayTime = title.stayTime;
		this.ticks = title.ticks;
	}

	public JsonTitle(String title, String subtitle, int fadeInTime, int stayTime, int fadeOutTime) 
	{
		this.title = title;
		this.subtitle = subtitle;
		this.fadeInTime = fadeInTime;
		this.stayTime = stayTime;
		this.fadeOutTime = fadeOutTime;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return this.title;
	}

	public void setSubtitle(String subtitle) 
	{
		this.subtitle = subtitle;
	}

	public String getSubtitle() 
	{
		return this.subtitle;
	}

	public void setTitleColor(ChatColor color) 
	{
		this.titleColor = color;
	}

	public void setSubtitleColor(ChatColor color) 
	{
		this.subtitleColor = color;
	}

	public void setFadeInTime(int time) 
	{
		this.fadeInTime = time;
	}

	public void setFadeOutTime(int time) 
	{
		this.fadeOutTime = time;
	}

	public void setStayTime(int time) 
	{
		this.stayTime = time;
	}

	public void ticks(boolean b)
	{
		this.ticks = b;
	}

	public void send(Player p) 
	{
		if (JsonChatAPI.getInstance().getTitleClass() != null) 
		{
			resetTitle(p);
			try 
			{
				Object pConnection = NMSUtils.getNMSPlayer(p).getClass().getField("playerConnection").get(NMSUtils.getNMSPlayer(p));
				
				Object[] actions = JsonChatAPI.getInstance().getTitleActionClass().getEnumConstants();
				Object packet = JsonChatAPI.getInstance().getTitleClass().getConstructor(JsonChatAPI.getInstance().getTitleActionClass(),
						JsonChatAPI.getInstance().getIChatBaseComponentClass(), Integer.TYPE, Integer.TYPE, Integer.TYPE)
							.newInstance(actions[2], null, fadeInTime * (ticks ? 1 : 20), stayTime * (ticks ? 1 : 20), fadeOutTime * (ticks ? 1 : 20));

				if(fadeInTime != -1 && fadeOutTime != -1 && stayTime != -1)
					pConnection.getClass().getMethod("sendPacket", JsonChatAPI.getInstance().getPacketClass()).invoke(pConnection, packet);

				Object serialized = JsonChatAPI.getInstance().getChatSerializerClass().getMethod("a", String.class)
										.invoke(null, "{text:'"+ChatColor.translateAlternateColorCodes('&', title)+"',color:"+titleColor.name().toLowerCase()+"}");

				packet = JsonChatAPI.getInstance().getTitleClass().getConstructor(JsonChatAPI.getInstance().getTitleActionClass(),
						JsonChatAPI.getInstance().getIChatBaseComponentClass()).newInstance(actions[0], serialized);
				
				pConnection.getClass().getMethod("sendPacket", JsonChatAPI.getInstance().getPacketClass()).invoke(pConnection, packet);
				if (this.subtitle != "") 
				{
					serialized = JsonChatAPI.getInstance().getChatSerializerClass().getMethod("a", String.class)
							.invoke(null, "{text:'"+ChatColor.translateAlternateColorCodes('&', subtitle)+"',color:"+subtitleColor.name().toLowerCase() + "}");
					packet = JsonChatAPI.getInstance().getTitleClass().getConstructor(
																						JsonChatAPI.getInstance().getTitleActionClass(), 
																						JsonChatAPI.getInstance().getIChatBaseComponentClass()).newInstance(actions[1],
																						serialized
																					);
					pConnection.getClass().getMethod("sendPacket", JsonChatAPI.getInstance().getPacketClass()).invoke(pConnection, packet);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void broadcast() 
	{
		for (Player p : Bukkit.getOnlinePlayers()) 
		{
			send(p);
		}
	}

	public void clearTitle(Player p) 
	{
		try 
		{
			Object pConnection = NMSUtils.getNMSPlayer(p).getClass().getField("playerConnection").get(NMSUtils.getNMSPlayer(p));
			Object[] actions = JsonChatAPI.getInstance().getTitleActionClass().getEnumConstants();
			Object packet = JsonChatAPI.getInstance().getTitleClass().getConstructor(JsonChatAPI.getInstance().getTitleActionClass(),
					JsonChatAPI.getInstance().getIChatBaseComponentClass()).newInstance(actions[3], null);
			pConnection.getClass().getMethod("sendPacket", JsonChatAPI.getInstance().getPacketClass()).invoke(pConnection, packet);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void resetTitle(Player p) 
	{
		try 
		{
			Object pConnection = NMSUtils.getNMSPlayer(p).getClass().getField("playerConnection").get(NMSUtils.getNMSPlayer(p));
			Object[] actions = JsonChatAPI.getInstance().getTitleActionClass().getEnumConstants();
			Object packet = JsonChatAPI.getInstance().getTitleClass().getConstructor(JsonChatAPI.getInstance().getTitleActionClass(),
					JsonChatAPI.getInstance().getIChatBaseComponentClass()).newInstance(actions[4], null);
			pConnection.getClass().getMethod("sendPacket", JsonChatAPI.getInstance().getPacketClass()).invoke(pConnection, packet);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
