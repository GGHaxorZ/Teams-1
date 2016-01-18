package com.gmail.merenze.teams.commands;
//https://bukkit.org/threads/multiple-args-basing-off-a-main-executor-in-different-classes.166300/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.merenze.teams.Teams;
import com.gmail.merenze.teams.commands.Create;

public class TeamCommand implements CommandExecutor {	
	private Teams plugin;
	
	public TeamCommand(Teams plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length==0 || !(sender instanceof Player)) {
				showUsage(sender);
		} else if (args.length>=1) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				String arg = args[0];
				if (arg.equalsIgnoreCase("create")) {
					Create.execute(player, plugin, args);
				} else if (arg.equalsIgnoreCase("join") || arg.equalsIgnoreCase("j")) {
					Join.execute(player, plugin, args);
				} else if (arg.equalsIgnoreCase("hq")) {
					Hq.execute(player, plugin, args);
				} else if (arg.equalsIgnoreCase("rally")) {
					Rally.execute(player, plugin, args);
				} else if (arg.equalsIgnoreCase("chat") || arg.equalsIgnoreCase("c")) {
					Chat.execute(player, plugin, args);
				} else if (arg.equalsIgnoreCase("info")) {
					Info.execute(player, plugin, args);
				} else if (arg.equalsIgnoreCase("pass")) {
					Pass.execute(player, plugin, args);
				} else if (arg.equalsIgnoreCase("promote")) {
					Promote.execute(player, plugin, args);
				} else if (arg.equalsIgnoreCase("demote")) {
					Demote.execute(player, plugin, args);
				} else if (arg.equalsIgnoreCase("kick") || arg.equalsIgnoreCase("k")) {
					Kick.execute(player, plugin, args);
				} else if (arg.equalsIgnoreCase("sethq")) {
					SetHq.execute(player, plugin, args);
				} else if (arg.equalsIgnoreCase("setrally")) {
					SetRally.execute(player, plugin, args);
				} else if (arg.equalsIgnoreCase("ff")) {
					FF.execute(player, plugin, args);
				} else if (arg.equalsIgnoreCase("leave")) {
					Leave.execute(player, plugin, args);
				} else {
					showUsage(player);
				}
			}
		}
		return true;
	}
	public void showUsage(CommandSender sender) {
		String usage =
				"/team - Display team interface\n" +
				"/team create <name> [pass] - Create a team\n" +
				"/team join <name> [pass] - Join an existing team\n" +
				"/team hq - Teleport to your team's headquarters\n" +
				"/team rally - Teleport to your team's rally\n" +
				"/team chat - Enter your team's chat\n" +
				"/team info [player] - Get info about a player's team\n" +
				"/team pass <password> - Set your team's password\n" +
				"/team promote <player> - Promote a player on your team\n" +
				"/team demote <player> - Demote a player on your team\n" +
				"/team kick <player> - Kick a player from your team\n" +
				"/team sethq - Set your team's headquarters to your location\n" +
				"/team setrally - Set your team's rally point to your location\n" +
				"/team ff - Toggle your team's friendlyfire setting\n" +
				"/team leave - Leave your team";
		sender.sendMessage(usage);
	}
}
