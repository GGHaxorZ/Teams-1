package com.gmail.merenze.teams.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.merenze.teams.Team;
import com.gmail.merenze.teams.Teams;

public class SetHq {
	public static void execute(Player player, Teams plugin, String[] args) {
		if (Team.hasTeam(player, plugin)) {
			Team team = Team.getTeam(player, plugin);
			if (team.isLeader(player)) {
				team.setHq(player.getLocation());
				player.sendMessage(ChatColor.AQUA + "Headquarters has been set to your location.");
			}
			else {
				player.sendMessage(ChatColor.RED + "You must be a team leader to do that.");
			}
		} else {
			player.sendMessage(ChatColor.RED + "You are not on a team.");
		}
	}
}
