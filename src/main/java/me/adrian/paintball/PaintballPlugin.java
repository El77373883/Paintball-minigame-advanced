package me.adrian.paintball;

import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.command.PaintballAdminCommand;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.gui.AdminGUIListener;
import me.adrian.paintball.gui.AdminPanelGUI;
import me.adrian.paintball.listener.PaintballListener;
import me.adrian.paintball.listener.ShopListener;
import me.adrian.paintball.shop.ShopGUI;
import me.adrian.paintball.scoreboard.ScoreboardTask;
import org.bukkit.plugin.java.JavaPlugin;

public class PaintballPlugin extends JavaPlugin {

    private static PaintballPlugin instance;

    private GameManager gameManager;
    private ShopGUI shopGUI;
    private AdminPanelGUI adminPanelGUI;

    @Override
    public void onEnable() {
        // Instancia estática
        instance = this;

        // Inicializar GameManager
        this.gameManager = new GameManager();

        // Inicializar ShopGUI
        this.shopGUI = new ShopGUI(this.gameManager);

        // Inicializar AdminPanelGUI
        this.adminPanelGUI = new AdminPanelGUI(this);

        // Registrar comandos
        this.getCommand("pa").setExecutor(new PaintballCommand(this));
        this.getCommand("paadmin").setExecutor(new PaintballAdminCommand(this));

        // Registrar listeners
        getServer().getPluginManager().registerEvents(new PaintballListener(this), this);
        getServer().getPluginManager().registerEvents(new ShopListener(this), this);
        getServer().getPluginManager().registerEvents(new AdminGUIListener(this), this);

        // Iniciar ScoreboardTask
        new ScoreboardTask(gameManager).runTaskTimer(this, 0, 40);

        getLogger().info("§a[PaintballAdvanced] Plugin habilitado correctamente!");
    }

    @Override
    public void onDisable() {
        getLogger().info("§c[PaintballAdvanced] Plugin deshabilitado!");
    }

    // ---------------- GETTERS ---------------- //
    public static PaintballPlugin getInstance() {
        return instance;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public ShopGUI getShopGUI() {
        return shopGUI;
    }

    public AdminPanelGUI getAdminPanelGUI() {
        return adminPanelGUI;
    }
}
