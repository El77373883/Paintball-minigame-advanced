package me.adrian.paintball.game;

import org.bukkit.entity.Player;

public class PlayerData {

    private final Player player;
    private int coins;
    private int kills;
    private GameTeam team;
    private boolean inArena;

    public PlayerData(Player player) {
        this.player = player;
        this.coins = 0;       // Coins iniciales
        this.kills = 0;       // Kills iniciales
        this.team = null;     // Equipo asignado más tarde
        this.inArena = false; // Estado en arena
    }

    // ---------------- GETTERS ---------------- //
    public Player getPlayer() {
        return player;
    }

    public int getCoins() {
        return coins;
    }

    public int getKills() {
        return kills;
    }

    public GameTeam getTeam() {
        return team;
    }

    public boolean isInArena() {
        return inArena;
    }

    // ---------------- SETTERS ---------------- //
    public void setTeam(GameTeam team) {
        this.team = team;
    }

    public void setInArena(boolean inArena) {
        this.inArena = inArena;
    }

    // ---------------- COINS ---------------- //
    public void addCoins(int amount) {
        this.coins += amount;
    }

    public void removeCoins(int amount) {
        this.coins -= amount;
        if (this.coins < 0) this.coins = 0;
    }

    // ---------------- KILLS ---------------- //
    public void addKill() {
        this.kills += 1;
    }

    public void resetKills() {
        this.kills = 0;
    }

    // ---------------- RESET PLAYER ---------------- //
    public void resetPlayer() {
        this.coins = 0;
        this.kills = 0;
        this.team = null;
        this.inArena = false;
    }
}
