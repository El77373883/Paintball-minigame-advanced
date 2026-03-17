package me.adrian.paintball.scoreboard;

import me.adrian.paintball.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {

    public static void update(Player p, GameManager gameManager){

        org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();

        Scoreboard board = manager.getNewScoreboard();

        Objective obj = board.registerNewObjective("paint","dummy","§bPaintballAdvanced");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore("§7").setScore(5);
        obj.getScore("§fJugador: §a"+p.getName()).setScore(4);
        obj.getScore("§fKills: §e0").setScore(3);
        obj.getScore("§fCoins: §6"+gameManager.getCoins(p)).setScore(2);
        obj.getScore("§7play.server").setScore(1);

        p.setScoreboard(board);

    }

}
