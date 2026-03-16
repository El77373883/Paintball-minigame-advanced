package me.adrian.paintball.game;

import org.bukkit.entity.Player;
import java.util.*;

public class Arena {

    private final String name;
    private final Map<Player, GameManager.GameTeam> players = new HashMap<>();

    public Arena(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public Collection<Player> getPlayers() { return players.keySet(); }

    public void addPlayer(Player player, GameManager.GameTeam team) {
        players.put(player, team);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }
}
