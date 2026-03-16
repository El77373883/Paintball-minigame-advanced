package me.adrian.paintball.game;

import org.bukkit.entity.Player;
import java.util.*;

public class GameManager {

    public enum GameState { WAITING, IN_GAME, FINISHED }
    public enum GameTeam { BLUE, GREEN }

    private GameState state;
    private final Map<String, Arena> arenas = new HashMap<>();
    private Arena currentArena;

    private final Map<Player, PlayerData> playerDataMap = new HashMap<>();

    public GameManager() {
        this.state = GameState.WAITING;
    }

    // ---------------- ARENAS ---------------- //
    public void createArena(String name) {
        if (!arenas.containsKey(name)) {
            Arena arena = new Arena(name);
            arenas.put(name, arena);
        }
    }

    public void addArena(Arena arena) {
        arenas.put(arena.getName(), arena);
    }

    public void setCurrentArena(String name) {
        this.currentArena = arenas.get(name);
    }

    public Arena getCurrentArena() {
        return currentArena;
    }

    public Collection<Arena> getArenas() {
        return arenas.values();
    }

    // ---------------- GAME ---------------- //
    public GameState getState() { return state; }

    public void startGame() { state = GameState.IN_GAME; }

    public void endGame() {
        state = GameState.FINISHED;
        playerDataMap.clear();
    }

    public boolean isPlaying(Player player) {
        return playerDataMap.containsKey(player);
    }

    public int getAliveCount() {
        int count = 0;
        for (PlayerData data : playerDataMap.values()) {
            if (data.isAlive()) count++;
        }
        return count;
    }

    public int getTime() {
        return 300; // ejemplo: 5 minutos
    }

    // ---------------- PLAYERS ---------------- //
    public void addPlayer(Player player) {
        playerDataMap.putIfAbsent(player, new PlayerData(player));
    }

    public void removePlayer(Player player) {
        playerDataMap.remove(player);
    }

    public PlayerData getPlayerData(Player player) {
        return playerDataMap.get(player);
    }
}
