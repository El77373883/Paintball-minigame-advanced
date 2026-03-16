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

        int kills = data.getKills();
        int coins = data.getCoins();
        int alive = gameManager.getAliveCount();
        int time = gameManager.getTime();

        // Aquí pondrías tu lógica de Scoreboard
        player.sendMessage("§6=== Scoreboard ===");
        player.sendMessage("§eKills: §f" + kills);
        player.sendMessage("§eCoins: §f" + coins);
        player.sendMessage("§eJugadores vivos: §f" + alive);
        player.sendMessage("§eTiempo restante: §f" + time + "s");
    }
}
