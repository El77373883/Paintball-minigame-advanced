package me.adrian.paintball.command;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.Arena;
import me.adrian.paintball.game.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PaintballAdminCommand implements CommandExecutor {

    private final PaintballPlugin plugin;
    private final GameManager gameManager;

    public PaintballAdminCommand(PaintballPlugin plugin) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("§cUsa: /paadmin arena <crear/nombre>");
            return true;
        }

        if (args[0].equalsIgnoreCase("arena")) {
            if (args.length < 2) {
                sender.sendMessage("§cDebes poner un nombre para la arena.");
                return true;
            }

            String name = args[1];

            // ✅ Aquí reemplazamos addArena() por createArena()
            // Antes: gameManager.addArena(new Arena(name));
            // Ahora:
            gameManager.createArena(name);

            sender.sendMessage("§aArena creada: " + name);
            return true;
        }

        return false;
    }
}
