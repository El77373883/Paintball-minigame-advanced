package me.adrian.paintball.game;

import me.adrian.paintball.PaintballPlugin;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTimerTask extends BukkitRunnable {

    private int time = 300;
    private Arena arena;
    private PaintballPlugin plugin;

    public GameTimerTask(PaintballPlugin plugin, Arena arena){
        this.plugin = plugin;
        this.arena = arena;
    }

    @Override
    public void run(){

        if(time <= 0){

            endGame();
            cancel();
            return;

        }

        time--;

    }

    private void endGame(){

        if(arena.getPlayers().isEmpty()) return;

        Location loc = arena.getPlayers().get(0).getLocation();

        Firework fw = loc.getWorld().spawn(loc, Firework.class);
        FireworkMeta meta = fw.getFireworkMeta();

        meta.addEffect(FireworkEffect.builder().flicker(true).build());
        meta.setPower(1);

        fw.setFireworkMeta(meta);

        arena.reset();

    }

}
