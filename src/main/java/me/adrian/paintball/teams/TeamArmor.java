package me.adrian.paintball.game;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class TeamArmor {

    public static void equipBlue(Player p){

        p.getInventory().setHelmet(create(Material.LEATHER_HELMET, Color.BLUE));
        p.getInventory().setChestplate(create(Material.LEATHER_CHESTPLATE, Color.BLUE));
        p.getInventory().setLeggings(create(Material.LEATHER_LEGGINGS, Color.BLUE));
        p.getInventory().setBoots(create(Material.LEATHER_BOOTS, Color.BLUE));

    }

    public static void equipGreen(Player p){

        p.getInventory().setHelmet(create(Material.LEATHER_HELMET, Color.GREEN));
        p.getInventory().setChestplate(create(Material.LEATHER_CHESTPLATE, Color.GREEN));
        p.getInventory().setLeggings(create(Material.LEATHER_LEGGINGS, Color.GREEN));
        p.getInventory().setBoots(create(Material.LEATHER_BOOTS, Color.GREEN));

    }

    private static ItemStack create(Material mat, Color color){

        ItemStack item = new ItemStack(mat);

        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();

        meta.setColor(color);

        item.setItemMeta(meta);

        return item;

    }

}
