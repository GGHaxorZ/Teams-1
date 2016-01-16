package com.gmail.merenze.teams.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.merenze.teams.Team;
import com.gmail.merenze.teams.Teams;

public class Leave {
	public static void execute(Player player, Teams plugin, String[] args) {
		if (Team.hasTeam(player, plugin)) {
			Team team = Team.getTeam(player, plugin);
			team.removeMember(player);
			team.sendMessage(ChatColor.AQUA + player.getName() + " has left the team.");
			player.sendMessage(ChatColor.AQUA + "You have left the team.");
		}
	}
}
