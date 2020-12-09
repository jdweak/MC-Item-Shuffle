package me.jdweak.shuffle.events;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import me.jdweak.shuffle.Main;
import me.jdweak.shuffle.TaskScheduler;

public class GameManager implements Listener{
	
	JavaPlugin mainClass;
	BukkitRunnable taskScheduler;
	int seconds;
	public String[] bannedMaterials = {"SHULKER", "PURPUR", "DRAGON", "SPAWNER", "CHAINMAIL", "CHORUS", "ENDSTONE", "RECORD", "COMMAND", "MONSTER", "STRUCTURE", "SMOOTH_STAIR"};
	public ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Boolean> completed = new ArrayList<Boolean>();
	public ArrayList<Material> challenge = new ArrayList<Material>();
	public ArrayList<Material> materials = new ArrayList<Material>();
	
	private static GameManager gameManager;
	
	public void setMainClass(JavaPlugin plugin) {
		mainClass = plugin;
	}
	
	private GameManager() {
		seconds = 480;
		setMaterialsList();
	}
	
	public static synchronized GameManager getInstance() {
//		Bukkit.broadcastMessage(ChatColor.RED + "Manager instance called");
		if(gameManager == null) {
//			Bukkit.broadcastMessage(ChatColor.RED + "Manager instance if1");
			gameManager = new GameManager();
//			Bukkit.broadcastMessage(ChatColor.RED + "Manager instance if2");
		}
		return gameManager;
	}
	
	public void setMaterialsList() {
		for(Material material : Material.values()) {
			if(material.isItem() && !isBanned(material.toString())) {
				materials.add(material);
			}
		}
	}
	
	public void startTimer() {
		if(taskScheduler != null) {
			taskScheduler.cancel();
		}
//		Bukkit.broadcastMessage(ChatColor.YELLOW + "Scheduling task");
		taskScheduler = new TaskScheduler(mainClass);
		taskScheduler.runTaskTimer(mainClass, seconds * 20, 20);
	}
	
	public void resetTimer() {
		taskScheduler.cancel();
		startTimer();
	}
	
	public void start() {	
		for(Player p : Bukkit.getOnlinePlayers()){
			players.add(p);
		}
		setAllPlayerGamemode(GameMode.SURVIVAL);
		setRandomArea();
		setMaterials();
		startTimer();
	}
	
	private void setMaterials() {
		challenge.clear();
		completed.clear();
		for(int i = 0; i < players.size(); i ++) {
			Material material = materials.get((int) (Math.random() * materials.size()));
			challenge.add(material); // add random material from large list to challenge list
			completed.add(false);
			players.get(i).sendMessage(ChatColor.YELLOW + "Your material is " + challenge.get(i).toString());
		}
	}

	public void update() { //add checks for no players left and 1 player left
//		Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "update method running");
		ArrayList<Player> temp = new ArrayList<Player>();
		for(int i = 0; i < players.size(); i ++) { // loops through players to see if they failed to get their item
			if(completed.get(i) == false) {
				Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + players.get(i).getName() + " has failed to find their item!");
				players.get(i).setGameMode(GameMode.SPECTATOR);
				players.get(i).getWorld().strikeLightning(players.get(i).getLocation());
				players.get(i).getWorld().createExplosion(players.get(i).getLocation(), (float) (Math.random() * 20));
			} else {
				temp.add(players.get(i));
			}
		}
		players = temp;
		checkWinConditions();
	}
	
	public void checkWinConditions() {
		if(players.size() == 1) {
			Bukkit.getServer().broadcastMessage(ChatColor.GREEN + players.get(0).getName() + " has won!");
			end();
		} else if(players.size() == 0) {
			Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Tie! No winners today!");
			end();
		} else {
			setMaterials();
			resetTimer();
		}
	}
	
	public void setRandomArea() {
		World world = players.get(0).getWorld();
		Location location = new Location(world, 0, 0, 0);
		location.setX(Math.random() * 20000000);
		location.setZ(Math.random() * 20000000);
		location.setY(players.get(0).getWorld().getHighestBlockYAt(location));
		for(Player player : players) {
			player.teleport(location);
		}
		world.setSpawnLocation(location);
	}

	public void rerollPlayerMaterial(Player player) {
		int index = players.indexOf(player);
		Material material = materials.get((int) (Math.random() * materials.size()));
		challenge.set(index, material);
		Bukkit.getServer().broadcastMessage("Rerolling " + player.getName() + "'s item!");
		player.sendMessage(ChatColor.YELLOW + "Your material is " + challenge.get(index).toString());
	}
	
	public void removePlayer(Player player) {
		players.remove(player);
		player.setGameMode(GameMode.SPECTATOR);
		player.getWorld().strikeLightning(player.getLocation());
		player.getWorld().createExplosion(player.getLocation(), (float) (Math.random() * 20));
		checkWinConditions();
	}
	
	public void getCurrentItem(Player player) {
		int index = players.indexOf(player);
		player.sendMessage(ChatColor.AQUA + "Your current item is " + challenge.get(index).toString());
	}
	
	public void end() {
		players.clear();
		completed.clear();
		challenge.clear();
		taskScheduler.cancel();
		setAllPlayerGamemode(GameMode.SURVIVAL);
	}
	
	public boolean isBanned(String material) {
		for(String banned : bannedMaterials) {
			if(material.contains(banned)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void setRoundLength(int seconds) {
		this.seconds = seconds;
	}
	
	public void setAllPlayerGamemode(GameMode gamemode) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.setGameMode(gamemode);
		}
	}
	
	@EventHandler
	public void playerClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getPlayer().getInventory().getItemInHand();
		Action action = event.getAction();
		if(players.contains(player)) {
			int index = players.indexOf(player);
			if(item.getType() == challenge.get(index) && completed.get(index) == false) {
				completed.set(index, true);
				Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + " has found their item!");
				checkReset();
			}
		}
	}
	
	public void checkReset() {
		if(!completed.contains(false)) { // check if everyone has found their items
			setMaterials();
			resetTimer();
		}
	}
	
	public void test() {
		Bukkit.broadcastMessage(ChatColor.BLUE + "GameManager test method called");
	}

}
