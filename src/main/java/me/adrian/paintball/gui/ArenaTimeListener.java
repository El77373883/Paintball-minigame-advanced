package me.adrian.paintball.gui;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ArenaTimeListener implements Listener {

    private final PaintballPlugin plugin;
    private final Player admin;

    public ArenaTimeListener(PaintballPlugin plugin, Player admin) {
        this.plugin = plugin;
        this.admin = admin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if(!e.getPlayer().equals(admin)) return;
        e.setCancelled(true);
        try {
            int time = Integer.parseInt(e.getMessage());
            if(plugin.getGameManager().getCurrentArena() != null){
                plugin.getGameManager().getCurrentArena().setTime(time);
                admin.sendMessage("§6[PaintballAdvanced] §aTiempo de la partida: §f" + time + " minutos");
            }
        } catch(NumberFormatException ex){
            admin.sendMessage("§6[PaintballAdvanced] §cDebes escribir un número válido.");
        }
        AsyncPlayerChatEvent.getHandlerList().unregister(this);
    }
}
