package com.github.sniper1127.MoneyPlugin;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	public void onEnable()
	{
		instance = this;
		
		Updata.CheckUpdata();
		
		getCommand("MoneyPlugin").setExecutor(new command());
		
		getServer().getPluginManager().registerEvents(new listener(), this);
		
		Config.FirstCheck();
		
		PluginDescriptionFile pdfFile = getDescription();
		System.out.println((new StringBuilder("[")).append(pdfFile.getName()).append("] ").append(pdfFile.getName()).append(" v").append(pdfFile.getVersion()).append(" enabled!").toString());
	}
	public void onDisable()
	{
		PluginDescriptionFile pdfFile = getDescription();
		System.out.println((new StringBuilder("[")).append(pdfFile.getName()).append("] ").append(pdfFile.getName()).append(" v").append(pdfFile.getVersion()).append("disabled!").toString());
	}
	public static Main instance;
	public static boolean isLatest = true;
}
