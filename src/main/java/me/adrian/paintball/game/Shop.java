package me.adrian.paintball.game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;

public class Shop {

    private final Player player;
    private final GameManager gameManager;

    public Shop(Player player, GameManager gameManager) {
        this.player = player;
        this.gameManager = gameManager;
    }

    public void open() {
        Inventory inv = Bukkit.createInventory(null, 9, "§6Tienda de Bolas de Nieve");

        ItemStack snowball = new ItemStack(Material.SNOWBALL);
        ItemMeta meta = snowball.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§bComprar 10 Bolas de Nieve - 20 Coins");

            List<String> lore = new ArrayList<>();
            lore.add("§7Tienes: " + getCoins() + " coins");
            meta.setLore(lore);
            snowball.setItemMeta(meta);
        }

        inv.setItem(4, snowball);
        player.openInventory(inv);
    }

    private int getCoins() {
        return gameManager.getCoins(player);
    }

    public boolean buySnowballs(int amount, int cost) {
        int coins = gameManager.getCoins(player);
        if (coins >= cost) {
            gameManager.removeCoins(player, cost);
            player.getInventory().addItem(new ItemStack(Material.SNOWBALL, amount));
            player.sendMessage("§aHas comprado " + amount + " Snowballs por " + cost + " Coins!");
            return true;
        } else {
            player.sendMessage("§cNo tienes suficientes Coins!");
            return false;
        }
    }
}
