package me.adrian.paintball.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ArenaPanelGUI {

    public void open(Player p){

        Inventory inv = Bukkit.createInventory(null, 27,"§8Editor de Arena");

        inv.setItem(10,item(Material.GOLD_BLOCK,"§ePosición 1","§7Click para guardar posición"));
        inv.setItem(12,item(Material.EMERALD_BLOCK,"§aPosición 2","§7Click para guardar posición"));
        inv.setItem(14,item(Material.NAME_TAG,"§bCambiar Nombre","§7Cambiar nombre de arena"));
        inv.setItem(16,item(Material.ARROW,"§7Volver","§7Regresar menú"));

        p.openInventory(inv);

    }

    private ItemStack item(Material mat,String name,String lore){

        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;

    }

}
