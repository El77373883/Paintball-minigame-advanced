package me.adrian.paintball.game;

public class PlayerData {
    private final int kills;
    private final int coins;
    private final int snowballs;

    public PlayerData(int kills, int coins, int snowballs) {
        this.kills = kills;
        this.coins = coins;
        this.snowballs = snowballs;
    }

    public int getKills() { return kills; }
    public int getCoins() { return coins; }
    public int getSnowballs() { return snowballs; }
}
