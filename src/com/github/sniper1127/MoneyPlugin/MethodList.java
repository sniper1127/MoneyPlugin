package com.github.sniper1127.MoneyPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class MethodList 
{
	public static void setScoreboard(Player player)
	{
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective objective = null;
		
		String name = player.getName();
		MoneyAPI api = MoneyAPI.getMoneyAPIByName(name);
		if(api == null)
		{
			api = new MoneyAPI(name);
		}
		int money = api.getMoney();
		Score score = null;
		
		if((Config.slottype != null) && (Config.slottype.equals(DisplaySlot.SIDEBAR)))
		{
			objective = board.registerNewObjective(ChatColor.DARK_GREEN + player.getName() , "dummy");
			objective.setDisplaySlot(Config.slottype);
			String space = null;
			if(money < 10)
			{
				space = "        ";
			}
			else if(money < 100)
			{
				space = "       ";
			}
			else if(money < 1000)
			{
				space = "      ";
			}
			else if(money < 10000)
			{
				space = "     ";
			}
			else if(money < 100000)
			{
				space = "    ";
			}
			else if(money < 1000000)
			{
				space = "   ";
			}
			else if(money < 10000000)
			{
				space = "  ";
			}
			score = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + space + money + " " + Config.name));
			score.setScore(9);
			player.setScoreboard(board);
		}
		else
		{
			board = manager.getNewScoreboard();
			objective = board.registerNewObjective(ChatColor.GOLD + Config.name, "dummy");
			objective.setDisplaySlot(Config.slottype);
			score = objective.getScore(player);
			score.setScore(money);
			for(Player play : Bukkit.getOnlinePlayers())
			{
				play.setScoreboard(board);
			}
		}
	}
}
