package me.adrian.paintball.listener;

import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.game.GameManager.GameTeam;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class PaintballListener implements Listener {

    private final GameManager gameManager;

    public PaintballListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onSnowballHit(ProjectileHitEvent e) {
        if (!(e.getEntity() instanceof Snowball)) return;
        if (!(e.getHitEntity() instanceof Player)) return;

        Player shooter = (Player) ((Snowball) e.getEntity()).getShooter();
        Player hit = (Player) e.getHitEntity();

        eliminatePlayer(shooter, hit);
    }

    public void eliminatePlayer(Player shooter, Player eliminated) {
        if (shooter == null || eliminated == null) return;
        gameManager.eliminatePlayer(shooter, eliminated);

        Location loc = eliminated.getLocation();
        var world = loc.getWorld();
        if (world != null) {
            world.spawnParticle(Particle.CRIT, loc, 30, 0.5, 0.5, 0.5, 0.1);
            world.playSound(loc, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 1.0f);
            world.strikeLightningEffect(loc);
        }
    }
}
