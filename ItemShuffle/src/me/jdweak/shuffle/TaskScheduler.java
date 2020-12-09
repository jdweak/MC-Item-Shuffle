package me.jdweak.shuffle;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.jdweak.shuffle.events.GameManager;
import net.md_5.bungee.api.ChatColor;


public class TaskScheduler extends BukkitRunnable{
	
	private final JavaPlugin plugin;
	
	int countdown = 10;
	
	public TaskScheduler(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
//		Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "TaskScheduler run method working");
		if(countdown == 0) {
			GameManager.getInstance().update();
		} else {
			Bukkit.getServer().broadcastMessage(ChatColor.RED + String.valueOf(countdown));
			countdown --;
		}
	}
	
}
