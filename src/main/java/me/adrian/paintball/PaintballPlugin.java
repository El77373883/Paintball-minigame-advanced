package me.adrian.paintball;

import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.command.PaintballAdminCommand;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.listener.AdminGUIListener;
import me.adrian.paintball.listener.PaintballListener;
import me.adrian.paintball.listener.ShopListener;
import me.adrian.paintball.scoreboard.ScoreboardTask;
import me.adrian.paintball.shop.ShopGUI;
import org.bukkit.plugin.java.JavaPlugin;

public class PaintballPlugin extends JavaPlugin {

    private static PaintballPlugin instance; // Instancia estática
    private GameManager gameManager;
    private ShopGUI shopGUI;

    @Override
    public void onEnable() {

        // Inicializar instancia
        instance = this;

        // Inicializar GameManager y ShopGUI
        this.gameManager = new GameManager();
        this.shopGUI = new ShopGUI(this.gameManager);

        // Registrar comandos
        this.getCommand("pa").setExecutor(new PaintballCommand(this));
        this.getCommand("paadmin").setExecutor(new PaintballAdminCommand(this));

        // Registrar listeners
        getServer().getPluginManager().registerEvents(new PaintballListener(this), this);
        getServer().getPluginManager().registerEvents(new ShopListener(this), this);
        getServer().getPluginManager().registerEvents(new AdminGUIListener(this), this);

        // Iniciar ScoreboardTask cada 40 ticks (2 segundos)
        new ScoreboardTask(gameManager).runTaskTimer(this, 0, 40);

        getLogger().info("§aPaintballAdvanced habilitado!");
    }

    @Override
    public void onDisable() {
        getLogger().info("§cPaintballAdvanced deshabilitado!");
    }

    // Getters
    public static PaintballPlugin getInstance() {
        return instance;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public ShopGUI getShopGUI() {
        return shopGUI;
    }

}
