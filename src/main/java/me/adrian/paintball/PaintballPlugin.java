package me.adrian.paintball;

import org.bukkit.plugin.java.JavaPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.command.PaintballCommand;
import me.adrian.paintball.listener.ShopListener;
import me.adrian.paintball.shop.ShopGUI;

public class PaintballPlugin extends JavaPlugin {

    private GameManager gameManager;
    private ShopGUI shopGUI;

    @Override
    public void onEnable() {
        this.gameManager = new GameManager();
        this.shopGUI = new ShopGUI(gameManager);

        getCommand("pa").setExecutor(new PaintballCommand(this));
        getServer().getPluginManager().registerEvents(new ShopListener(shopGUI), this);
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public ShopGUI getShopGUI() {
        return shopGUI;
    }
}
