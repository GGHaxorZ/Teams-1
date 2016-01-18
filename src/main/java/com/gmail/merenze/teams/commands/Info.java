package com.gmail.merenze.teams.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.merenze.teams.Team;
import com.gmail.merenze.teams.Teams;

public class Info {
	public static void execute(Player player, Teams plugin, String[] args) {
		if (args.length>=2) {
			Player target = plugin.getServer().getPlayer(args[1]);
			if (target!=null) { //If target player exists
				if (Team.hasTeam(target, plugin)) { //If target player is on a team
					displayOtherInfo(player, target, plugin);
				} else { //If target player is not on a team
					player.sendMessage(ChatColor.GRAY + "That player is not on a team.");
				}
			} else { //If target player does not exist
				player.sendMessage(ChatColor.RED + "That player does not exist.");
			}
		} else { //If no arguments are provided
			if (Team.hasTeam(player, plugin)) { //If player is on a team
				displayInfo(player, plugin);
			} else {
				player.sendMessage(ChatColor.RED + "You are not on a team!");
			}
		}
	}
	//Displays info about other team to player
	public static void displayOtherInfo(Player player, Player target, Teams plugin) {
		Team team = Team.getTeam(target, plugin);
		List<String> members = team.getMembers();
		Player member;
		String info =
				ChatColor.AQUA + "Displaying team info for " + target.getName() + ":\n" +
				ChatColor.GRAY + "Name: " + ChatColor.WHITE + team.getName() + "\n" +
				ChatColor.GRAY + "Members:\n";
		for (int i=0;i<=members.size();i++) {
			UUID uuid = UUID.fromString(members.get(i));
			member = plugin.getServer().getPlayer(uuid);
			if (team.isLeader(member)) {
				info = info + ChatColor.AQUA + member.getName();
			} else {
				info = info + ChatColor.GRAY + member.getName();
			}
			if (i!=members.size()-1) {
				info = info + ChatColor.GRAY + ", ";
			}
		}
		player.sendMessage(info);
	}
	//Displays player's own team info
	public static void displayInfo(Player target, Teams plugin) {
		Team team = Team.getTeam(target, plugin);
		List<String> members = team.getMembers();
		Player member;
		String info =
				ChatColor.AQUA + "Displaying team info for " + target.getName() + ":\n" +
				ChatColor.GRAY + "Name: " + ChatColor.WHITE + team.getName() + "\n";
		if (team.getPass()!=null) {
			info = info + ChatColor.GRAY + "Password: " + team.getPass() + "\n";
		} else {
			info = info + ChatColor.GRAY + "Password: Not set\n";
		}
		if (team.getHq()!=null) {
			info = info + ChatColor.GRAY + "HQ: Set\n";
		} else {
			info = info + ChatColor.GRAY + "HQ: Not set\n";
		}
		if (team.getRally()!=null) {
			info = info + ChatColor.GRAY + "Rally: Set\n";
		} else {
			info = info + ChatColor.GRAY + "Rally: Not set\n";
		}
		if (team.getFF()) {
			info = info + ChatColor.GRAY + "Friendlyfire: On\n";
		} else {
			info = info + ChatColor.GRAY + "Friendlyfire: Off\n";
		}
		info = info + ChatColor.GRAY + "Members:\n";
		for (int i=0;i<members.size();i++) {
			UUID uuid = UUID.fromString(members.get(i));
			member = plugin.getServer().getPlayer(uuid);
			if (team.isLeader(member)) {
				info = info + ChatColor.AQUA + member.getName() + ChatColor.GRAY + ", ";
			} else {
				info = info + ChatColor.GRAY + member.getName() + ", ";
			}
		}
		target.sendMessage(info);
	}
}
