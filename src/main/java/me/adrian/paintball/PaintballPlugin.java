package me.adrian.paintball;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.listener.PaintballListener;
import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.command.PaintballAdminCommand;
import me.adrian.paintball.scoreboard.ScoreboardTask;

public class PaintballPlugin extends JavaPlugin {

    private static PaintballPlugin instance;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        instance = this;
        this.gameManager = new GameManager();

        // Registrar listeners
        Bukkit.getPluginManager().registerEvents(new PaintballListener(gameManager), this);

        // Registrar comandos
        this.getCommand("pa").setExecutor(new PaintballCommand());
        this.getCommand("paadmin").setExecutor(new PaintballAdminCommand());

        // Scoreboard dinámico
        Bukkit.getScheduler().runTaskTimer(this, new ScoreboardTask(gameManager), 0L, 20L);

        getLogger().info("Paintball Minigame hecho por soyadrianyt001 - habilitado correctamente.");
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
