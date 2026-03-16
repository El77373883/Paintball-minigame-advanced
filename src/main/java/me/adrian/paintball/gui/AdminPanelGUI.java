package me.adrian.paintball.gui;

import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.game.GameManager.GameTeam;
import me.adrian.paintball.PaintballPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminPanelGUI {

    private final GameManager gm;

    public AdminPanelGUI(GameManager gm) {
        this.gm = gm;
    }

    public void openPanel(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, "§6Admin Arena Panel");

        ItemStack greenTeam = new ItemStack(Material.GREEN_WOOL);
        ItemMeta gmMeta = greenTeam.getItemMeta();
        gmMeta.setDisplayName("§aConfigurar Team Verde");
        greenTeam.setItemMeta(gmMeta);

        ItemStack blueTeam = new ItemStack(Material.BLUE_WOOL);
        ItemMeta bmMeta = blueTeam.getItemMeta();
        bmMeta.setDisplayName("§9Configurar Team Azul");
        blueTeam.setItemMeta(bmMeta);

        ItemStack spawn = new ItemStack(Material.BEACON);
        ItemMeta sm = spawn.getItemMeta();
        sm.setDisplayName("§eEstablecer Spawns");
        spawn.setItemMeta(sm);

        ItemStack effects = new ItemStack(Material.ENDER_PEARL);
        ItemMeta em = effects.getItemMeta();
        em.setDisplayName("§bConfigurar Efectos");
        effects.setItemMeta(em);

        inv.setItem(0, greenTeam);
        inv.setItem(1, blueTeam);
        inv.setItem(2, spawn);
        inv.setItem(3, effects);

        p.openInventory(inv);
    }

    public void handleClick(InventoryClickEvent e) {
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) return;

        switch (item.getType()) {
            case GREEN_WOOL -> p.sendMessage("§aSeleccionaste Team Verde");
            case BLUE_WOOL -> p.sendMessage("§9Seleccionaste Team Azul");
            case BEACON -> p.sendMessage("§eSeleccionaste configurar Spawns");
            case ENDER_PEARL -> p.sendMessage("§bSeleccionaste configurar Efectos");
        }
    }
}
