package me.adrian.paintball.scoreboard;

import me.adrian.paintball.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.List;

public class ScoreboardTask extends BukkitRunnable {

    private final GameManager gm;

    public ScoreboardTask(GameManager gm) {
        this.gm = gm;
    }

    @Override
    public void run() {
        if (gm.getState() != GameManager.GameState.IN_GAME) return;
        if (gm.getArena("default") == null) return;

        List<Player> players = gm.getArena("default").getAlivePlayers();
        for (Player p : players) {
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            if (manager == null) continue;
            Scoreboard board = manager.getNewScoreboard();
            Objective obj = board.registerNewObjective("paintball", "dummy", "§aPaintball Minigame");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);

            int kills = gm.getKills(p);
            String team = gm.getArena("default").getGreenTeam().contains(p) ? "Verde" : "Azul";
            int alive = gm.getArena("default").getAlivePlayers().size();

            obj.getScore("§7Equipo: §f" + team).setScore(3);
            obj.getScore("§7Kills: §f" + kills).setScore(2);
            obj.getScore("§7Vivos: §f" + alive).setScore(1);

            p.setScoreboard(board);
        }
    }
}
