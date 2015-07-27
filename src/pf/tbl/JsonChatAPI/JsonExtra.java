package pf.tbl.JsonChatAPI;

// ----- for 1.8
//import net.minecraft.server.v1_8_R1.IChatBaseComponent;
//import net.minecraft.server.v1_8_R1.IChatBaseComponent.ChatSerializer;

// ----- for 1.8.3
//import net.minecraft.server.v1_8_R2.IChatBaseComponent;
//import net.minecraft.server.v1_8_R2.IChatBaseComponent.ChatSerializer;

// ----- for 1.8.4+
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;

import pf.tbl.JsonChatAPI.outils.JsonClickEventType;
import pf.tbl.JsonChatAPI.outils.JsonColor;
import pf.tbl.JsonChatAPI.outils.JsonFormat;
import pf.tbl.JsonChatAPI.outils.JsonHoverEventType;


public class JsonExtra {

	String extra;
	IChatBaseComponent cbc;
	
	public JsonExtra(String text) {
		this.extra = "text: \""+text+"\", ";
	}
	
	public JsonExtra(String text, JsonColor color) {
		this.extra = "text: \""+text+"\", color: \""+color.work()+"\", ";
	}
	
	public JsonExtra(String text, JsonFormat format) {
		this.extra = "text: \""+text+"\", "+format.work()+": true, ";
	}
	
	public JsonExtra(String text, JsonFormat[] formats) {
		String format = "";
		for(JsonFormat fs : formats) format += fs.work()+": true, ";
		this.extra = "text: \""+text+"\", "+format+"";
	}
	
	public JsonExtra(String text, JsonColor color, JsonFormat format) {
		this.extra = "text: \""+text+"\", color: \""+color.work()+"\", "+format.work()+": true, ";
	}
	
	public JsonExtra(String text, JsonColor color, JsonFormat[] formats) {
		String format = "";
		for(JsonFormat fs : formats) format += fs.work()+": true, ";
		this.extra = "text: \""+text+"\", color:\""+color.work()+"\", "+format+"";
	}
	
	
	
	public JsonExtra hover(JsonHoverEventType jhe, String value) {
		this.extra += "hoverEvent: {action:\""+jhe.work()+"\", value:\""+value+"\"}, ";
		return this;
	}
	
	public JsonExtra click(JsonClickEventType jce, String value) {
		this.extra += "clickEvent: {action:\""+jce.work()+"\", value:\""+value+"\"}, ";
		return this;
	}
	
	public IChatBaseComponent work() {
		this.extra = this.extra.substring(0, this.extra.length()-2);
		String extra = "{text: \"\", extra: [{"+this.extra+"}]}";
		this.cbc = ChatSerializer.a(extra);
		return this.cbc;
	}
	
}
