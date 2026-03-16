package me.adrian.paintball.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.adrian.paintball.utils.CoinsManager;

public class ShopMenu {

    public static void openShop(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, "§6Tienda Paintball");

        ItemStack snowballs = new ItemStack(Material.SNOWBALL, 32);
        ItemMeta meta = snowballs.getItemMeta();
        meta.setDisplayName("§bComprar 32 bolas - 10 coins");
        snowballs.setItemMeta(meta);

        inv.setItem(4, snowballs);

        player.openInventory(inv);
    }

    public static void handleClick(Player player, int slot) {
        if (slot == 4) {
            if (CoinsManager.removeCoins(player, 10)) {
                player.getInventory().addItem(new ItemStack(Material.SNOWBALL, 32));
                player.sendMessage("§aCompraste 32 bolas de nieve!");
            } else {
                player.sendMessage("§cNo tienes suficientes coins!");
            }
        }
    }
}
