package me.adrian.paintball.scoreboard;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.game.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class ScoreboardTask extends BukkitRunnable {

    private final GameManager gameManager;

    public ScoreboardTask(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void run() {

        for (Player player : Bukkit.getOnlinePlayers()) {

            PlayerData data = gameManager.getPlayerData(player);

            // Crear scoreboard
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            if (manager == null) continue;

            Scoreboard board = manager.getNewScoreboard();

            Objective objective = board.registerNewObjective("paintball", "dummy", "§6§lPaintballAdvanced");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            int line = 10;

            // Kills
            Score kills = objective.getScore("§cKills: §f" + gameManager.getKills(player));
            kills.setScore(line--);

            // Coins
            Score coins = objective.getScore("§6Coins: §f" + gameManager.getCoins(player));
            coins.setScore(line--);

            // Jugadores vivos
            Score alive = objective.getScore("§aVivos: §f" + gameManager.getAliveCount());
            alive.setScore(line--);

            // Tiempo restante
            Score time = objective.getScore("§bTiempo: §f" + formatTime(gameManager.getTime()));
            time.setScore(line--);

            // Arena actual
            if (gameManager.getCurrentArena() != null) {
                Score arena = objective.getScore("§eArena: §f" + gameManager.getCurrentArena().getName());
                arena.setScore(line--);
            }

            player.setScoreboard(board);
        }

    }

    private String formatTime(int seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;
        return String.format("%02d:%02d", min, sec);
    }
}
