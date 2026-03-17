package me.adrian.paintball.gui;

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

public class AdminPanelGUI {

    private final PaintballPlugin plugin;
    private final GameManager gameManager;

    public AdminPanelGUI(PaintballPlugin plugin) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
    }

    public void open(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9*3, "§6Paintball Admin Panel");

        // Crear Arena
        ItemStack createArena = new ItemStack(Material.BANNER);
        ItemMeta meta = createArena.getItemMeta();
        meta.setDisplayName("§aCrear Arena");
        List<String> lore = new ArrayList<>();
        lore.add("§7Haz click para crear una arena nueva");
        meta.setLore(lore);
        createArena.setItemMeta(meta);
        inv.setItem(0, createArena);

        // Editar Arena
        ItemStack editArena = new ItemStack(Material.MAP);
        meta = editArena.getItemMeta();
        meta.setDisplayName("§eEditar Arena");
        lore = new ArrayList<>();
        lore.add("§7Selecciona un arena para editarla");
        meta.setLore(lore);
        editArena.setItemMeta(meta);
        inv.setItem(1, editArena);

        // Cambiar Tiempo
        ItemStack changeTime = new ItemStack(Material.CLOCK);
        meta = changeTime.getItemMeta();
        meta.setDisplayName("§bCambiar Tiempo de Juego");
        lore = new ArrayList<>();
        lore.add("§7Click para cambiar duración de la partida");
        meta.setLore(lore);
        changeTime.setItemMeta(meta);
        inv.setItem(2, changeTime);

        // Cambiar Coins por Kill
        ItemStack changeCoins = new ItemStack(Material.GOLD_INGOT);
        meta = changeCoins.getItemMeta();
        meta.setDisplayName("§6Cambiar Coins por Kill");
        lore = new ArrayList<>();
        lore.add("§7Click para cambiar coins otorgadas por eliminar");
        meta.setLore(lore);
        changeCoins.setItemMeta(meta);
        inv.setItem(3, changeCoins);

        // Cambiar Efectos
        ItemStack changeEffect = new ItemStack(Material.FIREWORK_ROCKET);
        meta = changeEffect.getItemMeta();
        meta.setDisplayName("§dCambiar Efectos al Eliminar");
        lore = new ArrayList<>();
        lore.add("§7Click para seleccionar efecto visual al matar");
        meta.setLore(lore);
        changeEffect.setItemMeta(meta);
        inv.setItem(4, changeEffect);

        // Cambiar Equipos
        ItemStack changeTeams = new ItemStack(Material.LEATHER_CHESTPLATE);
        meta = changeTeams.getItemMeta();
        meta.setDisplayName("§cCambiar Colores de Equipos");
        lore = new ArrayList<>();
        lore.add("§7Click para cambiar colores de los equipos");
        meta.setLore(lore);
        changeTeams.setItemMeta(meta);
        inv.setItem(5, changeTeams);

        // Volver / Regresar
        ItemStack back = new ItemStack(Material.BARRIER);
        meta = back.getItemMeta();
        meta.setDisplayName("§4Volver");
        back.setItemMeta(meta);
        inv.setItem(8, back);

        p.openInventory(inv);
    }
}
