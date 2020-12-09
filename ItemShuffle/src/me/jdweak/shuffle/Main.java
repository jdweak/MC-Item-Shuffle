package me.jdweak.shuffle;

import org.bukkit.plugin.java.JavaPlugin;

import me.jdweak.shuffle.commands.GetCurrentItemCommand;
import me.jdweak.shuffle.commands.GiveUpCommand;
import me.jdweak.shuffle.commands.ListMaterialsCommand;
import me.jdweak.shuffle.commands.ReRollCommand;
import me.jdweak.shuffle.commands.StartItemShuffleCommand;
import me.jdweak.shuffle.commands.StopItemShuffleCommand;
import me.jdweak.shuffle.events.GameManager;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
	


	
	public void onEnable() {

		
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "\n\nItemShuffle enabled\n\n");

		//Events///////////////////////////////////////
		GameManager.getInstance().setMainClass(this);
		getServer().getPluginManager().registerEvents(GameManager.getInstance(), this);
		//Commands/////////////////////////////////////
		getCommand("startItemShuffle").setExecutor(new StartItemShuffleCommand());
		getCommand("endItemShuffle").setExecutor(new StopItemShuffleCommand());
		getCommand("getMaterials").setExecutor(new ListMaterialsCommand());
		getCommand("giveUp").setExecutor(new GiveUpCommand());
		getCommand("reroll").setExecutor(new ReRollCommand());
		getCommand("getCurrentItem").setExecutor(new GetCurrentItemCommand());
		//testing
		

	}
	
	
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "\n\n LearningToCode2 disabled\n\n");
		
	}

}
