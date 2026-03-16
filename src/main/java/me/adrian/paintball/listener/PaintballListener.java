package me.adrian.paintball.listener;

import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.gui.ShopGUI;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;

public class PaintballListener implements Listener {

    private final GameManager gm;
    private final ShopGUI shop;

    public PaintballListener(GameManager gm) {
        this.gm = gm;
        this.shop = new ShopGUI(gm);
    }

    @EventHandler
    public void onSnowballHit(ProjectileHitEvent e) {
        if (!(e.getEntity() instanceof Snowball)) return;
        if (!(e.getHitEntity() instanceof Player hit)) return;
        if (!(e.getEntity().getShooter() instanceof Player shooter)) return;

        gm.eliminate(shooter, hit);
    }
}
