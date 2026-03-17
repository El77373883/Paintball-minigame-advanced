package me.adrian.paintball.game;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private String name;
    private boolean started;

    private List<Player> players = new ArrayList<>();

    public Arena(String name){
        this.name = name;
        this.started = false;
    }

    public String getName(){
        return name;
    }

    public boolean isStarted(){
        return started;
    }

    public void setStarted(boolean started){
        this.started = started;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public void addPlayer(Player p){

        players.add(p);

        // limpiar inventario
        p.getInventory().clear();

        // dar snowballs
        p.getInventory().addItem(new ItemStack(Material.SNOWBALL,64));
        p.getInventory().addItem(new ItemStack(Material.SNOWBALL,64));

        // armadura
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

        p.getInventory().setHelmet(helmet);
        p.getInventory().setChestplate(chest);
        p.getInventory().setLeggings(legs);
        p.getInventory().setBoots(boots);

        p.sendMessage("§aEntraste a la arena de Paintball!");
    }

    public void removePlayer(Player p){

        players.remove(p);

        p.getInventory().clear();

        p.sendMessage("§cSaliste de la arena.");
    }

    public void reset(){

        for(Player p : players){

            p.getInventory().clear();
            p.sendMessage("§eLa partida terminó.");

        }

        players.clear();
        started = false;

    }

}
