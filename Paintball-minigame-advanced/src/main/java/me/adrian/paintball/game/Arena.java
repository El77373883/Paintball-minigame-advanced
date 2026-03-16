package me.adrian.paintball.game;

import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Arena {

    private String name;
    private List<Player> players = new ArrayList<>();
    private Map<Player, GameTeam> playerTeams = new HashMap<>();

    public Arena(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player, GameTeam team) {
        players.add(player);
        playerTeams.put(player, team);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        playerTeams.remove(player);
    }

    // Devuelve el equipo de un jugador
    public GameTeam getTeam(Player player) {
        return playerTeams.getOrDefault(player, GameTeam.BLUE); // por defecto azul
    }
}
