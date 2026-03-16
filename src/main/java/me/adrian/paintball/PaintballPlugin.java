package me.adrian.paintball;

import org.bukkit.plugin.java.JavaPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.listener.PaintballListener;
import me.adrian.paintball.listener.ShopListener;
import me.adrian.paintball.shop.ShopGUI;
import me.adrian.paintball.scoreboard.ScoreboardTask;

public class PaintballPlugin extends JavaPlugin {

    private GameManager gameManager;
    private ShopGUI shopGUI;

    @Override
    public void onEnable() {
        this.gameManager = new GameManager(this);
        this.shopGUI = new ShopGUI(gameManager);

        // Listeners
        getServer().getPluginManager().registerEvents(new PaintballListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new ShopListener(shopGUI), this);

        // Scoreboard actualizado cada segundo
        new ScoreboardTask(gameManager).runTaskTimer(this, 0L, 20L);

        getLogger().info("Paintball Minigame 1.21.11 habilitado ✅ Hecho por soyadrianyt001");
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public ShopGUI getShopGUI() {
        return shopGUI;
    }
}
