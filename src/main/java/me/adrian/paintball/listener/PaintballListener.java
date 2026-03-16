package me.adrian.paintball.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.shop.ShopGUI;

public class PaintballListener implements Listener {

    private final GameManager gameManager;
    private final ShopGUI shopGUI;

    public PaintballListener(GameManager gm) {
        this.gameManager = gm;
        this.shopGUI = new ShopGUI(gm); // Inicializamos la tienda
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Abrir la tienda al hacer clic derecho con un EMERALD
        if (player.getInventory().getItemInMainHand().getType() == org.bukkit.Material.EMERALD) {
            shopGUI.open(player);
        }
    }
}
