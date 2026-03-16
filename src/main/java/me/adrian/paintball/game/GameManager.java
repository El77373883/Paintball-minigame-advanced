package me.adrian.paintball.game;

import org.bukkit.entity.Player;
import java.util.*;
import org.bukkit.Bukkit;

public class GameManager {

    private final PaintballPlugin plugin;
    private final Map<Player, PlayerData> playerDataMap = new HashMap<>();
    private final List<Arena> arenas = new ArrayList<>();
    private Arena currentArena;

    public GameManager(PaintballPlugin plugin) {
        this.plugin = plugin;
    }

    public void addArena(Arena arena) {
        arenas.add(arena);
    }

    public Arena getArena(String name) {
        return arenas.stream().filter(a -> a.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void joinArena(Player player, Arena arena) {
        playerDataMap.put(player, new PlayerData(player));
        currentArena = arena;
        // asignar equipo, spawn, etc.
    }

    public void leaveArena(Player player) {
        playerDataMap.remove(player);
    }

    public boolean isPlaying(Player player) {
        return playerDataMap.containsKey(player);
    }

    public void eliminate(Player shooter, Player eliminated) {
        if (!isPlaying(eliminated)) return;

        PlayerData data = playerDataMap.get(eliminated);
        data.setAlive(false);

        // Dar coins al killer
        PlayerData shooterData = playerDataMap.get(shooter);
        if (shooterData != null) shooterData.addCoins(10);

        Bukkit.getScheduler().runTask(plugin, () -> {
            eliminated.getWorld().spawnParticle(org.bukkit.Particle.CRIT_MAGIC, eliminated.getLocation(), 30, 0.5, 0.5, 0.5, 0.1);
            eliminated.getWorld().playSound(eliminated.getLocation(), org.bukkit.Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1f, 1f);
            eliminated.getWorld().strikeLightningEffect(eliminated.getLocation());
        });
    }

    public int getKills(Player player) {
        return playerDataMap.getOrDefault(player, new PlayerData(player)).getKills();
    }

    public GameTeam getTeam(Player player) {
        return playerDataMap.getOrDefault(player, new PlayerData(player)).getTeam();
    }

    public GameState getState() {
        return GameState.LOBBY;
    }

    public enum GameState { LOBBY, IN_GAME, END }
}
