package me.adrian.paintball.game;

import me.adrian.paintball.PaintballPlugin;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.Particle;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class GameManager {

    public enum GameTeam { GREEN, BLUE }
    public enum GameState { WAITING, IN_GAME }

    private GameState state = GameState.WAITING;
    private final PaintballPlugin plugin;
    private final Map<String, Arena> arenas = new HashMap<>();
    private final Map<Player, Integer> coins = new HashMap<>();
    private final Map<Player, Integer> kills = new HashMap<>();
    private Arena currentArena;

    public GameManager(PaintballPlugin plugin) { this.plugin = plugin; }

    public GameState getState() { return state; }

    public void createArena(String name) {
        arenas.put(name, new Arena(name));
    }

    public Arena getArena(String name) { return arenas.get(name); }

    public void joinArena(Player p, String arenaName, GameTeam team) {
        Arena a = arenas.get(arenaName);
        if (a == null) { p.sendMessage("Arena no existe"); return; }
        currentArena = a;
        a.addPlayer(p, team);
        coins.putIfAbsent(p, 10);
        kills.putIfAbsent(p, 0);
        checkStart();
    }

    private void checkStart() {
        if (currentArena != null && currentArena.getAlivePlayers().size() >= 2) {
            state = GameState.IN_GAME;
            Bukkit.broadcastMessage("§aEl juego ha comenzado en la arena " + currentArena.getName());
        }
    }

    public void eliminate(Player shooter, Player eliminated) {
        kills.put(shooter, kills.getOrDefault(shooter, 0) + 1);
        coins.put(shooter, coins.getOrDefault(shooter, 0) + 2);

        Location loc = eliminated.getLocation();
        var world = loc.getWorld();
        if (world != null) {
            world.spawnParticle(Particle.CRIT_MAGIC, loc, 30, 0.5, 0.5, 0.5, 0.1);
            world.playSound(loc, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1f, 1f);
            world.strikeLightningEffect(loc);
        }

        if (currentArena != null) {
            currentArena.removePlayer(eliminated);
            checkWin();
        }
    }

    private void checkWin() {
        if (!currentArena.isTeamAlive(GameTeam.GREEN)) winTeam(GameTeam.BLUE);
        if (!currentArena.isTeamAlive(GameTeam.BLUE)) winTeam(GameTeam.GREEN);
    }

    private void winTeam(GameTeam team) {
        for (Player p : currentArena.getAlivePlayers()) {
            p.sendMessage("§6El equipo " + team + " ha ganado!");
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1f, 1f);
        }
        state = GameState.WAITING;
    }

    public int getCoins(Player p) { return coins.getOrDefault(p, 0); }
    public int getKills(Player p) { return kills.getOrDefault(p, 0); }
}
