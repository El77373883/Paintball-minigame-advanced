package me.adrian.paintball.scoreboard;

import me.adrian.paintball.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardTask extends BukkitRunnable {

    private GameManager gameManager;

    public ScoreboardTask(GameManager gameManager){
        this.gameManager = gameManager;
    }

    @Override
    public void run(){

        for(Player p : Bukkit.getOnlinePlayers()){

            ScoreboardManager.update(p,gameManager);

        }

    }

}
