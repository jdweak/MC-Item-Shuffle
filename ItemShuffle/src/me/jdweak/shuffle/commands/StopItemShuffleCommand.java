package me.jdweak.shuffle.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.jdweak.shuffle.events.GameManager;

public class StopItemShuffleCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
		 String[] arg3) {
		Bukkit.broadcastMessage("Ending Item Shuffle!");
		GameManager.getInstance().end();
		return false;
	}

}
