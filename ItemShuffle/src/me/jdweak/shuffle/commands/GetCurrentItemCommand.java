package me.jdweak.shuffle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jdweak.shuffle.events.GameManager;

public class GetCurrentItemCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
		 String[] arg3) {
		
		GameManager.getInstance().getCurrentItem((Player) arg0);
		
		return false;
	}
}
