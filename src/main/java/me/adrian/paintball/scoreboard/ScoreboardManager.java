package me.adrian.paintball.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import me.adrian.paintball.game.GameManager;

public class ScoreboardManager {

    private final GameManager gameManager;

    public ScoreboardManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void update(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("paintball", "dummy", "§6Paintball Minigame");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore("§7Jugador: §f" + player.getName()).setScore(7);
        obj.getScore("§7Equipo: §f" + gameManager.getTeam(player)).setScore(6);
        obj.getScore("§7Kills: §f" + gameManager.getKills(player)).setScore(5);
        obj.getScore("§7Vivos: §f" + gameManager.getAliveCount()).setScore(4);
        obj.getScore("§7Tiempo: §f" + gameManager.getTime()).setScore(3);
        obj.getScore("§7Coins: §f" + gameManager.getPlayerData(player).getCoins()).setScore(2);
        obj.getScore("§bBienvenido!").setScore(1);

        player.setScoreboard(board);
    }
}
