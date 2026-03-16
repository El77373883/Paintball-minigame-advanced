package me.adrian.paintball.gui;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.Arena;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.game.GameTeam; // <-- importar el enum separado
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collection;

public class AdminPanelGUI {

    private final PaintballPlugin plugin;

    public AdminPanelGUI(PaintballPlugin plugin) {
        this.plugin = plugin;
    }

    public void open(Player player) {
        Collection<Arena> arenas = plugin.getGameManager().getArenas();
        Inventory menu = Bukkit.createInventory(null, 9 * ((arenas.size() + 8) / 9), ChatColor.DARK_PURPLE + "Panel Admin Paintball");

        for (Arena arena : arenas) {
            ItemStack item = new ItemStack(Material.BANNER);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + arena.getName());
            meta.setLore(java.util.List.of(
                    ChatColor.YELLOW + "Jugadores conectados: " + arena.getPlayers().size(),
                    ChatColor.GRAY + "Click para administrar"
            ));
            item.setItemMeta(meta);
            menu.addItem(item);
        }

        player.openInventory(menu);
    }
}
