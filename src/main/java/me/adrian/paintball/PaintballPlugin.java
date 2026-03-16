package me.adrian.paintball;

import org.bukkit.plugin.java.JavaPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.listener.PaintballListener;
import me.adrian.paintball.listener.ShopListener;

public class PaintballPlugin extends JavaPlugin {

    private static PaintballPlugin instance;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        instance = this;

        // Instanciamos GameManager sin argumentos
        this.gameManager = new GameManager();

        // Registrar comandos
        this.getCommand("paintball").setExecutor(new PaintballCommand(gameManager));

        // Registrar listeners
        getServer().getPluginManager().registerEvents(new PaintballListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new ShopListener(gameManager), this);

        getLogger().info("PaintballPlugin habilitado correctamente.");
    }

    @Override
    public void onDisable() {
        getLogger().info("PaintballPlugin deshabilitado.");
    }

    public static PaintballPlugin getInstance() {
        return instance;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
