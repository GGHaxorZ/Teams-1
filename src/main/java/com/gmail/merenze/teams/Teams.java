package com.gmail.merenze.teams;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.merenze.teams.commands.TeamCommand;

public class Teams extends JavaPlugin {
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().setExecutor(new TeamCommand(this));
	}
}
