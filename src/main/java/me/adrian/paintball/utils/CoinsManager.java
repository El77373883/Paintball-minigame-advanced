package me.adrian.paintball.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CoinsManager {

    private Map<Player, Integer> coins = new HashMap<>();

    public int getCoins(Player p) {
        return coins.getOrDefault(p, 0);
    }

    public void addCoins(Player p, int amount) {
        coins.put(p, getCoins(p) + amount);
    }

}
