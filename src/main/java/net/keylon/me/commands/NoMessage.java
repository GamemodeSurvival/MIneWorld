package net.keylon.me.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;

import net.keylon.me.utils.Common;

public class NoMessage extends PlayerCommand {
	
	public static List<String> nomsg = new ArrayList<String>();

	public NoMessage() {
		super("nomessage");
		setAliases(Arrays.asList("nomps", "nomsg"));
	}

	@Override
	protected void run(Player player, String[] args) {
		
		if(!nomsg.contains(player.getName())) {
		nomsg.add(player.getName());
		Common.tell(player, "&7Your messages are now &cdisabled");
		return;
		} else {
			nomsg.remove(player.getName());
			Common.tell(player, "&7Your messages are now &aenabled");
		}
		
	}

}
