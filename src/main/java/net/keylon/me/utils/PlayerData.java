package net.keylon.me.utils;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import net.keylon.me.Core;
import net.keylon.me.config.SimplePlayerFile;

public class PlayerData {
	
	private UUID uuid;
	private Player player;
	Integer kills;
	Integer deaths;
	Integer money;
	String rank;
	SimplePlayerFile dataF;
	public PlayerData(UUID uuid) {
		this.uuid = uuid;
		this.player = Bukkit.getPlayer(uuid);
		dataF = new SimplePlayerFile(uuid.toString() + ".yml");
		reloadData();
	}
	
	public PlayerData(Player player) {
		this.uuid = player.getUniqueId();
		this.player = player;
		dataF = new SimplePlayerFile(uuid.toString() + ".yml");
		reloadData();
	}
	
	public UUID getUUID() {
		return uuid;
	}
	
	public void reloadData() {
		File dataFolder = new File(Core.getInstance().getDataFolder() + File.separator + "players" + File.separator);
		dataFolder.mkdirs();
		SimplePlayerFile dataF = new SimplePlayerFile(uuid.toString() + ".yml");
		if (dataF.getConfig().isSet("data.kills")) {
			this.kills = dataF.getConfig().getInt("data.kills");
			
		} else {
			this.kills = 0;
			dataF.getConfig().set("data.kills", player.getStatistic(Statistic.PLAYER_KILLS));
		}
		
		if (dataF.getConfig().isSet("data.deaths")) {
			this.deaths = dataF.getConfig().getInt("data.deaths");
			
		} else {
			this.deaths = 0;
			dataF.getConfig().set("data.deaths", player.getStatistic(Statistic.DEATHS));
		}
		
		if (dataF.getConfig().isSet("data.money")) {
			this.money = dataF.getConfig().getInt("data.money");
			
		} else {
			this.money = 500;
			dataF.getConfig().set("data.money", 500);
		}
		
		if (dataF.getConfig().isSet("data.rank")) {
			this.rank = dataF.getConfig().getString("data.rank");
			
		} else {
			this.rank = "Member";
			dataF.getConfig().set("data.rank", "Member");
		}
		
		
		dataF.saveFile();
		
	}
	
	
	
}
