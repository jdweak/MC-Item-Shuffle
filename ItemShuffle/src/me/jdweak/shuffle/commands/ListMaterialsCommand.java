package me.jdweak.shuffle.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.jdweak.shuffle.events.GameManager;
import net.md_5.bungee.api.ChatColor;
public class ListMaterialsCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
		 String[] arg3) {
		int counter = 0;
		for(Material material : Material.values()) {
			arg0.sendMessage(material.toString());
			Bukkit.getServer().getConsoleSender().sendMessage(material.toString());
			Bukkit.getServer().getConsoleSender().sendMessage(String.valueOf(counter));
			counter ++;
		}
		
		arg0.sendMessage(String.valueOf(Material.values().length));
		return false;
	}

}
