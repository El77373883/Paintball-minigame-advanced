package me.adrian.paintball.game;

import org.bukkit.inventory.ItemStack;

public class PlayerData {

    private int kills;
    private int coins;
    private ItemStack[] savedInventory;

    public PlayerData() {
        this.kills = 0;
        this.coins = 0;
    }

    public int getKills() {
        return kills;
    }

    public void addKill() {
        this.kills++;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int amount) {
        this.coins += amount;
    }

    public void setSavedInventory(ItemStack[] inv) {
        this.savedInventory = inv;
    }

    public ItemStack[] getSavedInventory() {
        return savedInventory;
    }
}
