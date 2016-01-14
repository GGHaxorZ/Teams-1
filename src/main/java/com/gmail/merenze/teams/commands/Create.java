package com.gmail.merenze.teams.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.merenze.teams.Team;
import com.gmail.merenze.teams.Teams;

public class Create {
	public static void execute(Player player, Teams plugin, String[] args) {
		if (!Team.hasTeam(player, plugin)) {
			if (args.length>=2) { //If name is provided
				Team team = new Team(player, args[1], plugin);
				if (args.length>=3) { //If pass is provided
					team.setPass(args[2]);
				}
				player.sendMessage(ChatColor.AQUA + "Success! You have created team " + team.getName());
			} else {
				player.sendMessage(ChatColor.RED + "You must provide a name!");
			}
		} else {
			player.sendMessage(ChatColor.RED + "You are already on a team!");
		}
	}
	
}
