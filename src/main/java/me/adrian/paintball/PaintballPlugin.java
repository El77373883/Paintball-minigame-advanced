package me.adrian.paintball;

import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.shop.ShopGUI;
import org.bukkit.plugin.java.JavaPlugin;

public class PaintballPlugin extends JavaPlugin {

    private static PaintballPlugin instance;

    private GameManager gameManager;
    private ShopGUI shopGUI;

    @Override
    public void onEnable() {
        instance = this;

        // Inicializamos GameManager y ShopGUI
        this.gameManager = new GameManager();
        this.shopGUI = new ShopGUI();

        // Registramos comando /pa
        this.getCommand("pa").setExecutor(new PaintballCommand(this));

        getLogger().info("Paintball Plugin by soyadrianyt001 habilitado!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Paintball Plugin deshabilitado!");
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
