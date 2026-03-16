package me.adrian.paintball.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.FireworkEffect;
import org.bukkit.inventory.meta.FireworkMeta;

public class GameStartTask extends BukkitRunnable {

    private final PaintballPlugin plugin;
    private final GameManager gm;
    private int countdown = 10;

    public GameStartTask(PaintballPlugin plugin, GameManager gm) {
        this.plugin = plugin;
        this.gm = gm;
    }

    @Override
    public void run() {
        if (countdown > 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendTitle("§eComienza en", "§c" + countdown, 0, 20, 0);
                p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
            countdown--;
        } else {
            this.cancel();
            // Partida comenzada, al final se pueden lanzar fuegos artificiales
            for (Player p : Bukkit.getOnlinePlayers()) {
                Location loc = p.getLocation();
                Firework fw = (Firework) p.getWorld().spawn(loc, Firework.class);
                FireworkMeta meta = fw.getFireworkMeta();
                meta.addEffect(FireworkEffect.builder().withColor(org.bukkit.Color.AQUA).withTrail().build());
                fw.setFireworkMeta(meta);
            }
        }
    }
}
