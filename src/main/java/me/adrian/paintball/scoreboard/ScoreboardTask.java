package me.adrian.paintball.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.game.GameManager.GameTeam;

public class ScoreboardTask extends BukkitRunnable {

    private final GameManager gm;

    public ScoreboardTask(GameManager gm) {
        this.gm = gm;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!gm.isPlaying(player)) continue;

            ScoreboardManager manager = Bukkit.getScoreboardManager();
            if (manager == null) continue;

            Scoreboard board = manager.getNewScoreboard();
            Objective objective = board.registerNewObjective("paintball", "dummy", "§b§lPAINTBALL");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            // Equipo
            GameTeam team = gm.getTeam(player);
            String teamName = team == GameTeam.BLUE ? "§9Azul" : "§aVerde";
            Score teamScore = objective.getScore("§7Equipo: " + teamName);
            teamScore.setScore(6);

            // Kills
            int kills = gm.getKills(player);
            Score killsScore = objective.getScore("§7Kills: §e" + kills);
            killsScore.setScore(5);

            // Jugadores vivos
            int alive = gm.getAlivePlayers(team);
            Score aliveScore = objective.getScore("§7Vivos: §e" + alive);
            aliveScore.setScore(4);

            // Coins
            int coins = gm.getCoins(player);
            Score coinsScore = objective.getScore("§7Coins: §6" + coins);
            coinsScore.setScore(3);

            // Tiempo de juego
            int time = gm.getGameTime();
            int minutes = time / 60;
            int seconds = time % 60;
            Score timeScore = objective.getScore(String.format("§7Tiempo: §e%02d:%02d", minutes, seconds));
            timeScore.setScore(2);

            // Separador
            Score separator = objective.getScore("§f----------------");
            separator.setScore(1);

            player.setScoreboard(board);
        }
    }
}
