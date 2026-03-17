package me.adrian.paintball;

import me.adrian.paintball.command.PaintballAdminCommand;
import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.listener.AdminGUIListener;
import me.adrian.paintball.listener.PaintballListener;
import me.adrian.paintball.listener.ShopListener;
import org.bukkit.plugin.java.JavaPlugin;

public class PaintballPlugin extends JavaPlugin {

    private GameManager gameManager;

    @Override
    public void onEnable() {

        // Config
        saveDefaultConfig();

        // Game Manager
        gameManager = new GameManager(this);

        // Comandos
        getCommand("pa").setExecutor(new PaintballCommand(this));
        getCommand("paadmin").setExecutor(new PaintballAdminCommand(this));

        // Listeners
        getServer().getPluginManager().registerEvents(new PaintballListener(this), this);
        getServer().getPluginManager().registerEvents(new ShopListener(this), this);

        // 🔥 ADMIN PANEL LISTENER
        getServer().getPluginManager().registerEvents(new AdminGUIListener(this), this);

        getLogger().info("PaintballAdvanced cargado correctamente.");
        getLogger().info("Plugin creado por soyadrianyt001");

    }

    @Override
    public void onDisable() {

        getLogger().info("PaintballAdvanced desactivado.");

    }

    public GameManager getGameManager() {
        return gameManager;
    }

}
