package me.adrian.paintball.shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.adrian.paintball.game.GameManager;

import java.util.ArrayList;
import java.util.List;

public class ShopGUI {

    private final GameManager gameManager;

    public ShopGUI(GameManager gm) {
        this.gameManager = gm;
    }

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, "§6Tienda de Paintball");

        ItemStack snowball = new ItemStack(Material.SNOWBALL, 32);
        ItemMeta meta = snowball.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§b32 Snowballs");
            List<String> lore = new ArrayList<>();
            lore.add("§7Precio: 4 Coins");
            meta.setLore(lore);
            snowball.setItemMeta(meta);
        }

        inv.setItem(4, snowball);
        player.openInventory(inv);
    }

    public boolean handleClick(Player player, int slot) {
        if (slot == 4) { // Snowballs
            int coins = gameManager.getCoins(player);
            if (coins >= 4) {
                gameManager.removeCoins(player, 4);
                player.getInventory().addItem(new ItemStack(Material.SNOWBALL, 32));
                player.sendMessage("§aHas comprado 32 Snowballs por 4 Coins!");
            } else {
                player.sendMessage("§cNo tienes suficientes Coins!");
            }
            return true;
        }
        return false;
    }
}
