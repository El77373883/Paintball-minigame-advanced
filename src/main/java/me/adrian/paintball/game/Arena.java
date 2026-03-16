package me.adrian.paintball.game;

import org.bukkit.Location;

public class Arena {

    private final String name;
    private Location blueSpawn;
    private Location greenSpawn;

    public Arena(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBlueSpawn(Location loc) { blueSpawn = loc; }
    public void setGreenSpawn(Location loc) { greenSpawn = loc; }

    public Location getBlueSpawn() { return blueSpawn; }
    public Location getGreenSpawn() { return greenSpawn; }

    public GameTeam getTeam(org.bukkit.entity.Player p) {
        // Default team selection (simple)
        return GameTeam.BLUE;
    }
}
