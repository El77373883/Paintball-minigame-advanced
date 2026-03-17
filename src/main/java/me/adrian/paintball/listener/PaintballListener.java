package me.adrian.paintball.listener;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class PaintballListener implements Listener {

    private PaintballPlugin plugin;

    public PaintballListener(PaintballPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onSnowballHit(EntityDamageByEntityEvent e){

        if(!(e.getDamager() instanceof Snowball)) return;
        if(!(e.getEntity() instanceof Player)) return;

        Snowball ball = (Snowball) e.getDamager();

        if(!(ball.getShooter() instanceof Player)) return;

        Player shooter = (Player) ball.getShooter();
        Player victim = (Player) e.getEntity();

        e.setCancelled(true);

        victim.setHealth(0);

        GameManager gm = plugin.getGameManager();

        gm.giveKill(shooter);

        victim.getWorld().strikeLightningEffect(victim.getLocation());

        shooter.playSound(shooter.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);

    }

}
