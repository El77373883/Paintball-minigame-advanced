package me.adrian.paintball.menu;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.game.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ArenaPanelGUI {

    private final PaintballPlugin plugin;
    private final GameManager gameManager;

    public ArenaPanelGUI(PaintballPlugin plugin) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
    }

    public void open(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9*3, "§6Selector de Arenas");

        int slot = 0;
        for (Arena arena : gameManager.getArenas()) {
            ItemStack item = new ItemStack(Material.MAP);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§aArena: " + arena.getName());
            List<String> lore = new ArrayList<>();
            lore.add("§7Pos1: " + arena.getPos1());
            lore.add("§7Pos2: " + arena.getPos2());
            lore.add("§7Tiempo: " + arena.getTime() + " seg");
            lore.add("§7Max Players: " + arena.getMaxPlayers());
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(slot, item);
            slot++;
        }

        // Botón Volver
        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta meta = back.getItemMeta();
        meta.setDisplayName("§4Volver");
        back.setItemMeta(meta);
        inv.setItem(8, back);

        p.openInventory(inv);
    }
}
