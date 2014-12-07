package com.github.sniper1127.MoneyPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;

public class Updata
{
	public static void CheckUpdata()
	{
		String urlString = "http://sniper1127.webcrow.jp/file/versions.txt";
		try 
		{
			URL url = new URL(urlString);
			InputStream strm = url.openStream();
			
			PluginDescriptionFile pdfFile = Main.instance.getDescription();
			String name = pdfFile.getName() + "-" + Bukkit.getVersion().split("MC: ")[1].split("\\)")[0];
			int ThisVersion = getVersion(pdfFile.getVersion());
			
			InputStreamReader in = new InputStreamReader(strm);
			BufferedReader inb = new BufferedReader(in);
			String line;
			while((line = inb.readLine()) != null)
			{
				if(line.contains(name))
				{
					int Latest = getVersion(line.split(": ")[1]);
					if(Latest > ThisVersion)
					{
						Main.isLatest = false;
					}
					else
					{
						Main.isLatest = true;
					}
				}
			}
			inb.close();
			in.close();
		}
		catch(IOException e)
		{
			System.err.println("Error: " + urlString);
		}
	}
	public static int getVersion(String name)
	{
		String[] ver = name.split("\\.");
		String vers = ver[0] + ver[1] + ver[2];
		int versi = Integer.parseInt(vers);
		if(versi < 1000)
		{
			versi *= 10;
		}
		if(versi < 10000)
		{
			versi *= 10;
		}
		return versi;
	}
}
