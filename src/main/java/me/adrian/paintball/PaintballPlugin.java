package me.adrian.paintball;

import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.listener.PaintballListener;
import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.command.PaintballAdminCommand;
import me.adrian.paintball.scoreboard.ScoreboardTask;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class PaintballPlugin extends JavaPlugin {

    private static PaintballPlugin instance;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        instance = this;

        // Inicializar GameManager
        this.gameManager = new GameManager();

        // Registrar listeners
        getServer().getPluginManager().registerEvents(new PaintballListener(gameManager), this);

        // Registrar comandos
        getCommand("pa").setExecutor(new PaintballCommand());
        getCommand("paadmin").setExecutor(new PaintballAdminCommand());

        // Tareas automáticas: ScoreboardTask
        ScoreboardTask sbTask = new ScoreboardTask(gameManager);
        sbTask.runTaskTimer(this, 20L, 20L); // cada segundo

        // Mensaje de inicio
        getLogger().info("§aPaintball Minigame cargado y listo! Hecho por soyadrianyt001");
    }

    @Override
    public void onDisable() {
        getLogger().info("§cPaintball Minigame deshabilitado.");
    }

    // Singleton para acceder desde otras clases
    public static PaintballPlugin getInstance() {
        return instance;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
