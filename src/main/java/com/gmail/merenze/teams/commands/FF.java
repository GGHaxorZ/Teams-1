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
			if (team.isLeader(player)) {
				team.toggleFF();
				displayFF(player, team, plugin);
			}
			else {
				player.sendMessage(ChatColor.RED + "You must be a team leader to do that.");
			}
		} else {
			player.sendMessage("You are not on a team.");
		}
	}
	//Displays FF message to teammates
	public static void displayFF(Player player, Team team, Teams plugin) {
		List<String> members = team.getMembers();
		if (team.getFF()) {
			for (int i=0;i<members.size();i++) {
				UUID uuid = UUID.fromString(members.get(i));
				plugin.getServer().getPlayer(uuid).sendMessage(ChatColor.AQUA + "Friendlyfire is now on.");
			}
		} else {
			for (int i=0;i<members.size();i++) {
				UUID uuid = UUID.fromString(members.get(i));
				plugin.getServer().getPlayer(uuid).sendMessage(ChatColor.AQUA + "Friendlyfire is now off.");
			}
		}
	}
}
