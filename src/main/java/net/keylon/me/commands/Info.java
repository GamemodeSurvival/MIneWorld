package net.keylon.me.commands;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.keylon.me.utils.Common;

public class Info extends PlayerCommand {

	public Info() {
		super("info");
		setAliases(Arrays.asList("whois", "who"));
		setPermission("minecore.admin");
	}

	@Override
	protected void run(Player player, String[] args) {
		if(player.hasPermission("minecore.admin")) {
			Player target = Bukkit.getPlayer(args[0]);
			Common.tell(player, "&8&m------=&8(&a" + args[0] + "&8)&8&m=------",
					"&7Online &8» &a" + target.isOnline(),
					"&7Last Online &8» &a" + target.getLastPlayed());
		}
		
	}

}
