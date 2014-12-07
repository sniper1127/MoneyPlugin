package com.github.sniper1127.MoneyPlugin;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class command implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
	{
		if(args.length < 2)
		{
			return false;
		}
		if(args[0].equals("set"))
		{
			if(args[1].equals(Config.name))
			{
				sender.sendMessage(ChatColor.RED + "すでにお金の単位は " + ChatColor.YELLOW + args[1] + ChatColor.RED + " になっています");
				return true;
			}
			Config.SaveName(args[1]);
			sender.sendMessage(ChatColor.AQUA + "お金の単位を " + ChatColor.YELLOW + args[1] + ChatColor.AQUA + " に変更しました");
			for(Player player : Bukkit.getOnlinePlayers())
			{
				MethodList.setScoreboard(player);
			}
			return true;
		}
		else if(args[0].equals("slot"))
		{
			String aa = null;
			try
			{
				int i = Integer.parseInt(args[1]);
				if(i == 0)
				{
					aa = "none";
				}
				if(i == 1)
				{
					aa = "list";
				}
				else if(i == 2)
				{
					aa = "side";
				}
				else if(i == 3)
				{
					aa = "below";
				}
			}
			catch(NumberFormatException e)
			{
				if((args[1].equals("none")) || (args[1].equals("list")) || (args[1].equals("side")) || (args[1].equals("below")))
				{
					aa = args[1];
				}
			}
			if(aa == null)
			{
				return false;
			}
			String slot = null;
			if(aa.equals("none"))
			{
				slot = "なし";
			}
			if(aa.equals("list"))
			{
				slot = "リスト";
			}
			else if(aa.equals("side"))
			{
				slot = "サイド";
			}
			else if(aa.equals("below"))
			{
				slot = "プレイヤーネームの下";
			}
			if(aa.equals(Config.slot))
			{
				sender.sendMessage(ChatColor.RED + "すでにお金の表示場所は " + ChatColor.YELLOW + slot + ChatColor.RED + " になっています");
				return true;
			}
			Config.SaveSlot(aa);
			sender.sendMessage(ChatColor.AQUA + "お金の表示場所を " + ChatColor.YELLOW + slot + ChatColor.AQUA + " に変更しました");
			for(Player player : Bukkit.getOnlinePlayers())
			{
				MethodList.setScoreboard(player);
			}
			return true;
		}
		else if(args[0].equals("add"))
		{
			if(args.length < 3)
			{
				return false;
			}
			MoneyAPI api = MoneyAPI.getMoneyAPIByName(args[1]);
			if(api == null)
			{
				api = new MoneyAPI(args[1]);
			}
			int money = 0;
			try
			{
				money = Integer.parseInt(args[2]);
			}
			catch(NumberFormatException e)
			{
				return false;
			}
			api.addMoney(money);
			sender.sendMessage(ChatColor.YELLOW + args[1] + ChatColor.AQUA + " というプレイヤーのお金を " + ChatColor.YELLOW + money + " " + Config.name + ChatColor.AQUA + " 追加しました");
			for(Player player : Bukkit.getOnlinePlayers())
			{
				MethodList.setScoreboard(player);
			}
			return true;
		}
		return false;
	}
}
