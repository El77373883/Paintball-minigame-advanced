package me.adrian.paintball.gui;

import me.adrian.paintball.PaintballPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AdminGUIListener implements Listener {

    private final PaintballPlugin plugin;

    public AdminGUIListener(PaintballPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        // Verificar que el panel exista
        if(plugin.getAdminPanelGUI() == null) return;

        // Solo manejar clicks del AdminPanelGUI
        plugin.getAdminPanelGUI().handleClick(e);
    }
}
