package net.keylon.me.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import net.keylon.me.utils.Common;

public class Message extends PlayerCommand {
	
	@SuppressWarnings("rawtypes")
	public static Map reply = new HashMap();

	public Message() {
		super("message");
		setAliases(Arrays.asList("msg", "pm", "dm"));
		setUsage("[Usage] /msg [player] (message)");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void run(Player player, String[] args) {
		if(args.length < 0) {
			Common.tell(player, "&7Please specify a player.");
			return;
		}
		Player target = Bukkit.getServer().getPlayer(args[0]);
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
		if(NoMessage.nomsg.contains(target.getName())) {
			Common.tell(player, "&7This user has his messages &cdisabled");
			return;
		}
		String message = sb.toString();
		if(target.isOnline()) {
			Common.tell(target, "&8(&7Privado&f-&7From&8) &a" + player.getDisplayName() + " &8» &7 " + message);
			Common.tell(player, "&8(&7Privado&f-&7Sent&8) &a" + target.getDisplayName() + " &8» &7 " + message);
			target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 40, 10);
			reply.put(target.getName(), player.getName());
		} else {
			Common.tell(player, "&7Player &a" + target.getName() + " &7is not online.");
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static Map getMap() {
		return reply;
	}

}
