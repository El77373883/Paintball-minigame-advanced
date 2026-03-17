package me.adrian.paintball.game;

import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTask extends BukkitRunnable {

    private int time = 300;
    private Arena arena;

    public GameTask(Arena arena){
        this.arena = arena;
    }

    @Override
    public void run(){

        if(time <= 0){

            Player winner = null;
            int bestKills = 0;

            for(Player p : arena.getPlayers()){

                PlayerData data = new PlayerData();

                if(data.getKills() > bestKills){
                    bestKills = data.getKills();
                    winner = p;
                }

            }

            if(winner != null){

                for(Player p : arena.getPlayers()){

                    p.sendTitle("§6"+winner.getName(),"§eGanó la partida!",10,60,10);

                    spawnFirework(p.getLocation());

                }

            }

            cancel();
            return;
        }

        time--;

    }

    private void spawnFirework(Location loc){

        Firework fw = loc.getWorld().spawn(loc, Firework.class);

        FireworkMeta meta = fw.getFireworkMeta();

        meta.addEffect(FireworkEffect.builder()
                .flicker(true)
                .trail(true)
                .build());

        meta.setPower(1);

        fw.setFireworkMeta(meta);

    }

}
