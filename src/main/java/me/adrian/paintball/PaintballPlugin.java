package me.adrian.paintball;

import org.bukkit.plugin.java.JavaPlugin;

public class PaintballPlugin extends JavaPlugin {

    private GameManager gameManager;

    @Override
    public void onEnable() {
        this.gameManager = new GameManager(this);

        // Registrar comandos
        this.getCommand("paintball").setExecutor(new command.PaintballCommand(this));

        // Registrar listeners
        getServer().getPluginManager().registerEvents(new listener.PaintballListener(this), this);

        getLogger().info("PaintballPlugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PaintballPlugin disabled!");
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
