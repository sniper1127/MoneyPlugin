package com.github.sniper1127.MoneyPlugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scoreboard.DisplaySlot;

public class Config 
{
	public static void FirstCheck()
	{
		FileConfiguration config = Main.instance.getConfig();
		if(!config.contains("Name"))
		{
			config.set("Name", "Gold");
			config.set("Slot", "side");
			Main.instance.saveConfig();
		}
		name = config.get("Name").toString();
		slot = config.get("Slot").toString();
		if(slot.equals("none"))
		{
			slottype = null;
		}
		if(slot.equals("list"))
		{
			slottype = DisplaySlot.PLAYER_LIST;
		}
		else if(slot.equals("side"))
		{
			slottype = DisplaySlot.SIDEBAR;
		}
		else if(slot.equals("below"))
		{
			slottype = DisplaySlot.BELOW_NAME;
		}
	}
	public static void SaveName(String nam)
	{
		name = nam;
		FileConfiguration config = Main.instance.getConfig();
		config.set("Name", name);
		Main.instance.saveConfig();
	}
	public static void SaveSlot(String slo)
	{
		slot = slo;
		FileConfiguration config = Main.instance.getConfig();
		config.set("Slot", slot);
		Main.instance.saveConfig();
		if(slot.equals("none"))
		{
			slottype = null;
		}
		if(slot.equals("list"))
		{
			slottype = DisplaySlot.PLAYER_LIST;
		}
		else if(slot.equals("side"))
		{
			slottype = DisplaySlot.SIDEBAR;
		}
		else if(slot.equals("below"))
		{
			slottype = DisplaySlot.BELOW_NAME;
		}
	}
	public static void SaveMoney(String name, int money)
	{
		FileConfiguration config = Main.instance.getConfig();
		config.set("Money." + name, money);
		Main.instance.saveConfig();
	}
	public static String name;
	public static String slot;
	public static DisplaySlot slottype;
}
