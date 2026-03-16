package me.adrian.paintball;

import org.bukkit.plugin.java.JavaPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.listener.PaintballListener;
import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.command.PaintballAdminCommand;

public class PaintballPlugin extends JavaPlugin {

    private GameManager gameManager;

    @Override
    public void onEnable() {
        this.gameManager = new GameManager();

        getServer().getPluginManager().registerEvents(new PaintballListener(gameManager), this);

        getCommand("pa").setExecutor(new PaintballCommand(this));
        getCommand("paadmin").setExecutor(new PaintballAdminCommand(this));

        getLogger().info("§a[PaintballPlugin] Paintball minigame hecho por §bSoyAdrianyt001");
    }

    @Override
    public void onDisable() {
        getLogger().info("§c[PaintballPlugin] Paintball minigame deshabilitado");
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
