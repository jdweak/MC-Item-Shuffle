package me.jdweak.shuffle.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jdweak.shuffle.events.GameManager;
import net.md_5.bungee.api.ChatColor;

public class GiveUpCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
		 String[] arg3) {
		
		Bukkit.getServer().broadcastMessage(ChatColor.RED + arg0.getName() + " has thrown in the towel!");
		GameManager.getInstance().removePlayer((Player) arg0);
		
		return false;
	}
}
