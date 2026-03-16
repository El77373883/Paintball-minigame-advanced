package me.adrian.paintball.command;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.Arena;
import me.adrian.paintball.game.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PaintballAdminCommand implements CommandExecutor {

    private final PaintballPlugin plugin;
    private final GameManager gameManager;

    public PaintballAdminCommand(PaintballPlugin plugin) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cSolo jugadores pueden ejecutar este comando.");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage("§6=== Admin Commands ===");
            p.sendMessage("§e/paadmin createarena <name> §7- Crear arena");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "createarena":
                if (args.length < 2) {
                    p.sendMessage("§cDebes especificar un nombre para la arena.");
                    return true;
                }
                String arenaName = args[1];
                Arena arena = new Arena(arenaName);
                gameManager.addArena(arena); // ahora sí existe
                p.sendMessage("§aArena creada: §e" + arenaName);
                return true;

            default:
                p.sendMessage("§cComando no reconocido.");
                return true;
        }
    }
}
