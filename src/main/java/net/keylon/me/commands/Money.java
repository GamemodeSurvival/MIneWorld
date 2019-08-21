package net.keylon.me.commands;

import org.bukkit.entity.Player;

import net.keylon.me.config.SimplePlayerFile;

public class Money extends PlayerCommand {

	public Money() {
		super("money");
	}

	@Override
	protected void run(Player player, String[] args) {
		if(args[0] == "give") {
			if(player.hasPermission("minecore.money")) {
				SimplePlayerFile dataF;
				

			}
		}

	}

}
