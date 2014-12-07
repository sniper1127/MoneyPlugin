package com.github.sniper1127.MoneyPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;

public class listener implements Listener
{
	@EventHandler
	public void UpdataMessage(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		if((player.isOp()) && (!Main.isLatest))
		{
			PluginDescriptionFile pdfFile = Main.instance.getDescription();
			player.sendMessage(ChatColor.YELLOW + "[" + pdfFile.getName() + "]" + "新しいバージョンが出ています。アップデートをお勧めします。");
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
    {
		Player player = e.getPlayer();
		String name = player.getName();
		MoneyAPI api = MoneyAPI.getMoneyAPIByName(name);
		if(api == null)
		{
			api = new MoneyAPI(name);
		}
		MethodList.setScoreboard(player);
    }
}
