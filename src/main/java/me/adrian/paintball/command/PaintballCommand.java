package me.adrian.paintball.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import me.adrian.paintball.PaintballPlugin;

public class PaintballCommand implements CommandExecutor {

    private final PaintballPlugin plugin;

    public PaintballCommand(PaintballPlugin plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage("§aBienvenido al Paintball Minigame!");
        return true;
    }
}
