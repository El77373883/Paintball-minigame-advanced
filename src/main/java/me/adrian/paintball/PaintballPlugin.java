package me.adrian.paintball;

import org.bukkit.plugin.java.JavaPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.listener.PaintballListener;
import me.adrian.paintball.listener.ShopListener;
import me.adrian.paintball.shop.ShopGUI;

public class PaintballPlugin extends JavaPlugin {

    private static PaintballPlugin instance;
    private GameManager gameManager;
    private ShopGUI shopGUI;

    @Override
    public void onEnable() {
        instance = this;

        // Inicializar GameManager y ShopGUI
        this.gameManager = new GameManager();
        this.shopGUI = new ShopGUI(gameManager);

        // Registrar comandos
        this.getCommand("paintball").setExecutor(new PaintballCommand(gameManager, shopGUI));

        // Registrar listeners
        getServer().getPluginManager().registerEvents(new PaintballListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new ShopListener(shopGUI), this);

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

    public ShopGUI getShopGUI() {
        return shopGUI;
    }
}
