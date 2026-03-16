package me.adrian.paintball.game;

import org.bukkit.entity.Player;
import java.util.*;

public class GameManager {

    private final Map<Player, PlayerStats> stats = new HashMap<>();
    private final Map<Player, GameTeam> teams = new HashMap<>();
    private final List<Player> alivePlayers = new ArrayList<>();

    public enum GameState { LOBBY, IN_GAME, END }

    private GameState state = GameState.LOBBY;

    public void eliminatePlayer(Player shooter, Player eliminated) {
        alivePlayers.remove(eliminated);
        stats.get(shooter).addKill();
        // Aquí se podrían dar coins al shooter
    }

    public void joinPlayer(Player player, GameTeam team) {
        teams.put(player, team);
        alivePlayers.add(player);
        stats.put(player, new PlayerStats());
    }

    public boolean isAlive(Player player) {
        return alivePlayers.contains(player);
    }

    public GameTeam getTeam(Player player) {
        return teams.get(player);
    }

    public int getKills(Player player) {
        return stats.get(player).getKills();
    }

    public List<Player> getAlivePlayers() {
        return alivePlayers;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState newState) {
        this.state = newState;
    }

    public static class PlayerStats {
        private int kills = 0;

        public int getKills() { return kills; }
        public void addKill() { kills++; }
    }

    public enum GameTeam { GREEN, BLUE }
}
