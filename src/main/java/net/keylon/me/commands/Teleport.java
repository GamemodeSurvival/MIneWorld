package net.keylon.me.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.keylon.me.utils.Common;

public class Teleport extends PlayerCommand {

	public Teleport() {
		super("teleport");
		setAliases(Arrays.asList("tp", "tele"));
	}

	@Override
	protected void run(Player player, String[] args) {
		if (player.hasPermission("minecore.teleport")) {
			if (args.length < 0) {
				Common.tell(player, "&7Please specify a player.");
				return;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == player) {
				Common.tell(player, "&7You can not do this to yourself.");
				return;
			}
			if (target == null) {
				Common.tell(player, "&7Could not find player &8» &a" + args[0]);
				return;
			}
			Location teleportTo = target.getLocation();
			if (target.isOnline()) {
				player.teleport(teleportTo);
				Common.tell(player, "&7You have been successfully teleported to &8» &a" + target.getName());
			} else {
				Common.tell(player, "&7Player &a" + target.getName() + " &7is not online.");
			}
		}
	}
}
