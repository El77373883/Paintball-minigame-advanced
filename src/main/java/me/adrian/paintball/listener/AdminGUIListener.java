package me.adrian.paintball.listener;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.menu.ArenaPanelGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AdminGUIListener implements Listener {

    private PaintballPlugin plugin;

    public AdminGUIListener(PaintballPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void click(InventoryClickEvent e){

        if(e.getView().getTitle().equals("§8Panel Admin Paintball")){

            e.setCancelled(true);

            Player p = (Player) e.getWhoClicked();

            if(e.getCurrentItem() == null) return;

            Material mat = e.getCurrentItem().getType();

            if(mat == Material.DIAMOND_SWORD){

                p.closeInventory();

                p.sendMessage("§aEscribe en el chat el nombre de la arena.");

            }

            if(mat == Material.STICK){

                new ArenaPanelGUI().open(p);

            }

        }

        if(e.getView().getTitle().equals("§8Editor de Arena")){

            e.setCancelled(true);

            Player p = (Player) e.getWhoClicked();

            if(e.getCurrentItem()==null) return;

            Material mat = e.getCurrentItem().getType();

            if(mat == Material.GOLD_BLOCK){

                plugin.getGameManager().getArena("default").setPos1(p.getLocation());

                p.sendMessage("§aPos1 guardada");

            }

            if(mat == Material.EMERALD_BLOCK){

                plugin.getGameManager().getArena("default").setPos2(p.getLocation());

                p.sendMessage("§aPos2 guardada");

            }

        }

    }

}
