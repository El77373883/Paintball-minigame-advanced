package me.adrian.paintball.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Player;
import me.adrian.paintball.game.GameManager;

public class PaintballListener implements Listener {

    private final GameManager gameManager;

    public PaintballListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onSnowballHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Snowball)) return;
        if (!(event.getHitEntity() instanceof Player)) return;

        Snowball snowball = (Snowball) event.getEntity();
        Player shooter = (Player) snowball.getShooter();
        Player hit = (Player) event.getHitEntity();

        gameManager.eliminate(shooter, hit);
    }
}
