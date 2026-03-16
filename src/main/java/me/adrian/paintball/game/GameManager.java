package me.adrian.paintball.game;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import me.adrian.paintball.PaintballPlugin;

import java.util.*;

public class GameManager {

    public enum GameState { WAITING, IN_GAME, FINISHED }
    public enum GameTeam { BLUE, GREEN }

    private GameState state;
    private final Map<String, Arena> arenas = new HashMap<>();
    private Arena currentArena;

    // Mapas de jugadores
    private final Map<Player, PlayerData> playerDataMap = new HashMap<>();

    public GameManager() {
        this.state = GameState.WAITING;
    }

    // ---------------- ARENAS ---------------- //
    public void createArena(String name) {
        if (!arenas.containsKey(name)) {
            Arena arena = new Arena(name);
            arenas.put(name, arena);
            PaintballPlugin.getPlugin(PaintballPlugin.class)
                .getLogger().info("Arena creada: " + name);
        }
    }

    public Collection<Arena> getArenas() { return arenas.values(); }

    public void setCurrentArena(String name) { this.currentArena = arenas.get(name); }
    public Arena getCurrentArena() { return currentArena; }

    // ---------------- GAME STATE ---------------- //
    public GameState getState() { return state; }

    public void startGame() {
        if (currentArena == null) return;
        state = GameState.IN_GAME;
        assignTeams();
        giveTeamArmor();
    }

    public void endGame() {
        state = GameState.FINISHED;
        playerDataMap.clear();
    }

    // ---------------- PLAYER DATA ---------------- //
    public void addPlayer(Player player) {
        if (!playerDataMap.containsKey(player))
            playerDataMap.put(player, new PlayerData(player));
    }

    public void removePlayer(Player player) {
        playerDataMap.remove(player);
    }

    public PlayerData getPlayerData(Player player) {
        return playerDataMap.get(player);
    }

    // ---------------- COMPATIBILITY METHODS ---------------- //

    // Kills
    public int getKills(Player player) {
        PlayerData data = getPlayerData(player);
        return data != null ? data.getKills() : 0;
    }

    // Coins
    public int getCoins(Player player) {
        PlayerData data = getPlayerData(player);
        return data != null ? data.getCoins() : 0;
    }

    public void addCoins(Player player, int amount) {
        PlayerData data = getPlayerData(player);
        if (data != null) data.addCoins(amount);
    }

    public void removeCoins(Player player, int amount) {
        addCoins(player, -amount);
    }

    // Snowballs
    private final Map<Player, Integer> snowballCount = new HashMap<>();

    public boolean canThrowSnowball(Player player) {
        snowballCount.putIfAbsent(player, 32);
        return snowballCount.get(player) > 0;
    }

    public void throwSnowball(Player player) {
        snowballCount.put(player, snowballCount.get(player) - 1);
    }

    public void refillSnowballs(Player player, int amount) {
        snowballCount.put(player, snowballCount.getOrDefault(player, 0) + amount);
    }

    public int getSnowballs(Player player) {
        return snowballCount.getOrDefault(player, 0);
    }

    // ---------------- TEAMS ---------------- //
    private void assignTeams() {
        if (playerDataMap.isEmpty()) return;

        List<Player> players = new ArrayList<>(playerDataMap.keySet());
        Collections.shuffle(players);
        int half = players.size() / 2;

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            PlayerData data = getPlayerData(player);
            if (data != null) {
                data.setTeam(i < half ? GameTeam.BLUE : GameTeam.GREEN);
                data.setAlive(true);
            }
        }
    }

    private void giveTeamArmor() {
        for (PlayerData data : playerDataMap.values()) {
            Player player = data.getPlayer();
            GameTeam team = data.getTeam();
            Color color = team == GameTeam.BLUE ? Color.BLUE : Color.GREEN;

            ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
            ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
            ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

            LeatherArmorMeta meta;

            meta = (LeatherArmorMeta) helmet.getItemMeta();
            meta.setColor(color); helmet.setItemMeta(meta);

            meta = (LeatherArmorMeta) chest.getItemMeta();
            meta.setColor(color); chest.setItemMeta(meta);

            meta = (LeatherArmorMeta) legs.getItemMeta();
            meta.setColor(color); legs.setItemMeta(meta);

            meta = (LeatherArmorMeta) boots.getItemMeta();
            meta.setColor(color); boots.setItemMeta(meta);

            player.getInventory().setHelmet(helmet);
            player.getInventory().setChestplate(chest);
            player.getInventory().setLeggings(legs);
            player.getInventory().setBoots(boots);
        }
    }

}
