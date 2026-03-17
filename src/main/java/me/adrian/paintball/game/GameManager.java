package me.adrian.paintball.game;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GameManager {

    private Map<String, Arena> arenas = new HashMap<>();
    private Map<Player, PlayerData> playerData = new HashMap<>();

    private int gameTime = 300; // 5 minutos

    public void createArena(String name){
        arenas.put(name,new Arena(name));
    }

    public Arena getArena(String name){
        return arenas.get(name);
    }

    public Map<String,Arena> getArenas(){
        return arenas;
    }

    public void addPlayerToArena(Player p,String arenaName){

        Arena arena = arenas.get(arenaName);

        if(arena == null) return;

        arena.addPlayer(p);

        if(!playerData.containsKey(p)){
            playerData.put(p,new PlayerData(p));
        }

    }

    public void removePlayer(Player p){

        for(Arena arena : arenas.values()){

            if(arena.getPlayers().contains(p)){
                arena.removePlayer(p);
                break;
            }

        }

    }

    public PlayerData getPlayerData(Player p){
        return playerData.get(p);
    }

    public int getCoins(Player p){

        PlayerData data = playerData.get(p);

        if(data == null) return 0;

        return data.getCoins();

    }

    public void addCoins(Player p,int amount){

        PlayerData data = playerData.get(p);

        if(data == null){
            data = new PlayerData(p);
            playerData.put(p,data);
        }

        data.addCoins(amount);

    }

    public int getAliveCount(){

        int count = 0;

        for(Arena arena : arenas.values()){
            count += arena.getPlayers().size();
        }

        return count;

    }

    public int getTime(){
        return gameTime;
    }

    public void setTime(int time){
        this.gameTime = time;
    }

}
