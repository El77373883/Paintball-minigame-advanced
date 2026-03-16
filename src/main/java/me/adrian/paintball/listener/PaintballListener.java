package me.adrian.paintball.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.game.GameManager.GameState;
import me.adrian.paintball.game.GameManager.GameTeam;

public class PaintballListener implements Listener {

    private final GameManager gameManager;

    public PaintballListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    // Detecta cuando la bola de nieve golpea a un jugador
    @EventHandler
    public void onSnowballHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Snowball)) return;
        if (!(event.getHitEntity() instanceof Player)) return;

        Player shooter = (Player) ((Snowball) event.getEntity()).getShooter();
        Player hit = (Player) event.getHitEntity();

        if (shooter == null || hit == null) return;
        if (!gameManager.isPlaying(shooter) || !gameManager.isPlaying(hit)) return;
        if (!gameManager.isAlive(hit)) return;

        // Verifica si el shooter aún puede tirar bolas
        if (!gameManager.canThrowSnowball(shooter)) return;
        gameManager.throwSnowball(shooter);

        // Elimina al jugador golpeado
        eliminatePlayer(shooter, hit);
    }

    private void eliminatePlayer(Player shooter, Player eliminated) {
        gameManager.eliminate(shooter, eliminated);

        Location loc = eliminated.getLocation();
        var world = loc.getWorld();

        if (world != null) {
            // Partículas y efectos de rayos
            world.spawnParticle(Particle.CRIT_MAGIC, loc, 30, 0.5, 0.5, 0.5, 0.1);
            world.playSound(loc, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 1.0f);
            world.strikeLightningEffect(loc);
        }

        // Mensaje de eliminación
        shooter.sendMessage("§aHas eliminado a §b" + eliminated.getName() + "§a!");
        eliminated.sendMessage("§cHas sido eliminado por §b" + shooter.getName() + "§c!");
    }

    // Detecta clicks para recargar bolas con item o abrir GUI
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null) return;

        // Ejemplo: abrir GUI si clickea con item de tienda
        // Aquí se integraría tu ShopGUI
    }
}
