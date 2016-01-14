package com.gmail.merenze.teams;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.merenze.teams.commands.TeamCommand;

public class Teams extends JavaPlugin {
	@Override
	public void onEnable() {
		this.getCommand("team").setExecutor(new TeamCommand(this));
		this.loadConfiguration();
	}
	public void loadConfiguration() {
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
}
