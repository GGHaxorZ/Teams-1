package com.gmail.merenze.teams.commands;
//https://bukkit.org/threads/multiple-args-basing-off-a-main-executor-in-different-classes.166300/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.merenze.teams.Teams;

public class TeamCommand implements CommandExecutor {	
	private Teams plugin;
	
	public TeamCommand(Teams plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length==0) {
				
		} else if (args.length>=1) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				String arg = args[0];
				if (arg.equalsIgnoreCase("create")) {
					Create.execute(player, plugin, args);
				} else if (arg.equalsIgnoreCase("join")) {
					
				} else if (arg.equalsIgnoreCase("hq")) {
					
				} else if (arg.equalsIgnoreCase("rally")) {
					
				} else if (arg.equalsIgnoreCase("chat")) {
					
				} else if (arg.equalsIgnoreCase("info")) {
					
				} else if (arg.equalsIgnoreCase("promote")) {
					
				} else if (arg.equalsIgnoreCase("demote")) {
					
				} else if (arg.equalsIgnoreCase("kick")) {
					
				} else if (arg.equalsIgnoreCase("sethq")) {
					
				} else if (arg.equalsIgnoreCase("setrally")) {
					
				} else if (arg.equalsIgnoreCase("ff")) {
					
				} else if (arg.equalsIgnoreCase("leave")) {
					
				}
			}
		}
		return true;
	}
	
}
