package me.adrian.paintball.scoreboard;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStartTask extends BukkitRunnable {

    private int countdown = 10;
    private Arena arena;
    private PaintballPlugin plugin;

    public GameStartTask(PaintballPlugin plugin, Arena arena){
        this.plugin = plugin;
        this.arena = arena;
    }

    @Override
    public void run(){

        if(countdown == 0){

            arena.setStarted(true);

            for(Player p : arena.getPlayers()){

                p.sendTitle("§a¡GO!", "§7La partida comenzó",10,40,10);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);

            }

            new GameTimerTask(plugin, arena).runTaskTimer(plugin,20,20);

            cancel();
            return;
        }

        for(Player p : arena.getPlayers()){

            p.sendTitle("§e" + countdown,"§7La partida comienza",0,20,0);
            p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK,1,1);

        }

        countdown--;

    }

}
