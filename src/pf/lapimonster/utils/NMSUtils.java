package pf.lapimonster.utils;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NMSUtils 
{
	public static Object getNMSPlayer(Player p) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		return p.getClass().getMethod("getHandle").invoke(p);
	}
	
	
	public static Class<?> getNMS(String clazz) throws ClassNotFoundException
	{
		return Class.forName("net.minecraft.server."+getVersion()+"."+clazz);
	}
	
	public static Class<?> getNMS(Class<?>[] clazz, String sclazz)
	{
		for(Class<?> t : clazz)
			if(t.getSimpleName().equals(sclazz)) return t;
		return null;
	}
	
	private static String getVersion() 
	{
	    final String packageName = Bukkit.getServer().getClass().getPackage().getName();
	    return packageName.substring(packageName.lastIndexOf('.') + 1);
	}
}
