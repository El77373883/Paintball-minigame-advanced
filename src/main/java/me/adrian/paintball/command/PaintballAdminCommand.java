package me.adrian.paintball.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import me.adrian.paintball.PaintballPlugin;

public class PaintballAdminCommand implements CommandExecutor {

    private final PaintballPlugin plugin;

    public PaintballAdminCommand(PaintballPlugin plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage("§cComando de administración del Paintball Minigame");
        return true;
    }
}
