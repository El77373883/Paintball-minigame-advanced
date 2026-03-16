package me.adrian.paintball;

import org.bukkit.plugin.java.JavaPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.listener.PaintballListener;
import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.command.PaintballAdminCommand;
import me.adrian.paintball.scoreboard.ScoreboardTask;
import org.bukkit.Bukkit;

public class PaintballPlugin extends JavaPlugin {

    private GameManager gameManager;

    @Override
    public void onEnable() {
        this.gameManager = new GameManager();

        // Registrar listeners
        getServer().getPluginManager().registerEvents(new PaintballListener(gameManager), this);

        // Registrar comandos
        this.getCommand("pa").setExecutor(new PaintballCommand(this));
        this.getCommand("paadmin").setExecutor(new PaintballAdminCommand(this));

        // Scoreboard dinámico cada segundo
        Bukkit.getScheduler().runTaskTimer(this, new ScoreboardTask(gameManager), 0L, 20L);

        getLogger().info("Paintball Minigame hecho por soyadrianyt001 - habilitado correctamente.");
    }

    @Override
    public void onDisable() {
        getLogger().info("PaintballPlugin deshabilitado.");
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
