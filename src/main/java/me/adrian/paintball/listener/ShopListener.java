package me.adrian.paintball.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.shop.ShopGUI;

public class PaintballListener implements Listener {

    private final GameManager gameManager;
    private final ShopGUI shopGUI;

    // Constructor
    public PaintballListener(GameManager gm) {
        this.gameManager = gm;
        this.shopGUI = new ShopGUI(gm); // Inicializamos la tienda
    }

    // Abre la tienda al hacer clic derecho con un EMERALD
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getInventory().getItemInMainHand().getType() == Material.EMERALD) {
            shopGUI.open(player);
        }
    }

    // Maneja los clicks dentro de la tienda
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        if (event.getView().title().equals("§6Tienda de Paintball")) {
            event.setCancelled(true);
            int slot = event.getSlot();
            shopGUI.handleClick(player, slot);
        }
    }
}
