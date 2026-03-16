package me.adrian.paintball.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.gui.ShopGUI;
import me.adrian.paintball.gui.ArenaPanelGUI;
import me.adrian.paintball.game.Arena;

public class PaintballListener implements Listener {

    private final GameManager gameManager;
    private final ShopGUI shopGUI;
    private final ArenaPanelGUI panelGUI;

    public PaintballListener(GameManager gm) {
        this.gameManager = gm;
        this.shopGUI = new ShopGUI(gm);
        this.panelGUI = new ArenaPanelGUI();
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

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);

        String title = e.getView().getTitle();

        if (title.equals("§6Tienda de Paintball")) {
            shopGUI.handleClick(p, e.getSlot());
        } else if (title.startsWith("§6Panel Arena: ")) {
            Arena arena = gameManager.getArena(p);
            panelGUI.handleClick(p, e.getSlot(), arena);
        }
    }
}
