package com.gmail.merenze.teams.commands;
//https://bukkit.org/threads/multiple-args-basing-off-a-main-executor-in-different-classes.166300/
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gmail.merenze.teams.Teams;

public class TeamCommand implements CommandExecutor {
	private Map<String,Object> argHandler;
	
	private Teams plugin;
	
	public TeamCommand(Teams plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return true;
	}
}
