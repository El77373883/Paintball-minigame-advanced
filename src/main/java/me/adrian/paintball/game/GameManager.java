package me.adrian.paintball.game;

import me.adrian.paintball.PaintballPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GameManager {

    private PaintballPlugin plugin;

    private Map<String, Arena> arenas = new HashMap<>();
    private Map<Player, PlayerData> playerData = new HashMap<>();

    public GameManager(PaintballPlugin plugin) {
        this.plugin = plugin;
    }

    public void createArena(String name) {

        Arena arena = new Arena(name);
        arenas.put(name.toLowerCase(), arena);

    }

    public Arena getArena(String name) {
        return arenas.get(name.toLowerCase());
    }

    public Map<String, Arena> getArenas() {
        return arenas;
    }

    public PlayerData getPlayerData(Player p) {

        if (!playerData.containsKey(p)) {
            playerData.put(p, new PlayerData());
        }

        return playerData.get(p);
    }

    public void joinArena(Player p, Arena arena) {

        arena.addPlayer(p);

        getPlayerData(p).setSavedInventory(p.getInventory().getContents());

        p.getInventory().clear();

        p.sendMessage("§b§lPaintballAdvanced §8» §aEntraste a la arena!");
    }

    public void leaveArena(Player p, Arena arena) {

        arena.removePlayer(p);

        PlayerData data = getPlayerData(p);

        if (data.getSavedInventory() != null) {
            p.getInventory().setContents(data.getSavedInventory());
        }

        p.sendMessage("§b§lPaintballAdvanced §8» §cSaliste de la arena.");
    }

    public void giveKill(Player killer) {

        PlayerData data = getPlayerData(killer);

        data.addKill();
        data.addCoins(3);

        killer.sendMessage("§a+1 Kill §7(+3 coins)");
    }

}
