package me.adrian.paintball.listener;

import me.adrian.paintball.gui.AdminPanelGUI;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AdminGUIListener implements Listener {

    private final AdminPanelGUI gui;

    public AdminGUIListener(AdminPanelGUI gui) { this.gui = gui; }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("§6Admin Arena Panel")) {
            gui.handleClick(e);
        }
    }
}
