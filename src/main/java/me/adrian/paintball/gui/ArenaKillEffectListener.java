package me.adrian.paintball.gui;

import me.adrian.paintball.PaintballPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ArenaKillEffectListener implements Listener {

    private final PaintballPlugin plugin;
    private final Player admin;

    public ArenaKillEffectListener(PaintballPlugin plugin, Player admin){
        this.plugin = plugin;
        this.admin = admin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if(!e.getPlayer().equals(admin)) return;
        e.setCancelled(true);
        String effect = e.getMessage().toUpperCase();
        if(plugin.getGameManager().getCurrentArena() != null){
            plugin.getGameManager().getCurrentArena().setKillEffect(effect);
            admin.sendMessage("§6[PaintballAdvanced] §aEfecto al eliminar jugadores: §f" + effect);
        }
        AsyncPlayerChatEvent.getHandlerList().unregister(this);
    }
}
