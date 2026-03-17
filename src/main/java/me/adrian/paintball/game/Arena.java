package me.adrian.paintball.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private String name;
    private Location pos1;
    private Location pos2;
    private Location spawnBlue;
    private Location spawnGreen;

    private List<Player> players = new ArrayList<>();

    public Arena(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }

    public Location getPos1() {
        return pos1;
    }

    public Location getPos2() {
        return pos2;
    }

    public void setSpawnBlue(Location loc) {
        this.spawnBlue = loc;
    }

    public void setSpawnGreen(Location loc) {
        this.spawnGreen = loc;
    }

    public Location getSpawnBlue() {
        return spawnBlue;
    }

    public Location getSpawnGreen() {
        return spawnGreen;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void removePlayer(Player p) {
        players.remove(p);
    }
}
