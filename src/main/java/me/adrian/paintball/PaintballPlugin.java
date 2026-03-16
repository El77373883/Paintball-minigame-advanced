package me.adrian.paintball;

import org.bukkit.plugin.java.JavaPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.listener.PaintballListener;
import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.command.PaintballAdminCommand;
import me.adrian.paintball.scoreboard.ScoreboardTask;

public class PaintballPlugin extends JavaPlugin {

    private GameManager gameManager;

    @Override
    public void onEnable() {
        getLogger().info("Paintball Minigame hecho por soyadrianyt001");
        saveDefaultConfig();

        this.gameManager = new GameManager(this);

        getServer().getPluginManager().registerEvents(new PaintballListener(gameManager), this);

        this.getCommand("pa").setExecutor(new PaintballCommand(this));
        this.getCommand("paadmin").setExecutor(new PaintballAdminCommand(this));

        // Start scoreboard task
        new ScoreboardTask(this, gameManager).runTaskTimer(this, 0L, 20L);
    }

    @Override
    public void onDisable() {
        getLogger().info("PaintballPlugin deshabilitado.");
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
