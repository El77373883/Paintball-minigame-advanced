package me.adrian.paintball;

import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.command.PaintballAdminCommand;
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
        this.gameManager = new GameManager();
        this.shopGUI = new ShopGUI(this.gameManager);

        this.getCommand("pa").setExecutor(new PaintballCommand(this));
        this.getCommand("paadmin").setExecutor(new PaintballAdminCommand(this));

        getLogger().info("PaintballPlugin habilitado!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PaintballPlugin deshabilitado!");
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
