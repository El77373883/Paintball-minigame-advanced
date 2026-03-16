package me.adrian.paintball.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.adrian.paintball.game.Arena;

import java.util.ArrayList;
import java.util.List;

public class ArenaPanelGUI {

    public void open(Player player, Arena arena) {
        Inventory inv = Bukkit.createInventory(null, 27, "§6Panel Arena: " + arena.getName());

        // Vidrios decorativos
        ItemStack glass = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);
        for (int i = 0; i < 27; i++) {
            inv.setItem(i, glass);
        }

        // Flecha regresar
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§cRegresar");
        back.setItemMeta(backMeta);
        inv.setItem(18, back);

        // Spawn Azul
        ItemStack blueSpawn = new ItemStack(Material.BLUE_WOOL);
        ItemMeta blueMeta = blueSpawn.getItemMeta();
        blueMeta.setDisplayName("§bSet Spawn Azul");
        List<String> lore = new ArrayList<>();
        lore.add("§7Click para editar spawn azul");
        blueMeta.setLore(lore);
        blueSpawn.setItemMeta(blueMeta);
        inv.setItem(10, blueSpawn);

        // Spawn Verde
        ItemStack greenSpawn = new ItemStack(Material.GREEN_WOOL);
        ItemMeta greenMeta = greenSpawn.getItemMeta();
        greenMeta.setDisplayName("§aSet Spawn Verde");
        lore = new ArrayList<>();
        lore.add("§7Click para editar spawn verde");
        greenMeta.setLore(lore);
        greenSpawn.setItemMeta(greenMeta);
        inv.setItem(12, greenSpawn);

        player.openInventory(inv);
    }

    public boolean handleClick(Player player, int slot, Arena arena) {
        switch (slot) {
            case 18: // Flecha regresar
                player.closeInventory();
                return true;
            case 10:
                player.sendMessage("§bAhora selecciona el spawn azul con tu varita.");
                return true;
            case 12:
                player.sendMessage("§aAhora selecciona el spawn verde con tu varita.");
                return true;
        }
        return false;
    }
}
