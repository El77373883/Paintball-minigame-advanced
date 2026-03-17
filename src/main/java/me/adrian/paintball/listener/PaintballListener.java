package me.adrian.paintball.listener;

import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.game.GameManager.GameTeam;
import me.adrian.paintball.utils.CoinsManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.Color;

import me.adrian.paintball.PaintballPlugin;

public class PaintballListener implements Listener {

    private final GameManager gameManager;

    public PaintballListener(PaintballPlugin plugin) {
        this.gameManager = plugin.getGameManager();
    }

    // Cuando el jugador entra a la arena (invocar esto desde tu comando o admin panel)
    public void addPlayer(Player p) {
        // Añadir a la lista de jugadores
        gameManager.addPlayer(p);

        // Limpiar inventario
        p.getInventory().clear();

        // Dar snowballs
        p.getInventory().addItem(new ItemStack(Material.SNOWBALL, 64));
        p.getInventory().addItem(new ItemStack(Material.SNOWBALL, 64));

        // Armadura del team automáticamente
        GameTeam team = gameManager.getTeam(p);
        Color color = team == GameTeam.BLUE ? Color.BLUE : Color.RED;

        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

        LeatherArmorMeta meta;

        meta = (LeatherArmorMeta) helmet.getItemMeta();
        meta.setColor(color);
        helmet.setItemMeta(meta);

        meta = (LeatherArmorMeta) chest.getItemMeta();
        meta.setColor(color);
        chest.setItemMeta(meta);

        meta = (LeatherArmorMeta) legs.getItemMeta();
        meta.setColor(color);
        legs.setItemMeta(meta);

        meta = (LeatherArmorMeta) boots.getItemMeta();
        meta.setColor(color);
        boots.setItemMeta(meta);

        p.getInventory().setHelmet(helmet);
        p.getInventory().setChestplate(chest);
        p.getInventory().setLeggings(legs);
        p.getInventory().setBoots(boots);

        p.sendMessage("§6[PaintballAdvanced] §a¡Has entrado a la arena!");
    }

    @EventHandler
    public void onSnowballHit(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        Player victim = (Player) e.getEntity();

        if (!(e.getDamager() instanceof Snowball)) return;

        Snowball ball = (Snowball) e.getDamager();

        if (!(ball.getShooter() instanceof Player)) return;

        Player killer = (Player) ball.getShooter();

        if (!gameManager.isAlive(victim) || !gameManager.isAlive(killer)) return;

        // Eliminar jugador
        gameManager.eliminate(killer, victim);

        // Efecto de trueno
        victim.getWorld().strikeLightningEffect(victim.getLocation());

        // Dar coins
        CoinsManager.addCoins(killer, 3);
        killer.sendMessage("§6+3 Coins por eliminar a " + victim.getName());

        victim.sendMessage("§c¡Has sido eliminado por " + killer.getName() + "!");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (gameManager.isPlaying(p)) {
            gameManager.removePlayer(p);
            // Si quieres, asignar victoria al otro equipo
            gameManager.getCurrentArena().checkWinCondition();
        }
    }
}
