package me.adrian.paintball.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CoinsManager {

    private static HashMap<UUID,Integer> coins = new HashMap<>();

    public static int getCoins(Player p){

        return coins.getOrDefault(p.getUniqueId(),0);

    }

    public static void addCoins(Player p,int amount){

        coins.put(p.getUniqueId(), getCoins(p) + amount);

    }

}
