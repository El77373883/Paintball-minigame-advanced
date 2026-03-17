package me.adrian.paintball.gui;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ArenaNameListener implements Listener {

    private final PaintballPlugin plugin;
    private final Player admin;

    public ArenaNameListener(PaintballPlugin plugin, Player admin) {
        this.plugin = plugin;
        this.admin = admin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if(!e.getPlayer().equals(admin)) return;
        e.setCancelled(true);
        String name = e.getMessage();

        GameManager gm = plugin.getGameManager();
        gm.createArena(name);

        admin.sendMessage("§6[PaintballAdvanced] §aArena creada con nombre: §f" + name);
        AsyncPlayerChatEvent.getHandlerList().unregister(this);
    }
}
