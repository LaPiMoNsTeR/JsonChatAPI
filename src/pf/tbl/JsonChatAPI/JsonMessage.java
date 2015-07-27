package pf.tbl.JsonChatAPI;

// ----- for 1.8
//import net.minecraft.server.v1_8_R1.IChatBaseComponent;
//import net.minecraft.server.v1_8_R1.IChatBaseComponent.ChatSerializer;
//import net.minecraft.server.v1_8_R1.PacketPlayOutChat;

// ----- for 1.8.3
//import net.minecraft.server.v1_8_R2.IChatBaseComponent;
//import net.minecraft.server.v1_8_R2.IChatBaseComponent.ChatSerializer;
//import net.minecraft.server.v1_8_R2.PacketPlayOutChat;

// ----- for 1.8.4+
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import pf.tbl.JsonChatAPI.outils.JsonColor;
import pf.tbl.JsonChatAPI.outils.JsonFormat;
import pf.tbl.JsonChatAPI.outils.JsonPosition;

public class JsonMessage {

	IChatBaseComponent cbc;
	IChatBaseComponent i;
	byte position;
	
	public JsonMessage(JsonPosition position) {
		this.cbc = ChatSerializer.a("{text: \"\"}");
		this.position = position.work();
	}
	
	public JsonMessage text(String text) {
		i = ChatSerializer.a("{text: \""+text+"\"}");
		cbc.addSibling(i);
		return this;
	}
	
	public JsonMessage text(String text, JsonColor color) {
		i = ChatSerializer.a("{text: \""+text+"\", color:\""+color.work()+"\"}");
		cbc.addSibling(i);
		return this;
	}
	
	public JsonMessage text(String text, JsonFormat format) {
		this.cbc.addSibling(ChatSerializer.a("{text: \""+text+"\", "+format.work()+": true}"));
		return this;
	}
	
	public JsonMessage text(String text, JsonFormat[] formats) {
		String format = "";
		for(JsonFormat fs : formats) format += fs.work()+": true, ";
		format = format.substring(0, format.length()-2);
		this.cbc.addSibling(ChatSerializer.a("{text: \""+text+"\", "+format+"}"));
		return this;
	}
	
	public JsonMessage text(String text, JsonColor color, JsonFormat format) {
		this.cbc.addSibling(ChatSerializer.a("{text: \""+text+"\", color: \""+color.toString()+"\", "+format.work()+": true}"));
		return this;
	}
	
	public JsonMessage text(String text, JsonColor color, JsonFormat[] formats) {
		String format = "";
		for(JsonFormat fs : formats) format += fs.work()+": true, ";
		format = format.substring(0, format.length()-2);
		String sibling = "{text: \""+text+"\", "+format+", color: \""+color.work()+"\"}";
		Bukkit.getServer().broadcastMessage(sibling);
		this.cbc.addSibling(ChatSerializer.a(sibling));
		return this;
	}
	
	public JsonMessage extra(IChatBaseComponent JsonExtra) {
		this.cbc.addSibling(JsonExtra);
		return this;
	}
	
	
	public void send(Player p) {
		PacketPlayOutChat packet = new PacketPlayOutChat(cbc, position);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}
	
	public void sendToAll() {
		PacketPlayOutChat packet = new PacketPlayOutChat(cbc, position);
		for(Player p : Bukkit.getServer().getOnlinePlayers()) ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}
	
	
	
	IChatBaseComponent getIChatBaseComment() { return this.cbc; }

}
