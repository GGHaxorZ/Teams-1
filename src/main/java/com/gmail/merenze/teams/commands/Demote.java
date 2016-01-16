package com.gmail.merenze.teams.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.merenze.teams.Team;
import com.gmail.merenze.teams.Teams;

public class Demote {
	public static void execute(Player player, Teams plugin, String[] args) {
		if (Team.hasTeam(player, plugin)) {
			Team team = Team.getTeam(player, plugin);
			if (team.isLeader(player)) {
				if (args.length>=2) {
					Player target = plugin.getServer().getPlayer(args[1]);
					if (team.hasMember(target)) {
						team.demote(target);
						team.sendMessage(ChatColor.AQUA + target.getName() + " has been demoted.");
					} else {
						player.sendMessage(ChatColor.RED + "That player is not on your team.");
					}
				} else {
					player.sendMessage(ChatColor.RED + "You must specify a teammate.");
				}
			} else {
				player.sendMessage(ChatColor.RED + "You must be a team leader to do that.");
			}
		} else {
			player.sendMessage(ChatColor.RED + "You are not on a team.");
		}
	}
}
