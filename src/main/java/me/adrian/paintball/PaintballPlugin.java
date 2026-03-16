package me.adrian.paintball;

import org.bukkit.plugin.java.JavaPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.listener.ShopListener;
import me.adrian.paintball.shop.ShopGUI;

public final class PaintballPlugin extends JavaPlugin {

    // Instancia estática para poder usar getInstance()
    private static PaintballPlugin instance;

    private GameManager gameManager;
    private ShopGUI shopGUI;

    @Override
    public void onEnable() {
        // Guardamos instancia
        instance = this;

        // Inicializamos GameManager y ShopGUI
        this.gameManager = new GameManager();
        this.shopGUI = new ShopGUI(gameManager);

        // Registrar comando /pa
        getCommand("pa").setExecutor(new PaintballCommand(this));

        // Registrar listener de la tienda
        getServer().getPluginManager().registerEvents(new ShopListener(shopGUI), this);

        getLogger().info("PaintballPlugin habilitado correctamente.");
    }

    @Override
    public void onDisable() {
        getLogger().info("PaintballPlugin deshabilitado.");
    }

    // Getter estático para obtener la instancia del plugin
    public static PaintballPlugin getInstance() {
        return instance;
    }

    // Getter del GameManager
    public GameManager getGameManager() {
        return gameManager;
    }

    // Getter del ShopGUI
    public ShopGUI getShopGUI() {
        return shopGUI;
    }
}
