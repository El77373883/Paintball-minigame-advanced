package me.adrian.paintball.listener;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.shop.ShopGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShopListener implements Listener {

    private final PaintballPlugin plugin;

    public ShopListener(PaintballPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onShopClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        if (item == null) return;
        if (e.getView().getTitle().contains("Paintball Shop")) {
            e.setCancelled(true);

            ShopGUI shop = new ShopGUI(plugin.getGameManager());
            shop.buy(p,item);
        }
    }
}
