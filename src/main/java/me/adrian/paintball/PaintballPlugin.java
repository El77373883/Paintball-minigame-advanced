package me.adrian.paintball;

import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.command.PaintballAdminCommand;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.shop.ShopGUI;
import org.bukkit.plugin.java.JavaPlugin;

public class PaintballPlugin extends JavaPlugin {

    private GameManager gameManager;
    private ShopGUI shopGUI;

    @Override
    public void onEnable() {
        // Inicializar GameManager primero
        this.gameManager = new GameManager();

        // Pasar gameManager al constructor de ShopGUI
        this.shopGUI = new ShopGUI(this.gameManager);

        // Registrar comandos
        this.getCommand("pa").setExecutor(new PaintballCommand(this));
        this.getCommand("paadmin").setExecutor(new PaintballAdminCommand(this));

        getLogger().info("PaintballPlugin habilitado!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PaintballPlugin deshabilitado!");
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public ShopGUI getShopGUI() {
        return shopGUI;
    }
}
