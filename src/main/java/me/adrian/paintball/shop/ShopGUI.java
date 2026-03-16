package me.adrian.paintball.shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.adrian.paintball.game.GameManager;

public class ShopGUI {

    private final GameManager gm;

    public ShopGUI(GameManager gm) {
        this.gm = gm;
    }

    public void openShop(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, "§bTienda Paintball");

        // Bola de nieve
        ItemStack snowballs = new ItemStack(Material.SNOWBALL, 32);
        ItemMeta snowMeta = snowballs.getItemMeta();
        snowMeta.setDisplayName("§fComprar 32 bolas de nieve §6- 10 coins");
        snowballs.setItemMeta(snowMeta);
        inv.setItem(0, snowballs);

        // Ejemplo: efectos
        ItemStack lightning = new ItemStack(Material.GOLDEN_CARROT);
        ItemMeta lMeta = lightning.getItemMeta();
        lMeta.setDisplayName("§fEfecto Trueno §6- 20 coins");
        lightning.setItemMeta(lMeta);
        inv.setItem(1, lightning);

        player.openInventory(inv);
    }

    public void handleClick(Player player, int slot) {
        int coins = gm.getCoins(player);

        switch (slot) {
            case 0 -> { // Bolas de nieve
                if (coins >= 10) {
                    gm.addSnowballs(player, 32);
                    gm.removeCoins(player, 10);
                    player.sendMessage("§aCompraste 32 bolas de nieve!");
                    player.closeInventory();
                } else {
                    player.sendMessage("§cNo tienes suficientes coins.");
                }
            }
            case 1 -> { // Efecto Trueno
                if (coins >= 20) {
                    gm.setPlayerEffect(player, "LIGHTNING");
                    gm.removeCoins(player, 20);
                    player.sendMessage("§aCompraste el efecto Trueno!");
                    player.closeInventory();
                } else {
                    player.sendMessage("§cNo tienes suficientes coins.");
                }
            }
        }
    }
}
