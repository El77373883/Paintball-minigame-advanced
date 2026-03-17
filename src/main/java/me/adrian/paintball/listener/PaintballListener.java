package me.adrian.paintball.listener;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.utils.CoinsManager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PaintballListener implements Listener {

    private PaintballPlugin plugin;

    public PaintballListener(PaintballPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onSnowballHit(EntityDamageByEntityEvent e){

        if(!(e.getEntity() instanceof Player)) return;

        Player victim = (Player) e.getEntity();

        if(!(e.getDamager() instanceof Snowball)) return;

        Snowball ball = (Snowball) e.getDamager();

        if(!(ball.getShooter() instanceof Player)) return;

        Player killer = (Player) ball.getShooter();

        e.setCancelled(true);

        if(victim.equals(killer)) return;

        // efecto trueno
        victim.getWorld().strikeLightningEffect(victim.getLocation());

        // matar jugador
        victim.setHealth(0);

        // coins
        CoinsManager.addCoins(killer,3);

        killer.sendMessage("§6+3 coins por eliminación");
    }

}
