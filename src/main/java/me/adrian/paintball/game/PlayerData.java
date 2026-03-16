package me.adrian.paintball.game;

import org.bukkit.entity.Player;

public class PlayerData {
    private final Player player;
    private GameTeam team;
    private int kills = 0;
    private int coins = 32;
    private boolean alive = true;

    public PlayerData(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public GameTeam getTeam() {
        return team;
    }

    public void setTeam(GameTeam team) {
        this.team = team;
    }

    public int getKills() {
        return kills;
    }

    public void addKill() {
        kills++;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int c) {
        coins += c;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
