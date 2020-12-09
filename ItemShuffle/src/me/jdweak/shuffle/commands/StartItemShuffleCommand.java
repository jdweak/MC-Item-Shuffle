package me.jdweak.shuffle.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.jdweak.shuffle.events.GameManager;
import net.md_5.bungee.api.ChatColor;
public class StartItemShuffleCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
		 String[] arg3) {
		Bukkit.broadcastMessage("Starting Item Shuffle!");
		Bukkit.broadcastMessage(ChatColor.GOLD + "HOW TO PLAY: EVERY ROUND, EACH PLAYER IS GIVEN AN ITEM TO FIND. WHEN YOU FIND YOUR ITEM, LEFT OR RIGHT CLICK WITH THE ITEM IN YOUR HAND TO REGISTER YOUR ITEM. IF YOU FAIL TO FIND YOUR ITEM IN TIME, YOU'RE OUT! LAST PLAYER STANDING WINS!");
		if(arg3.length != 0) {
			GameManager.getInstance().setRoundLength(Integer.valueOf(arg3[0]));
		}
			GameManager.getInstance().start();
		
		return false;
	}

}
