package me.jdweak.shuffle.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jdweak.shuffle.events.GameManager;
import net.md_5.bungee.api.ChatColor;

public class ReRollCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
		 String[] arg3) {
		if(arg3.length != 0 && arg0.hasPermission("op")){
			Player player = Bukkit.getPlayer(arg3[0]);
			GameManager.getInstance().rerollPlayerMaterial(player);
		}
		
		GameManager.getInstance().rerollPlayerMaterial((Player) arg0);
		
		return false;
	}
}
