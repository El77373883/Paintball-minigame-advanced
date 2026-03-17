package me.adrian.paintball.scoreboard;

import me.adrian.paintball.game.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStartTask extends BukkitRunnable {

    private Arena arena;
    private int countdown = 10;

    public GameStartTask(Arena arena){
        this.arena = arena;
    }

    @Override
    public void run(){

        if(countdown == 0){

            for(Player p : arena.getPlayers()){

                p.sendTitle("§aGO!", "§fLa partida comenzó",10,40,10);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);

            }

            arena.setStarted(true);
            cancel();
            return;

        }

        for(Player p : arena.getPlayers()){

            p.sendTitle("§e"+countdown,"§fLa partida empieza",10,20,10);
            p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK,1,1);

        }

        countdown--;

    }

}
