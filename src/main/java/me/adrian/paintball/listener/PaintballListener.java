package me.adrian.paintball.listener;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.Arena;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.game.GameManager.GameTeam;
import me.adrian.paintball.utils.CoinsManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.Color;

public class PaintballListener implements Listener {

    private final PaintballPlugin plugin;
    private final GameManager gameManager;

    public PaintballListener(PaintballPlugin plugin) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
    }

    // ---------------- Player Join ---------------- //
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        // Mensaje premium
        p.sendMessage("§6[PaintballAdvanced] §aBienvenido a Paintball!");
    }

    // ---------------- Player Quit ---------------- //
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (gameManager.isPlaying(p)) {
            // Si se va de la arena, el otro equipo gana
            Arena arena = gameManager.getCurrentArena();
            if (arena != null) {
                arena.endGame();
            }
            // Limpiar inventario
            p.getInventory().clear();
        }
    }

    // ---------------- Kill con Snowball ---------------- //
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player victim = (Player) e.getEntity();

        if (e.getDamager() instanceof Snowball) {
            Snowball ball = (Snowball) e.getDamager();

            if (ball.getShooter() instanceof Player) {
                Player killer = (Player) ball.getShooter();

                // Efecto trueno
                victim.getWorld().strikeLightningEffect(victim.getLocation());

                // Coins por kill
                CoinsManager.addCoins(killer,3);
                killer.sendMessage("§6[PaintballAdvanced] §a+3 Coins");

                // Eliminar jugador de la arena
                gameManager.removePlayer(victim);

                // Limpiar inventario
                victim.getInventory().clear();
                victim.sendMessage("§6[PaintballAdvanced] §cFuiste eliminado!");
            }
        }
    }

    // ---------------- Entrar a Arena ---------------- //
    public void addPlayerToArena(Player p, GameTeam team) {
        // Evitar items previos
        p.getInventory().clear();

        // Registrar jugador
        gameManager.addPlayer(p);

        // Dar snowballs
        p.getInventory().addItem(new ItemStack(Material.SNOWBALL,64));
        p.getInventory().addItem(new ItemStack(Material.SNOWBALL,64));

        // Armadura automática según equipo
        Color color = team == GameTeam.BLUE ? Color.BLUE : Color.RED;

        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta meta = (LeatherArmorMeta) helmet.getItemMeta();
        meta.setColor(color);
        helmet.setItemMeta(meta);

        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        meta = (LeatherArmorMeta) chest.getItemMeta();
        meta.setColor(color);
        chest.setItemMeta(meta);

        ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
        meta = (LeatherArmorMeta) legs.getItemMeta();
        meta.setColor(color);
        legs.setItemMeta(meta);

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        meta = (LeatherArmorMeta) boots.getItemMeta();
        meta.setColor(color);
        boots.setItemMeta(meta);

        p.getInventory().setHelmet(helmet);
        p.getInventory().setChestplate(chest);
        p.getInventory().setLeggings(legs);
        p.getInventory().setBoots(boots);

        // Mensaje premium
        p.sendMessage("§6[PaintballAdvanced] §aEntraste a la arena!");
    }
}
