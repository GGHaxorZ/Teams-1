package com.gmail.merenze.teams.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.merenze.teams.Team;
import com.gmail.merenze.teams.Teams;

public class Rally {
	public static void execute(Player player, Teams plugin, String[] args) {
		if (Team.hasTeam(player, plugin)) { //If player is on a team
			Team team = Team.getTeam(player, plugin);
			if (team.getRally()!=null) { //If team has an rally
				player.teleport(team.getHq()); //Teleport player to rally
			}
		} else { //If player is not on a team
			player.sendMessage(ChatColor.RED + "You are not on a team!");
		}
	}
}
