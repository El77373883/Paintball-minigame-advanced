package me.adrian.paintball.gui;

import me.adrian.paintball.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopGUI {

    private final GameManager gm;

    public ShopGUI(GameManager gm) {
        this.gm = gm;
    }

    public void open(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, "Tienda de Paintball");

        ItemStack snowballs = new ItemStack(Material.SNOWBALL, 32);
        ItemMeta meta = snowballs.getItemMeta();
        if (meta != null) meta.setDisplayName("§aComprar 32 bolas - 5 Coins");
        snowballs.setItemMeta(meta);

        inv.setItem(4, snowballs);
        p.openInventory(inv);
    }

    public void buy(Player p) {
        int coins = gm.getCoins(p);
        if (coins >= 5) {
            gm.getCoins(p); // actualizar
            p.getInventory().addItem(new ItemStack(Material.SNOWBALL, 32));
            p.sendMessage("§aHas comprado 32 bolas de nieve!");
        } else {
            p.sendMessage("§cNo tienes suficientes monedas!");
        }
    }
}
