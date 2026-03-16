package me.adrian.paintball.game;

import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class GameManager {

    private final Map<String, Arena> arenas = new HashMap<>();
    private final Map<Player, Arena> playerArena = new HashMap<>();

    public GameManager() {}

    public void createArena(String name) {
        arenas.put(name, new Arena(name));
    }

    public Arena getArena(Player p) {
        return playerArena.get(p);
    }

    public void joinArena(Player p) {
        if (!arenas.isEmpty()) {
            Arena arena = arenas.values().stream().findAny().orElse(null);
            if (arena != null) {
                // Alterna equipos por tamaño
                GameTeam team = arena.getPlayers().size() % 2 == 0 ? GameTeam.BLUE : GameTeam.GREEN;
                arena.join(p, team);
                playerArena.put(p, arena);
            }
        }
    }

    public boolean isInArena(Player p) {
        return playerArena.containsKey(p);
    }
}
