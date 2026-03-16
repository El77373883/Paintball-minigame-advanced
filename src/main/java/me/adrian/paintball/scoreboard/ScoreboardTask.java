package me.adrian.paintball.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;

public class ScoreboardTask extends BukkitRunnable {

    private final PaintballPlugin plugin;
    private final GameManager gameManager;
    private int time = 300; // 5 minutes

    public ScoreboardTask(PaintballPlugin plugin, GameManager gm) {
        this.plugin = plugin;
        this.gameManager = gm;
    }

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendActionBar("Kills: "+gameManager.getKills(p)+" | Coins: "+gameManager.getCoins(p)+" | Time: "+time);

            // Countdown sounds and titles
            if(time <=10 && time>0){
                p.sendTitle("Comienza en", String.valueOf(time), 0, 20,0);
                p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING,1f,1f);
            }

            if(time>0) time--;
        }
    }
}
