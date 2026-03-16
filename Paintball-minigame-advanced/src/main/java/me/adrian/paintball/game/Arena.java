package me.adrian.paintball.game;

import java.util.List;
import me.adrian.paintball.game.GameTeam; // importa el enum si lo usas
import org.bukkit.entity.Player; // si necesitas jugadores de Bukkit

public class Arena {

    private String name;
    private List<Player> players; // ejemplo de lista de jugadores
    private GameTeam team; // ejemplo de equipo asignado

    public Arena(String name) {
        this.name = name;
        // inicializa otras cosas si quieres
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameTeam getTeam() {
        return team;
    }

    public void setTeam(GameTeam team) {
        this.team = team;
    }

    // Aquí puedes agregar más métodos para lógica del juego
}
