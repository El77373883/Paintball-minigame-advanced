package me.adrian.paintball.gui;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ArenaRenameListener implements Listener {

    private final PaintballPlugin plugin;
    private final Player admin;

    public ArenaRenameListener(PaintballPlugin plugin, Player admin) {
        this.plugin = plugin;
        this.admin = admin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if(!e.getPlayer().equals(admin)) return;
        e.setCancelled(true);
        String newName = e.getMessage();

        GameManager gm = plugin.getGameManager();
        if(gm.getCurrentArena() != null){
            gm.getCurrentArena().setName(newName);
            admin.sendMessage("§6[PaintballAdvanced] §aArena renombrada a: §f" + newName);
        }

        AsyncPlayerChatEvent.getHandlerList().unregister(this);
    }
}
