package com.gmail.merenze.teams.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.merenze.teams.Team;
import com.gmail.merenze.teams.Teams;

public class FF {
	public static void execute(Player player, Teams plugin, String[] args) {
		if (Team.hasTeam(player, plugin)) {
			Team team = Team.getTeam(player, plugin);
			if (team.isManager(player)) {
				team.toggleFF();
				if (team.getFF()) team.sendMessage(ChatColor.DARK_AQUA + "Friendlyfire is now on.");
				else team.sendMessage(ChatColor.DARK_AQUA + "Friendlyfire is now off.");
			}
			else {
				player.sendMessage(ChatColor.RED + "You must be a manager to do that.");
			}
		} else {
			player.sendMessage("You are not on a team.");
		}
	}
}
