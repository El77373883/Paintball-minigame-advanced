package me.adrian.paintball.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.adrian.paintball.game.Arena;

import java.util.ArrayList;
import java.util.List;

public class ArenaPanel {

    private final Player player;
    private final Arena arena;

    public ArenaPanel(Player player, Arena arena) {
        this.player = player;
        this.arena = arena;
    }

    public void open() {
        Inventory inv = Bukkit.createInventory(null, 27, "§6Editar Arena: " + arena.getName());

        inv.setItem(11, createItem(Material.GREEN_WOOL, "§aSpawn Team Verde"));
        inv.setItem(15, createItem(Material.BLUE_WOOL, "§9Spawn Team Azul"));
        inv.setItem(13, createItem(Material.DIAMOND, "§bConfigurar equipo y max jugadores"));

        player.openInventory(inv);
    }

    private ItemStack createItem(Material mat, String name) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            List<String> lore = new ArrayList<>();
            lore.add("§7Haz click para editar");
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }
}
