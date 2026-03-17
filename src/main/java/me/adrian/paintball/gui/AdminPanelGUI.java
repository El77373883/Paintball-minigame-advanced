package me.adrian.paintball.gui;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.Arena;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.game.GameManager.GameTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminPanelGUI {

    private final PaintballPlugin plugin;
    private final GameManager gameManager;
    private Inventory gui;

    public AdminPanelGUI(PaintballPlugin plugin) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
        createGUI();
    }

    private void createGUI() {
        gui = Bukkit.createInventory(null, 27, ChatColor.DARK_PURPLE + "Paintball Admin Panel");

        // Items del menú
        gui.setItem(0, createItem(Material.BANNER, ChatColor.AQUA + "Crear Arena"));
        gui.setItem(1, createItem(Material.OAK_SIGN, ChatColor.GREEN + "Posición 1"));
        gui.setItem(2, createItem(Material.OAK_SIGN, ChatColor.GREEN + "Posición 2"));
        gui.setItem(3, createItem(Material.NAME_TAG, ChatColor.YELLOW + "Cambiar Nombre"));
        gui.setItem(4, createItem(Material.LEATHER_CHESTPLATE, ChatColor.RED + "Color Equipo Rojo"));
        gui.setItem(5, createItem(Material.LEATHER_CHESTPLATE, ChatColor.BLUE + "Color Equipo Azul"));
        gui.setItem(6, createItem(Material.CLOCK, ChatColor.GOLD + "Cambiar Tiempo"));
        gui.setItem(7, createItem(Material.GOLD_INGOT, ChatColor.GOLD + "Ajustar Coins"));
        gui.setItem(8, createItem(Material.PLAYER_HEAD, ChatColor.LIGHT_PURPLE + "Max Players"));
        gui.setItem(9, createItem(Material.DIAMOND_SWORD, ChatColor.RED + "Efecto al Kill"));
        gui.setItem(17, createItem(Material.BARRIER, ChatColor.DARK_RED + "Cerrar Panel"));
    }

    private ItemStack createItem(Material mat, String name) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        return item;
    }

    public void open(Player player) {
        player.openInventory(gui);
    }

    public void handleClick(InventoryClickEvent e) {
        if (!e.getInventory().equals(gui)) return;
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR) return;

        switch (clicked.getType()) {
            case BANNER:
                p.sendMessage(ChatColor.AQUA + "[PaintballAdvanced] " + ChatColor.GREEN + "Escribe en el chat el nombre de la nueva arena.");
                plugin.getServer().getPluginManager().registerEvents(new ArenaNameListener(plugin, p), plugin);
                p.closeInventory();
                break;
            case OAK_SIGN:
                p.sendMessage(ChatColor.AQUA + "[PaintballAdvanced] " + ChatColor.GREEN + "Marca la posición en la arena con tu ubicación actual.");
                plugin.getServer().getPluginManager().registerEvents(new ArenaPositionListener(plugin, p, e.getSlot()), plugin);
                p.closeInventory();
                break;
            case NAME_TAG:
                p.sendMessage(ChatColor.AQUA + "[PaintballAdvanced] " + ChatColor.GREEN + "Escribe en el chat el nuevo nombre de la arena.");
                plugin.getServer().getPluginManager().registerEvents(new ArenaRenameListener(plugin, p), plugin);
                p.closeInventory();
                break;
            case LEATHER_CHESTPLATE:
                if(clicked.getItemMeta().getDisplayName().contains("Rojo")){
                    p.sendMessage(ChatColor.RED + "Equipo Rojo seleccionado.");
                    plugin.getServer().getPluginManager().registerEvents(new ArenaTeamColorListener(plugin, p, GameTeam.RED), plugin);
                } else {
                    p.sendMessage(ChatColor.BLUE + "Equipo Azul seleccionado.");
                    plugin.getServer().getPluginManager().registerEvents(new ArenaTeamColorListener(plugin, p, GameTeam.BLUE), plugin);
                }
                p.closeInventory();
                break;
            case CLOCK:
                p.sendMessage(ChatColor.GOLD + "Escribe en chat el tiempo de la partida en minutos.");
                plugin.getServer().getPluginManager().registerEvents(new ArenaTimeListener(plugin, p), plugin);
                p.closeInventory();
                break;
            case GOLD_INGOT:
                p.sendMessage(ChatColor.GOLD + "Escribe en chat la cantidad de coins iniciales.");
                plugin.getServer().getPluginManager().registerEvents(new ArenaCoinsListener(plugin, p), plugin);
                p.closeInventory();
                break;
            case PLAYER_HEAD:
                p.sendMessage(ChatColor.LIGHT_PURPLE + "Escribe en chat la cantidad máxima de jugadores.");
                plugin.getServer().getPluginManager().registerEvents(new ArenaMaxPlayersListener(plugin, p), plugin);
                p.closeInventory();
                break;
            case DIAMOND_SWORD:
                p.sendMessage(ChatColor.RED + "Selecciona el efecto al eliminar jugadores.");
                plugin.getServer().getPluginManager().registerEvents(new ArenaKillEffectListener(plugin, p), plugin);
                p.closeInventory();
                break;
            case BARRIER:
                p.closeInventory();
                p.sendMessage(ChatColor.DARK_RED + "[PaintballAdvanced] Panel cerrado.");
                break;
        }
    }
}
