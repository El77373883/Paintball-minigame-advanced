package me.adrian.paintball.shop;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopGUI {

    private final GameManager gm = PaintballPlugin.getInstance().getGameManager();

    public void openShop(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, "§6Tienda de Paintball");

        ItemStack snowballs = new ItemStack(Material.SNOWBALL, 32);
        ItemMeta meta = snowballs.getItemMeta();
        meta.setDisplayName("§bComprar 32 bolas de nieve (5 coins)");
        snowballs.setItemMeta(meta);

        inv.setItem(0, snowballs);
        p.openInventory(inv);
    }
}
