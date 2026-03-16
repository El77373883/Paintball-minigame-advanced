package me.adrian.paintball.game;

import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class Arena {

    private final String name;
    private final Map<Player, GameTeam> players = new HashMap<>();

    public Arena(String name) {
        this.name = name;
    }

    public void join(Player player, GameTeam team) {
        players.put(player, team);
    }

    public void leave(Player player) {
        players.remove(player);
    }

    public Map<Player, GameTeam> getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }
}
