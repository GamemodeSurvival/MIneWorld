package net.keylon.me.commands;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.keylon.me.utils.Common;

public class Invisible extends PlayerCommand {

	public Invisible() {
		super("invisible");
		setAliases(Arrays.asList("hide", "hideme", "invis"));
		setPermission("minecore.admin");
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void run(Player player, String[] args) {
		if(player.hasPermission("minecore.admin")) {
		Collection<? extends Player> onlineList = Bukkit.getServer().getOnlinePlayers();
		
		for (Player name : onlineList) {
			name.hidePlayer(player);
		}
		Common.tell(player, "&7You are now &aInvisible");
		} else {
			Common.tell(player, "&7You do have have permission to do this!");
			return;
		}
		
	}

}
