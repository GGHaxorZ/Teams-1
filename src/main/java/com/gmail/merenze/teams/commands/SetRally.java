package com.gmail.merenze.teams.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.merenze.teams.Team;
import com.gmail.merenze.teams.Teams;

public class SetRally {
	public static void execute(Player player, Teams plugin, String[] args) {
		if (Team.hasTeam(player, plugin)) {
			Team team = Team.getTeam(player, plugin);
			if (team.isLeader(player)) {
				team.setRally(player.getLocation());
				player.sendMessage(ChatColor.AQUA + "Rally point has been set to your location.");
			}
		}
	}
}
