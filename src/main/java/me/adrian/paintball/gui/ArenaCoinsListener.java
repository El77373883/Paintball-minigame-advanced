package me.adrian.paintball.gui;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ArenaCoinsListener implements Listener {

    private final PaintballPlugin plugin;
    private final Player admin;

    public ArenaCoinsListener(PaintballPlugin plugin, Player admin){
        this.plugin = plugin;
        this.admin = admin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if(!e.getPlayer().equals(admin)) return;
        e.setCancelled(true);
        try{
            int coins = Integer.parseInt(e.getMessage());
            if(plugin.getGameManager().getCurrentArena() != null){
                plugin.getGameManager().getCurrentArena().setStartingCoins(coins);
                admin.sendMessage("§6[PaintballAdvanced] §aCoins iniciales: §f" + coins);
            }
        }catch(NumberFormatException ex){
            admin.sendMessage("§6[PaintballAdvanced] §cDebes escribir un número válido.");
        }
        AsyncPlayerChatEvent.getHandlerList().unregister(this);
    }
}
