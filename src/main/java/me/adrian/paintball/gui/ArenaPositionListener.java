package me.adrian.paintball.gui;

import me.adrian.paintball.PaintballPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ArenaPositionListener implements Listener {

    private final PaintballPlugin plugin;
    private final Player admin;
    private final int slot;

    public ArenaPositionListener(PaintballPlugin plugin, Player admin, int slot){
        this.plugin = plugin;
        this.admin = admin;
        this.slot = slot; // 1 o 2
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(!e.getPlayer().equals(admin)) return;
        Location loc = admin.getLocation();
        if(plugin.getGameManager().getCurrentArena() != null){
            if(slot == 1) plugin.getGameManager().getCurrentArena().setPos1(loc);
            else plugin.getGameManager().getCurrentArena().setPos2(loc);

            admin.sendMessage("§6[PaintballAdvanced] §aPosición " + slot + " guardada.");
        }
        PlayerMoveEvent.getHandlerList().unregister(this);
    }
}
