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

    private final Map<Player, GameTeam> playerTeams = new HashMap<>();
    private final Map<Player, Integer> playerKills = new HashMap<>();
    private final Map<Player, Integer> playerCoins = new HashMap<>();
    private final Map<Player, Integer> snowballCount = new HashMap<>();

    public GameManager() {
        this.state = GameState.WAITING;
    }

    // ---------------- ARENAS ---------------- //
    public void createArena(String name) {
        if (!arenas.containsKey(name)) {
            Arena arena = new Arena(name);
            arenas.put(name, arena);
            PaintballPlugin.getInstance().getLogger().info("Arena creada: " + name);
        }
    }

    public void setCurrentArena(String name) {
        this.currentArena = arenas.get(name);
    }

    public Arena getCurrentArena() {
        return currentArena;
    }

    public Collection<Arena> getArenas() {
        return arenas.values();
    }

    // ---------------- GAME ---------------- //
    public GameState getState() { return state; }

    public void startGame() {
        if (currentArena == null) return;
        state = GameState.IN_GAME;
        assignTeams();
        giveTeamArmor();
        PaintballPlugin.getInstance().getLogger().info("Juego iniciado en arena: " + currentArena.getName());
    }

    public void endGame() {
        state = GameState.FINISHED;
        playerTeams.clear();
        playerKills.clear();
        PaintballPlugin.getInstance().getLogger().info("Juego finalizado en arena: " + currentArena.getName());
    }

    public boolean isPlaying(Player player) { return playerTeams.containsKey(player); }
    public boolean isAlive(Player player) { return isPlaying(player) && playerKills.containsKey(player); }

    // ---------------- PLAYERS ---------------- //
    public void addPlayer(Player player, GameTeam team) {
        playerTeams.put(player, team);
        playerKills.put(player, 0);
        playerCoins.putIfAbsent(player, 32);
        snowballCount.putIfAbsent(player, 32);
    }

    public void removePlayer(Player player) {
        playerTeams.remove(player);
        playerKills.remove(player);
        snowballCount.remove(player);
        playerCoins.remove(player);
    }

    public void eliminate(Player shooter, Player eliminated) {
        if (!isAlive(eliminated)) return;

        playerKills.put(shooter, playerKills.getOrDefault(shooter, 0) + 1);
        playerCoins.put(shooter, playerCoins.getOrDefault(shooter, 0) + 5);
        removePlayer(eliminated);
    }

    // ---------------- TEAMS ---------------- //
    private void assignTeams() {
        if (currentArena == null) return;
        List<Player> players = new ArrayList<>(playerKills.keySet());
        Collections.shuffle(players);
        int half = players.size() / 2;

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            GameTeam team = i < half ? GameTeam.BLUE : GameTeam.GREEN;
            playerTeams.put(p, team);
        }
    }

    private void giveTeamArmor() {
        for (Map.Entry<Player, GameTeam> entry : playerTeams.entrySet()) {
            Player p = entry.getKey();
            Color color = entry.getValue() == GameTeam.BLUE ? Color.BLUE : Color.GREEN;

            ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
            ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
            ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

            LeatherArmorMeta meta;

            meta = (LeatherArmorMeta) helmet.getItemMeta();
            meta.setColor(color);
            helmet.setItemMeta(meta);

            meta = (LeatherArmorMeta) chest.getItemMeta();
            meta.setColor(color);
            chest.setItemMeta(meta);

            meta = (LeatherArmorMeta) legs.getItemMeta();
            meta.setColor(color);
            legs.setItemMeta(meta);

            meta = (LeatherArmorMeta) boots.getItemMeta();
            meta.setColor(color);
            boots.setItemMeta(meta);

            p.getInventory().setHelmet(helmet);
            p.getInventory().setChestplate(chest);
            p.getInventory().setLeggings(legs);
            p.getInventory().setBoots(boots);
        }
    }

    public GameTeam getTeam(Player player) { return playerTeams.get(player); }

    // ---------------- MÉTODOS DE COMPATIBILIDAD ---------------- //
    public int getKills(Player player) { return playerKills.getOrDefault(player, 0); }
    public int getCoins(Player player) { return playerCoins.getOrDefault(player, 0); }
    public void addCoins(Player player, int amount) { playerCoins.put(player, getCoins(player) + amount); }
    public void removeCoins(Player player, int amount) { playerCoins.put(player, Math.max(0, getCoins(player) - amount)); }
    public void refillSnowballs(Player player, int amount) { snowballCount.put(player, getSnowballs(player) + amount); }
    public int getSnowballs(Player player) { return snowballCount.getOrDefault(player, 0); }

    public PlayerData getPlayerData(Player player) {
        return new PlayerData(getKills(player), getCoins(player), getSnowballs(player));
    }

    public boolean canThrowSnowball(Player player) {
        snowballCount.putIfAbsent(player, 32);
        return snowballCount.get(player) > 0;
    }

    public void throwSnowball(Player player) {
        snowballCount.put(player, snowballCount.get(player) - 1);
    }

    public int getAliveCount() {
        int count = 0;
        for (Player p : playerTeams.keySet()) {
            if (isAlive(p)) count++;
        }
        return count;
    }
}
