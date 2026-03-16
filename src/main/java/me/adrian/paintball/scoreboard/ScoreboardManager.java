package me.adrian.paintball.scoreboard;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.game.GameManager.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

public class ScoreboardManager {

    private final PaintballPlugin plugin;
    private final GameManager gameManager;

    private final Map<Player, Scoreboard> playerBoards = new HashMap<>();

    public ScoreboardManager(PaintballPlugin plugin) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
    }

    public void createScoreboard(Player player) {
        // ⚠️ Importante: usamos Bukkit.getScoreboardManager() de org.bukkit
        org.bukkit.scoreboard.ScoreboardManager bukkitManager = Bukkit.getScoreboardManager();
        if (bukkitManager == null) return;

        Scoreboard board = bukkitManager.getNewScoreboard(); // ✔ ahora sí funciona
        Objective obj = board.registerNewObjective("paintball", "dummy", "§a§lPaintball Stats");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Inicializamos las líneas
        obj.getScore("§7----------------").setScore(6);
        obj.getScore("Kills: 0").setScore(5);
        obj.getScore("Coins: 0").setScore(4);
        obj.getScore("Snowballs: 0").setScore(3);
        obj.getScore("Vivos: 0").setScore(2);
        obj.getScore("Tiempo: 0s").setScore(1);
        obj.getScore("§7----------------").setScore(0);

        player.setScoreboard(board);
        playerBoards.put(player, board);
    }

    public void updateScoreboard(Player player) {
        if (!playerBoards.containsKey(player)) return;

        Scoreboard board = playerBoards.get(player);
        Objective obj = board.getObjective("paintball");
        if (obj == null) return;

        PlayerData data = gameManager.getPlayerData(player);

        // Limpiamos líneas antiguas
        for (String line : board.getEntries()) {
            board.resetScores(line);
        }

        obj.getScore("§7----------------").setScore(6);
        obj.getScore("Kills: §a" + data.getKills()).setScore(5);
        obj.getScore("Coins: §e" + data.getCoins()).setScore(4);
        obj.getScore("Snowballs: §f" + data.getSnowballs()).setScore(3);
        obj.getScore("Vivos: §c" + gameManager.getAliveCount()).setScore(2);
        obj.getScore("Tiempo: §b" + gameManager.getTime() + "s").setScore(1);
        obj.getScore("§7----------------").setScore(0);

        player.setScoreboard(board);
    }

    public void removeScoreboard(Player player) {
        if (!playerBoards.containsKey(player)) return;
        org.bukkit.scoreboard.Scoreboard emptyBoard = Bukkit.getScoreboardManager().getNewScoreboard();
        player.setScoreboard(emptyBoard);
        playerBoards.remove(player);
    }

    public void updateAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            updateScoreboard(player);
        }
    }
}
