package net.keylon.me;

import java.io.File;
import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Core extends JavaPlugin implements Listener {

	public static Core instance;

	private static Economy econ = null;
	private static Permission perms = null;
	private static Chat chat = null;

	@Override
	public void onEnable() {

		instance = this;

		if (!setupEconomy()) {
			System.out.println(
					String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		setupPermissions();
		setupChat();

		System.out.println("MineCore Successfully Enabled!");
	}

	public Core() {
		instance = this;
	}

	public static Core getInstance() {
		return instance;
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

	private boolean setupChat() {
		RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		chat = rsp.getProvider();
		return chat != null;
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}

	@EventHandler
	public void onjoin(PlayerJoinEvent e) {
		File f = new File(this.getDataFolder() + File.separator + "players" + e.getPlayer().getUniqueId() + ".yml");
		if (!(f.exists())) {
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static Economy getEconomy() {
		return econ;
	}

	public static Permission getPermissions() {
		return perms;
	}

	public static Chat getChat() {
		return chat;
	}

}
