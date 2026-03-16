package me.adrian.paintball.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.Bukkit;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private final String name;
    private Location spawnBlue;
    private Location spawnGreen;
    private List<Player> blueTeam = new ArrayList<>();
    private List<Player> greenTeam = new ArrayList<>();
    private List<Player> players = new ArrayList<>();

    public Arena(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSpawnBlue(Location loc) {
        this.spawnBlue = loc;
    }

    public void setSpawnGreen(Location loc) {
        this.spawnGreen = loc;
    }

    public void join(Player player, GameTeam team) {
        players.add(player);
        if (team == GameTeam.BLUE) {
            blueTeam.add(player);
            teleportAndGiveArmor(player, spawnBlue, GameTeam.BLUE.getColor());
        } else {
            greenTeam.add(player);
            teleportAndGiveArmor(player, spawnGreen, GameTeam.GREEN.getColor());
        }
    }

    private void teleportAndGiveArmor(Player player, Location loc, Color color) {
        player.teleport(loc);
        PlayerInventory inv = player.getInventory();
        inv.clear();

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

        inv.setHelmet(helmet);
        inv.setChestplate(chest);
        inv.setLeggings(legs);
        inv.setBoots(boots);

        inv.addItem(new ItemStack(Material.SNOWBALL, 32));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isPlayerInArena(Player p) {
        return players.contains(p);
    }
}
