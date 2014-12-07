package com.github.sniper1127.MoneyPlugin;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MoneyAPI 
{
	public MoneyAPI(String name)
	{
		FileConfiguration config = Main.instance.getConfig();
		this.name = name;
		if(!config.contains("Money." + name))
		{
			config.set("Money." + name, 0);
			Main.instance.saveConfig();
		}
		money = config.getInt("Money." + name);
		list.put(name, this);
	}
	public String getName()
	{
		return name;
	}
	public int getMoney()
	{
		return money;
	}
	public void setMoney(int money)
	{
		this.money = money;
		Player player = (Bukkit.getServer().getPlayerExact(name));
		if(player != null)
		{
			MethodList.setScoreboard(player);
		}
		Config.SaveMoney(name, money);
	}
	public void addMoney(int add)
	{
		money += add;
		if(money < 0)
		{
			money = 0;
		}
		Player player = (Bukkit.getServer().getPlayerExact(name));
		if(player != null)
		{
			MethodList.setScoreboard(player);
		}
		Config.SaveMoney(name, money);
	}
	public static MoneyAPI getMoneyAPIByName(String name)
	{
		return list.get(name);
	}
	public String name;
	public int money;
	public static HashMap<String,MoneyAPI> list = new HashMap<String,MoneyAPI>();
}
