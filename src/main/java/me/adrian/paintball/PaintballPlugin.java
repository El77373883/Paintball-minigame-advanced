package me.adrian.paintball;

import org.bukkit.plugin.java.JavaPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.listener.PaintballListener;
import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.command.PaintballAdminCommand;

public class PaintballPlugin extends JavaPlugin {

    private GameManager gameManager;
    private static PaintballPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        this.gameManager = new GameManager();

        getServer().getPluginManager().registerEvents(new PaintballListener(gameManager), this);

        this.getCommand("pa").setExecutor(new PaintballCommand());
        this.getCommand("paadmin").setExecutor(new PaintballAdminCommand());

        getLogger().info("§6Paintball Minigame habilitado por §bSoyAdrianyt001");
    }

    @Override
    public void onDisable() {
        getLogger().info("§6Paintball Minigame deshabilitado.");
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public static PaintballPlugin getInstance() {
        return instance;
    }
}
