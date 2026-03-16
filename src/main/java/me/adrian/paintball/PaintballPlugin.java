package me.adrian.paintball;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.PlayerInventory;
import me.adrian.paintball.PaintballPlugin;

import java.util.*;

public class GameManager {

    private final PaintballPlugin plugin;
    private final Map<Player, Arena> playerArena = new HashMap<>();
    private final Map<Player, Integer> coins = new HashMap<>();
    private final Map<Player, Integer> kills = new HashMap<>();
    private final Map<Player, Integer> snowballs = new HashMap<>();
    private final List<Arena> arenas = new ArrayList<>();
    private GameState state = GameState.WAITING;

    public GameManager(PaintballPlugin plugin) {
        this.plugin = plugin;
    }

    public enum GameState {
        WAITING, IN_GAME, END
    }

    public void joinArena(Player player, Arena arena) {
        playerArena.put(player, arena);
        coins.put(player, 10); // 10 coins default
        kills.put(player, 0);
        snowballs.put(player, 64*2); // 2 stacks
        giveTeamArmor(player, arena.getTeam(player));
        giveSnowballs(player);
    }

    private void giveTeamArmor(Player player, GameTeam team) {
        PlayerInventory inv = player.getInventory();
        inv.clear();
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta;

        meta = (LeatherArmorMeta) helmet.getItemMeta();
        meta.setColor(team == GameTeam.BLUE ? Color.BLUE : Color.GREEN);
        helmet.setItemMeta(meta);

        meta = (LeatherArmorMeta) chest.getItemMeta();
        meta.setColor(team == GameTeam.BLUE ? Color.BLUE : Color.GREEN);
        chest.setItemMeta(meta);

        meta = (LeatherArmorMeta) legs.getItemMeta();
        meta.setColor(team == GameTeam.BLUE ? Color.BLUE : Color.GREEN);
        legs.setItemMeta(meta);

        meta = (LeatherArmorMeta) boots.getItemMeta();
        meta.setColor(team == GameTeam.BLUE ? Color.BLUE : Color.GREEN);
        boots.setItemMeta(meta);

        inv.setHelmet(helmet);
        inv.setChestplate(chest);
        inv.setLeggings(legs);
        inv.setBoots(boots);
    }

    private void giveSnowballs(Player player) {
        player.getInventory().addItem(new ItemStack(Material.SNOWBALL, snowballs.get(player)));
    }

    public void eliminate(Player shooter, Player eliminated) {
        if (!kills.containsKey(shooter)) return;
        kills.put(shooter, kills.get(shooter) + 1);
        coins.put(shooter, coins.get(shooter) + 6);
        PlayerArenaEffects(eliminated);
    }

    private void PlayerArenaEffects(Player eliminated) {
        eliminated.getWorld().strikeLightningEffect(eliminated.getLocation());
        eliminated.getWorld().spawnParticle(org.bukkit.Particle.CRIT_MAGIC, eliminated.getLocation(), 30, 0.5,0.5,0.5,0.1);
        eliminated.getWorld().playSound(eliminated.getLocation(), org.bukkit.Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f,1.0f);
    }

    public Arena getArena(Player player) {
        return playerArena.get(player);
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public int getCoins(Player player) {
        return coins.getOrDefault(player,0);
    }

    public int getKills(Player player) {
        return kills.getOrDefault(player,0);
    }

    public int getSnowballs(Player player) {
        return snowballs.getOrDefault(player,0);
    }

    public void removePlayer(Player player) {
        playerArena.remove(player);
        coins.remove(player);
        kills.remove(player);
        snowballs.remove(player);
    }

    public List<Arena> getArenas() {
        return arenas;
    }

    public void addArena(Arena arena) {
        arenas.add(arena);
    }
}
