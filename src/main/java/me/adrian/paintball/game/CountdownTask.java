package me.adrian.paintball.game;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountdownTask extends BukkitRunnable {

    private int time = 10;
    private Arena arena;

    public CountdownTask(Arena arena){
        this.arena = arena;
    }

    @Override
    public void run(){

        if(time == 0){

            for(Player p : arena.getPlayers()){
                p.sendTitle("§a§lGO!", "§7La partida comenzó",10,40,10);
                p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL,1,1);
            }

            cancel();
            return;
        }

        for(Player p : arena.getPlayers()){
            p.sendTitle("§e"+time,"§7La partida comienza...",0,20,0);
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING,1,1);
        }

        time--;

    }

}
