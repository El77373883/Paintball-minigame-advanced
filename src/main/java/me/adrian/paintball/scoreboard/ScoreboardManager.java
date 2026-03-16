package me.adrian.paintball.scoreboard;

import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.game.PlayerData;
import org.bukkit.entity.Player;

public class ScoreboardManager {

    private final GameManager gameManager;

    public ScoreboardManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void updateScoreboard(Player player) {
        PlayerData data = gameManager.getPlayerData(player);
        if (data == null) return;

        int alive = gameManager.getAliveCount();
        int time = gameManager.getTime();
        int kills = data.getKills();
        int coins = data.getCoins();

        player.sendMessage("§6=== Scoreboard ===");
        player.sendMessage("§eVivos: §a" + alive);
        player.sendMessage("§eTiempo restante: §a" + time + "s");
        player.sendMessage("§eKills: §a" + kills);
        player.sendMessage("§eCoins: §a" + coins);
    }
}
