package com.gmail.merenze.teams.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.merenze.teams.Team;
import com.gmail.merenze.teams.Teams;

public class Chat {
	public static void execute(Player player, Teams plugin, String[] args) {
		if (Team.hasTeam(player, plugin)) { //If player is on a team
			Team.toggleChat(player, plugin); //Toggle player chat
			if (Team.getChat(player, plugin)) { //If player is now in team chat
				player.sendMessage(ChatColor.AQUA + "You are now in team chat.");
			} else { //If player is now out of team chat
				player.sendMessage(ChatColor.AQUA + "You are no longer in team chat.");
			}
		}
	}
}
