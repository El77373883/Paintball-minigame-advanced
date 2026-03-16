package me.adrian.paintball.scoreboard;

import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.game.GameManager.GameTeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class ScoreboardTask extends BukkitRunnable {

    private final GameManager gm;

    public ScoreboardTask(GameManager gm) { this.gm = gm; }

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective obj = board.registerNewObjective("paintball", "dummy", "§6§lPaintball Minigame");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);

            Score kills = obj.getScore("Kills: " + gm.getKills(p));
            kills.setScore(3);

            GameTeam team = gm.getTeam(p);
            Score teamScore = obj.getScore("Team: " + (team != null ? team.name() : "Ninguno"));
            teamScore.setScore(2);

            Score alive = obj.getScore("Vivos: " + gm.getAlivePlayers().size());
            alive.setScore(1);

            p.setScoreboard(board);
        }
    }
}
