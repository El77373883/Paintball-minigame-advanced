package me.adrian.paintball.game;

import org.bukkit.Location;

public class Arena {

    private final String name;
    private Location greenTeamSpawn;
    private Location blueTeamSpawn;

    public Arena(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void setGreenTeamSpawn(Location loc) { this.greenTeamSpawn = loc; }
    public void setBlueTeamSpawn(Location loc) { this.blueTeamSpawn = loc; }

    public Location getGreenTeamSpawn() { return greenTeamSpawn; }
    public Location getBlueTeamSpawn() { return blueTeamSpawn; }
}
