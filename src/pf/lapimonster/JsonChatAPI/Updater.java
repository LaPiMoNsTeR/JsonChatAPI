package pf.lapimonster.JsonChatAPI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import pf.lapimonster.JsonChatAPI.utils.HTTPDownloadUtility;

public class Updater 
{
	private String version;
	
	public Updater() throws IOException 
	{
		this.version = JsonChatAPI.getInstance().getDescription().getVersion();
		
		URLConnection wV = new URL("http://lapimonster.ovh/plugins/"+JsonChatAPI.getInstance().getName()+"/version.txt").openConnection();
		
		final String v = IOUtils.toString(wV.getInputStream());
		
		if(version.equals(v) == false)
		{
			final File destination = new File(JsonChatAPI.getInstance().getDataFolder().getParentFile().getPath().replace(".jar", "")+"/");
			
			new BukkitRunnable() 
			{
				
				@Override
				public void run() 
				{
					try 
					{
						Bukkit.getServer().getLogger().info("[Updater] Nouvelle version disponible : "+v);
						Bukkit.getServer().getLogger().info("[Updater] Mise à jour en cours ...");
						
						HTTPDownloadUtility.downloadFile("http://lapimonster.ovh/plugins/"+JsonChatAPI.getInstance().getName()+"/"+JsonChatAPI.getInstance().getName()+".jar", destination.getAbsolutePath());
						
						Bukkit.getServer().getLogger().info("[Updater] Mise à jour terminé. (version "+v+")");
						Bukkit.getServer().getLogger().info("[Updater] Relancez le serveur.");
						
						Bukkit.getServer().shutdown();
					}
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			}.runTaskAsynchronously(JsonChatAPI.getInstance());
		}
		else Bukkit.getServer().getLogger().info("[Updater] Plugin à jour.");
	}
}
