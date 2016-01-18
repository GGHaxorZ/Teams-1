package com.gmail.merenze.teams.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.merenze.teams.Team;
import com.gmail.merenze.teams.Teams;

public class Join {
	public static void execute(Player player, Teams plugin, String[] args) {
		if (!Team.hasTeam(player, plugin)) { //If player is not already on a team
			if (args.length>=2) { //If name is provided
				if (Team.exists(args[1], plugin)) {
					Team team = Team.getTeam(args[1], plugin);
					if (team.getPass() == null) { //If team does not have a pass
						team.sendMessage(ChatColor.DARK_AQUA + player.getName() + " has joined the team.");
						team.addMember(player); //Add the player to the team
						player.sendMessage(ChatColor.DARK_AQUA + "Success! You have joined team " + team.getName());
					} else { //If team does have a pass
						if (args.length>=3) { //If pass is provided
							if (args[2].equals(team.getPass())) { //If pass is correct
								team.addMember(player); //Add the player to the team
								team.sendMessage(ChatColor.DARK_AQUA + player.getName() + " has joined the team.");
							} else { //If pass is incorrect
								player.sendMessage(ChatColor.RED + "Incorrect password.");
							}
						} else {
							player.sendMessage(ChatColor.RED + "Incorrect password.");
						}
					}
				} else {
					player.sendMessage(ChatColor.RED + "That team does not exist.");
				}
			} else { //If name is not provided
				player.sendMessage(ChatColor.RED + "You must provide a name.");
			}
		} else { //If player is already on a team
			player.sendMessage(ChatColor.RED + "You are already on a team.");
		}
	}
}
