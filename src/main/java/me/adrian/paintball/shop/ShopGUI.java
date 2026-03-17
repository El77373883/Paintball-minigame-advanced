package me.adrian.paintball.shop;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.game.PlayerData;
import me.adrian.paintball.utils.CoinsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ShopGUI implements Listener {

    private final PaintballPlugin plugin;
    private final GameManager gameManager;

    public ShopGUI(GameManager gameManager) {
        this.plugin = PaintballPlugin.getInstance();
        this.gameManager = gameManager;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void openShop(Player player) {
        Inventory shop = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Paintball Shop");

        // Item 1: 16 Snowballs por 5 Coins
        shop.setItem(0, createItem(Material.SNOWBALL, ChatColor.AQUA + "16 Snowballs", ChatColor.YELLOW + "Precio: 5 Coins"));

        // Item 2: 32 Snowballs por 10 Coins
        shop.setItem(1, createItem(Material.SNOWBALL, ChatColor.AQUA + "32 Snowballs", ChatColor.YELLOW + "Precio: 10 Coins"));

        // Item 3: Mejora de arma (Ejemplo)
        shop.setItem(2, createItem(Material.DIAMOND_SWORD, ChatColor.RED + "Mejora de arma", ChatColor.YELLOW + "Precio: 20 Coins"));

        player.openInventory(shop);
    }

    private ItemStack createItem(Material material, String name, String loreString) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            List<String> lore = new ArrayList<>();
            lore.add(loreString);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player p = (Player) e.getWhoClicked();

        if (e.getInventory() == null || e.getCurrentItem() == null) return;

        String title = e.getView().getTitle();
        if (!title.equals(ChatColor.GOLD + "Paintball Shop")) return;

        e.setCancelled(true);

        PlayerData data = gameManager.getPlayerData(p);

        Material type = e.getCurrentItem().getType();
        ItemMeta meta = e.getCurrentItem().getItemMeta();
        if (meta == null) return;

        String displayName = meta.getDisplayName();

        switch (type) {
            case SNOWBALL:
                if (displayName.contains("16")) {
                    if (data.getCoins() >= 5) {
                        data.removeCoins(5);
                        p.getInventory().addItem(new ItemStack(Material.SNOWBALL, 16));
                        p.sendMessage(ChatColor.GREEN + "Compra realizada correctamente: 16 Snowballs");
                    } else {
                        p.sendMessage(ChatColor.RED + "No tienes suficientes coins.");
                    }
                } else if (displayName.contains("32")) {
                    if (data.getCoins() >= 10) {
                        data.removeCoins(10);
                        p.getInventory().addItem(new ItemStack(Material.SNOWBALL, 32));
                        p.sendMessage(ChatColor.GREEN + "Compra realizada correctamente: 32 Snowballs");
                    } else {
                        p.sendMessage(ChatColor.RED + "No tienes suficientes coins.");
                    }
                }
                break;
            case DIAMOND_SWORD:
                if (displayName.contains("Mejora de arma")) {
                    if (data.getCoins() >= 20) {
                        data.removeCoins(20);
                        // Aquí puedes agregar la lógica para mejorar arma
                        p.sendMessage(ChatColor.GREEN + "Compra realizada correctamente: Mejora de arma");
                    } else {
                        p.sendMessage(ChatColor.RED + "No tienes suficientes coins.");
                    }
                }
                break;
            default:
                break;
        }
        p.updateInventory();
    }
}
