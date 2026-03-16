package me.adrian.paintball.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import me.adrian.paintball.shop.ShopGUI;

public class ShopListener implements Listener {

    private final ShopGUI shop;

    public ShopListener(ShopGUI shop) {
        this.shop = shop;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player player)) return;

        if (e.getView().title().equals("§bTienda Paintball")) {
            e.setCancelled(true);
            int slot = e.getSlot();
            shop.handleClick(player, slot);
        }
    }
}
