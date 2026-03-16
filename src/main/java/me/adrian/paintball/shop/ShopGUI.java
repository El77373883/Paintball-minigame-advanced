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

    // Constructor recibe GameManager
    public ShopGUI(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    // Abrir la tienda
    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, "§6Tienda de Paintball");

        // Item de snowballs
        ItemStack snowball = new ItemStack(Material.SNOWBALL, 32);
        ItemMeta meta = snowball.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§b32 Snowballs");
            List<String> lore = new ArrayList<>();
            lore.add("§7Precio: 4 Coins");
            lore.add("§7Tienes: " + gameManager.getCoins(player) + " Coins");
            meta.setLore(lore);
            snowball.setItemMeta(meta);
        }

        inv.setItem(4, snowball); // Slot central
        player.openInventory(inv);
    }

    // Manejar clicks dentro de la tienda
    public boolean handleClick(Player player, int slot) {
        if (slot == 4) { // Snowballs
            int coins = gameManager.getCoins(player);
            if (coins >= 4) {
                // Quitar coins
                gameManager.removeCoins(player, 4);
                // Dar snowballs
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
