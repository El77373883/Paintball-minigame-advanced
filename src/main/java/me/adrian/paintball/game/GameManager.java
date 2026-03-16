package me.adrian.paintball.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;

public class GameManager {

    private final Map<String, Arena> arenas = new HashMap<>();
    private final Map<Player, Arena> playerArena = new HashMap<>();
    private final Map<Player, Integer> kills = new HashMap<>();
    private int gameTime = 0;

    public GameManager() {}

    public void createArena(String name) {
        arenas.put(name, new Arena(name));
    }

    public Arena getArena(Player p) {
        return playerArena.get(p);
    }

    public void joinArena(Player p) {
        if (!arenas.isEmpty()) {
            Arena arena = arenas.values().stream().findAny().orElse(null);
            if (arena != null) {
                GameTeam team = arena.getPlayers().size() % 2 == 0 ? GameTeam.BLUE : GameTeam.GREEN;
                arena.join(p, team);
                playerArena.put(p, arena);
                setupScoreboard(p, team);
            }
        }
    }

    public void setupScoreboard(Player player, GameTeam team) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null) return;

        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("paintball", "dummy", "§6Paintball");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore("Jugador: §b" + player.getName()).setScore(5);
        obj.getScore("Equipo: §a" + team.name()).setScore(4);
        obj.getScore("Kills: §c0").setScore(3);
        obj.getScore("Vivos: §e" + arena.getPlayers().size()).setScore(2);
        obj.getScore("Tiempo: §f0s").setScore(1);

        player.setScoreboard(board);
    }

    public void addKill(Player killer) {
        kills.put(killer, kills.getOrDefault(killer, 0) + 1);
        updateScoreboards(killer);
    }

    public int getKills(Player player) {
        return kills.getOrDefault(player, 0);
    }

    private void updateScoreboards(Player p) {
        Scoreboard board = p.getScoreboard();
        for (String line : board.getEntries()) {
            if (line.startsWith("Kills:")) {
                board.getObjective(DisplaySlot.SIDEBAR).getScore(line).setScore(getKills(p));
            }
        }
    }
}
