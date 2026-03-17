package me.adrian.paintball;

import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.command.PaintballAdminCommand;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.gui.AdminGUIListener;
import me.adrian.paintball.listener.PaintballListener;
import me.adrian.paintball.listener.ShopListener;
import me.adrian.paintball.shop.ShopGUI;
import me.adrian.paintball.scoreboard.ScoreboardTask;
import org.bukkit.plugin.java.JavaPlugin;

public class PaintballPlugin extends JavaPlugin {

    private static PaintballPlugin instance;

    private GameManager gameManager;
    private ShopGUI shopGUI;

    @Override
    public void onEnable() {
        // Instancia estática
        instance = this;

        // Inicializar GameManager y ShopGUI
        this.gameManager = new GameManager();
        this.shopGUI = new ShopGUI(this.gameManager);

        // Registrar comandos premium
        this.getCommand("pa").setExecutor(new PaintballCommand(this));
        this.getCommand("paadmin").setExecutor(new PaintballAdminCommand(this));
        this.getCommand("pacreator").setExecutor((sender, cmd, label, args) -> {
            sender.sendMessage("§6[PaintballAdvanced] §aPlugin creado por §eSoyAdriAnyT001");
            return true;
        });

        // Registrar listeners
        getServer().getPluginManager().registerEvents(new PaintballListener(this), this);
        getServer().getPluginManager().registerEvents(new ShopListener(this), this);
        getServer().getPluginManager().registerEvents(new AdminGUIListener(this), this);

        // Iniciar ScoreboardTask (animado)
        new ScoreboardTask(gameManager).runTaskTimer(this, 0, 40);

        getLogger().info("§a[PaintballAdvanced] Plugin habilitado correctamente!");
    }

    @Override
    public void onDisable() {
        // Limpiar jugadores de todas las arenas
        gameManager.getArenas().forEach(arena -> arena.getPlayers().forEach(p -> {
            p.getInventory().clear();
            p.sendMessage("§c[PaintballAdvanced] Se ha cerrado la partida y tu inventario fue limpiado.");
        }));

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
}
