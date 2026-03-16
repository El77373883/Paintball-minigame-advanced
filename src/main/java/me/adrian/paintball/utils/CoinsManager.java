package me.adrian.paintball.utils;

import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class CoinsManager {

    private static final Map<Player, Integer> coins = new HashMap<>();

    public static int getCoins(Player player) {
        return coins.getOrDefault(player, 0);
    }

    public static void addCoins(Player player, int amount) {
        coins.put(player, getCoins(player) + amount);
    }

    public static boolean removeCoins(Player player, int amount) {
        if (getCoins(player) >= amount) {
            coins.put(player, getCoins(player) - amount);
            return true;
        }
        return false;
    }
}
