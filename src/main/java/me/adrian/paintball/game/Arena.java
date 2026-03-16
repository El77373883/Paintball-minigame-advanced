package me.adrian.paintball.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.Bukkit;
import java.util.ArrayList;
import java.util.List;

public class Arena {

    private final String name;
    private Location greenSpawn;
    private Location blueSpawn;

    private final List<Player> greenTeam = new ArrayList<>();
    private final List<Player> blueTeam = new ArrayList<>();
    private final List<Player> alivePlayers = new ArrayList<>();

    public Arena(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setGreenSpawn(Location loc) { this.greenSpawn = loc; }
    public void setBlueSpawn(Location loc) { this.blueSpawn = loc; }
    public Location getGreenSpawn() { return greenSpawn; }
    public Location getBlueSpawn() { return blueSpawn; }

    public void addPlayer(Player p, GameManager.GameTeam team) {
        alivePlayers.add(p);
        switch (team) {
            case GREEN -> {
                greenTeam.add(p);
                equipArmor(p, Color.GREEN);
            }
            case BLUE -> {
                blueTeam.add(p);
                equipArmor(p, Color.BLUE);
            }
        }
        teleportPlayer(p, team);
    }

    private void teleportPlayer(Player p, GameManager.GameTeam team) {
        Location loc = (team == GameManager.GameTeam.GREEN) ? greenSpawn : blueSpawn;
        if (loc != null) p.teleport(loc);
    }

    private void equipArmor(Player p, Color color) {
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

        LeatherArmorMeta hm = (LeatherArmorMeta) helmet.getItemMeta();
        LeatherArmorMeta cm = (LeatherArmorMeta) chest.getItemMeta();
        LeatherArmorMeta lm = (LeatherArmorMeta) legs.getItemMeta();
        LeatherArmorMeta bm = (LeatherArmorMeta) boots.getItemMeta();

        if (hm != null && cm != null && lm != null && bm != null) {
            hm.setColor(color);
            cm.setColor(color);
            lm.setColor(color);
            bm.setColor(color);

            helmet.setItemMeta(hm);
            chest.setItemMeta(cm);
            legs.setItemMeta(lm);
            boots.setItemMeta(bm);
        }

        p.getInventory().setHelmet(helmet);
        p.getInventory().setChestplate(chest);
        p.getInventory().setLeggings(legs);
        p.getInventory().setBoots(boots);
    }

    public List<Player> getGreenTeam() { return greenTeam; }
    public List<Player> getBlueTeam() { return blueTeam; }
    public List<Player> getAlivePlayers() { return alivePlayers; }

    public void removePlayer(Player p) {
        greenTeam.remove(p);
        blueTeam.remove(p);
        alivePlayers.remove(p);
    }

    public boolean isTeamAlive(GameManager.GameTeam team) {
        return !((team == GameManager.GameTeam.GREEN ? greenTeam : blueTeam).isEmpty());
    }
}
