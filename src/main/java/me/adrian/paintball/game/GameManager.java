package me.adrian.paintball.game;

import org.bukkit.entity.Player;
import java.util.*;

public class GameManager {

    private final Map<Player, PlayerStats> stats = new HashMap<>();
    private final Map<Player, GameTeam> teams = new HashMap<>();
    private final List<Player> alivePlayers = new ArrayList<>();
    private final Map<String, Arena> arenas = new HashMap<>();

    public enum GameState { LOBBY, IN_GAME, END }

    private GameState state = GameState.LOBBY;

    public void eliminatePlayer(Player shooter, Player eliminated) {
        alivePlayers.remove(eliminated);
        stats.get(shooter).addKill();
        // Aquí se pueden dar monedas al shooter
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

    public Map<String, Arena> getArenas() {
        return arenas;
    }

    public static class PlayerStats {
        private int kills = 0;
        private int coins = 0;

        public int getKills() { return kills; }
        public void addKill() { kills++; }

        public int getCoins() { return coins; }
        public void addCoins(int amount) { coins += amount; }
        public boolean removeCoins(int amount) {
            if (coins >= amount) { coins -= amount; return true; }
            return false;
        }
    }

    public enum GameTeam { GREEN, BLUE }

    public static class Arena {
        private final String name;
        private Map<GameTeam, LocationWrapper> spawns = new HashMap<>();

        public Arena(String name) { this.name = name; }
        public String getName() { return name; }
        public void setSpawn(GameTeam team, LocationWrapper loc) { spawns.put(team, loc); }
        public LocationWrapper getSpawn(GameTeam team) { return spawns.get(team); }
    }

    public static class LocationWrapper {
        private final double x, y, z;
        private final float yaw, pitch;
        private final String world;

        public LocationWrapper(String world, double x, double y, double z, float yaw, float pitch) {
            this.world = world; this.x = x; this.y = y; this.z = z; this.yaw = yaw; this.pitch = pitch;
        }
    }
}
