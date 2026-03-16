package me.adrian.paintball.game;

import org.bukkit.Color;

public enum GameTeam {
    BLUE(Color.BLUE),
    GREEN(Color.GREEN);

    private final Color color;

    GameTeam(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
