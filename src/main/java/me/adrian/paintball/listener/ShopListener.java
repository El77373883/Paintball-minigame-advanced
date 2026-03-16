package me.adrian.paintball.listener;

import me.adrian.paintball.shop.ShopGUI;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.PaintballPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ShopListener implements Listener {

    private final GameManager gm = PaintballPlugin.getInstance().getGameManager();
    private final ShopGUI shop = new ShopGUI();

    @EventHandler
    public void onShopClick(InventoryClickEvent e) {
        if (!e.getView().getTitle().equals("§6Tienda de Paintball")) return;

        e.setCancelled(true);
        if (!(e.getWhoClicked() instanceof Player p)) return;
        ItemStack item = e.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) return;

        if (item.getType() == Material.SNOWBALL) {
            if (gm.getPlayerStats(p).removeCoins(5)) {
                p.getInventory().addItem(new ItemStack(Material.SNOWBALL, 32));
                p.sendMessage("§aCompraste 32 bolas de nieve!");
            } else p.sendMessage("§cNo tienes suficientes coins!");
        }
    }
}
