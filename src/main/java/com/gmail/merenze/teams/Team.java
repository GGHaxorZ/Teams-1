 package com.gmail.merenze.teams;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
//Members and Mangers arrays do NOT contain overlapping values
public class Team {
	private Teams plugin;
	private String name;
	/* - - - - - - -
	 * Constructors
	 * - - - - - - -
	 */
	//Saves a new team to the config. Only use this constructor when creating a new team.
	public Team(Player creator, String name, Teams plugin) {
		this.plugin = plugin;
		this.name = name;
		String uuid = creator.getUniqueId().toString();
		//Update team data
		//List<String> managers = new ArrayList<String>();
		//managers.add(uuid);
		this.addMember(creator);
		this.promote(creator);
		this.plugin.getConfig().set("teams." + name + ".ff", false); //Sets friendlyfire to default false		
		//Update player data
		this.plugin.getConfig().set("players." + uuid + ".team", this.name);
		this.plugin.getConfig().set("players." + uuid + ".chat", false);
		this.plugin.saveConfig();
		//Add team name to list
		List<String> names = this.plugin.getConfig().getStringList("names");
		names.add(name);
		this.plugin.getConfig().set("names", names);
	}
	//Used in the static method Team.getTeam(Player, Teams).
	private Team(Player player, Teams plugin) {
		String path = "players." + player.getUniqueId().toString() + ".team";
		this.plugin = plugin;
		this.name = plugin.getConfig().getString(path);
	}
	//Used in the static method Team.getTeam(String, Teams).
	private Team(String name, Teams plugin) {
		this.name=name;
		this.plugin=plugin;
	}
	/* - - - - -
	 * Mutators
	 * - - - - -
	 */
	//Sets team pass
	public void setPass(String pass) {
		plugin.getConfig().set("teams." + name + ".pass", pass);
		plugin.saveConfig();
	}
	//Adds a member to a team
	public void addMember(Player target) {
		String uuid = target.getUniqueId().toString();
		String path;
		//Updates team data
		path = "teams." + name + ".members";
		List<String> members = new ArrayList<String>();
		members.add(uuid);
		//plugin.getConfig().set("teams." + name + ".members", null); //Delete old list
		plugin.getConfig().set("teams." + name + ".members", members); //Set new list
		//Updates player data
		path = "players." + uuid;
		plugin.getConfig().set(path + ".team", name);
		plugin.getConfig().set(path + ".chat", false); //Sets team chat to default false
		plugin.saveConfig();
	}
	//Removes a member from the team
	public void removeMember(Player target) {
		List<String> members = plugin.getConfig().getStringList("teams." + name + ".members");
		String uuid = target.getUniqueId().toString();
		this.demote(target);
		//Updates team data
		for (int i=0;i<members.size();i++) {
			if (members.get(i).equals(uuid)) {
				members.remove(i);
				break;
			}
		}
		plugin.getConfig().set("teams." + name + ".members", null); //Delete old list
		plugin.getConfig().set("teams." + name + ".members", members); //Save new list
		plugin.getConfig().set("players." + uuid, null);
		plugin.saveConfig();
	}
	//Promotes a member to manager
	public void promote(Player target) {
		String uuid = target.getUniqueId().toString();
		List<String> members = plugin.getConfig().getStringList("teams." + name + ".members");
		if (members.contains(uuid)) { //If player is a member (as opposed to a manager or not on the team)
			List<String> managers = plugin.getConfig().getStringList("teams." + name + ".managers");
			if (members.contains(uuid)) {
				for (int i=0;i<members.size();i++) { //Remove player from members
					if (members.get(i).equals(uuid)) {
						members.remove(i);
						break;
					}
				}
			}
			managers.add(uuid); //Add player to managers
			plugin.getConfig().set("teams." + name + ".managers", null);
			plugin.getConfig().set("teams." + name + ".managers", managers); //Update managers
			plugin.getConfig().set("teams." + name + ".members", null);
			plugin.getConfig().set("teams." + name + ".members", members); //Update members
			
		}
		plugin.saveConfig();
	}
	//Demotes a manager
	public void demote(Player target) {
		String uuid = target.getUniqueId().toString();
		List<String> managers = plugin.getConfig().getStringList("teams." + name + ".managers");
		if (managers.contains(uuid)) { //If player is a manager
			List<String> members = plugin.getConfig().getStringList("teams." + name + ".managers");
			for (int i=0;i<managers.size();i++) { //Remove player from managers
				if (managers.get(i).equals(uuid)) {
					managers.remove(i);
					break;
				}
			}
			if (!members.contains(uuid)) {
				members.add(uuid); //Add player to members
			}
			plugin.getConfig().set("teams." + name + ".members", null);
			plugin.getConfig().set("teams." + name + ".members", members); //Update members
			plugin.getConfig().set("teams." + name + ".managers", null);
			plugin.getConfig().set("teams." + name + ".managers", managers); //Update managers
		}
		plugin.saveConfig();
	}
	//Sets team hq
	public void setHq(Location location) {
		String path = "teams." + name + ".hq";
		plugin.getConfig().set(path + ".world", location.getWorld().getName());
		plugin.getConfig().set(path + ".x", location.getX());
		plugin.getConfig().set(path + ".y", location.getY());
		plugin.getConfig().set(path + ".z", location.getZ());
		plugin.saveConfig();
	}
	//Sets team rally point
	public void setRally(Location location) {
		String path = "teams." + name + ".rally";
		plugin.getConfig().set(path + ".world", location.getWorld().getName());
		plugin.getConfig().set(path + ".x", Double.toString(location.getX()));
		plugin.getConfig().set(path + ".y", Double.toString(location.getY()));
		plugin.getConfig().set(path + ".z", Double.toString(location.getZ()));
		plugin.saveConfig();
	}
	//Changes whether or not teammates can hurt each other
	public void toggleFF() {
		String path = "teams." + name + "settings.ff";
		if(this.getFF()) { //If friendly fire is on
			plugin.getConfig().set(path, false); //Turn friendly fire off
		} else { //If friendly fire is off
			plugin.getConfig().set(path, true); //Turn friendly fire on
		}
		plugin.saveConfig();
	}
	//Toggles player's team chat
	public static void toggleChat(Player player, Teams plugin) {
		String uuid = player.getUniqueId().toString();
		if (getChat(player, plugin)) {
			plugin.getConfig().set("players." + uuid + ".chat", false);
		} else {
			plugin.getConfig().set("players." + uuid + ".chat", true);
		}
	}
	//Displays message to members of team
	public void sendMessage(String msg) {
		List<String> members = getMembers();
		for (int i=0;i<members.size();i++) {
			UUID uuid = UUID.fromString(members.get(i));
			plugin.getServer().getPlayer(uuid).sendMessage(msg);
		}
	}
	/* - - - - - -
	 * Accessors
	 * - - - - - -
	 */
	//Returns team name
	public String getName() {
		return name;
	}
	//Returns team pass
	public String getPass() {
		return plugin.getConfig().getString("teams." + name + ".pass");
	}
	//Returns a list of all members (including managers)
	public List<String> getMembers() {
		List<String> members = plugin.getConfig().getStringList("teams." + name + ".members");
		List<String> managers = plugin.getConfig().getStringList("teams." + name + ".managers");
		List<String> players = new ArrayList<String>();
		players.addAll(members);
		players.addAll(managers);
		return players;
	}
	//Returns team hq
	public Location getHq() {
		String path = "teams." + name + ".hq";
		if (plugin.getConfig().get(path)!=null) {
			String worldName = plugin.getConfig().getString(path + ".world");
			World world = plugin.getServer().getWorld(worldName);
			double x = plugin.getConfig().getDouble(path + ".x");
			double y = plugin.getConfig().getDouble(path + ".y");
			double z = plugin.getConfig().getDouble(path + ".z");
			return new Location(world, x, y, z);
		} else return null;
	}
	//Returns team rally point
	public Location getRally() {
		String path = "teams." + name + ".rally";
		if (plugin.getConfig().get(path)!=null) {
			String worldName = plugin.getConfig().getString(path + ".world");
			World world = plugin.getServer().getWorld(worldName);
			double x = plugin.getConfig().getDouble(path + ".x");
			double y = plugin.getConfig().getDouble(path + ".y");
			double z = plugin.getConfig().getDouble(path + ".z");
			return new Location(world, x, y, z);
		} else return null;
	}
	//Returns friendly fire setting
	public boolean getFF() {
		return plugin.getConfig().getBoolean("teams." + name + ".ff");
	}
	//Checks if a player is on the team
	public boolean hasMember(Player player) {
		String uuid = player.getUniqueId().toString();
		List<String> members = plugin.getConfig().getStringList("teams." + name + ".members");
		List<String> managers = plugin.getConfig().getStringList("teams." + name + ".managers");
		
		if (members!=null && managers!=null) {
			return (members.contains(uuid) || managers.contains(uuid));
		} else if (members==null && managers!=null) {
			return managers.contains(uuid);
		} else if (members!=null && managers==null) {
			return members.contains(uuid);
		} else { //if both are null
			return false;
		}
		//return (members.contains(uuid) || managers.contains(uuid));
	}
	//Checks if a player is team manager
	public boolean isManager(Player player) {
		String uuid = player.getUniqueId().toString();
		List<String> managers = plugin.getConfig().getStringList("teams." + name + ".managers");
		return managers.contains(uuid);
	}
	//Tests if a player is on any team
	public static boolean hasTeam(Player player, Teams plugin) {
		return getTeam(player, plugin).getName() != null;
	}
	//Tests if team exists
	public static boolean exists(String name, Teams plugin) {
		List<String> names = plugin.getConfig().getStringList("names");
		return names.contains(name);
	}
	//Returns a reference to a team
	public static Team getTeam(Player player, Teams plugin) {
		return new Team(player, plugin);
	}
	//Tests if team name exists
	public static Team getTeam (String name, Teams plugin) {
		if (exists(name, plugin)) {
			return new Team(name, plugin);
		} else return null;
	}
	//Tests if player is in team chat
	public static boolean getChat(Player player, Teams plugin) {
		String uuid = player.getUniqueId().toString();
		return plugin.getConfig().getBoolean(uuid + ".chat");
	}
}
