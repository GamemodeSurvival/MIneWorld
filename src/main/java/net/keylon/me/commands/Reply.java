package net.keylon.me.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.keylon.me.utils.Common;

public class Reply extends PlayerCommand {

	public Reply() {
		super("reply");
		setAliases(Arrays.asList("r"));
	}

	@Override
	protected void run(Player player, String[] args) {
			
		Object checkReply = Message.getMap().get(player);
		Player target = Bukkit.getServer().getPlayer(checkReply.toString());
		
		if(args.length < 0) {
			Common.tell(player, "&7Please specify a player.");
			return;
		}

		if(target == player) {
			Common.tell(player, "&7You can not do this to yourself.");
			return;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < args.length; i++) {
			sb.append(args[i]);
			sb.append(" ");
		}
		if(target == null) {
			Common.tell(player, "&7Could not find player &8» &a" + args[0]);
		}
		
	}
}
