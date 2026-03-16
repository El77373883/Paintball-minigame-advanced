package me.adrian.paintball.gui;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.Arena;
import me.adrian.paintball.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminPanelGUI {

    private final GameManager gameManager;
    private final Inventory inventory;

    public AdminPanelGUI() {
        this.gameManager = PaintballPlugin.getInstance().getGameManager();
        this.inventory = Bukkit.createInventory(null, 9 * 3, ChatColor.DARK_RED + "Panel de Admin");
        build();
    }

    private void build() {
        for (Arena arena : gameManager.getArenas()) {
            ItemStack item = new ItemStack(Material.WHITE_BANNER);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + arena.getName());
            item.setItemMeta(meta);
            inventory.addItem(item);
        }
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    public void handleClick(InventoryClickEvent e) {
        e.setCancelled(true);
        Player player = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR) return;
        String arenaName = ChatColor.stripColor(clicked.getItemMeta().getDisplayName());
        Arena arena = gameManager.getArenas().stream()
                .filter(a -> a.getName().equalsIgnoreCase(arenaName))
                .findFirst().orElse(null);
        if (arena == null) return;
        player.sendMessage(ChatColor.GREEN + "Administrando arena: " + arena.getName());
    }
}
