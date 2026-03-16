package me.adrian.paintball.command;

import me.adrian.paintball.game.Arena;
import me.adrian.paintball.game.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PaintballAdminCommand implements CommandExecutor {

    private final GameManager gameManager;

    public PaintballAdminCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cSolo jugadores pueden usar este comando.");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage("§cFalta argumento.");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "createarena":
                if (args.length < 2) {
                    p.sendMessage("§cUsa /admin createarena <nombre>");
                    return true;
                }
                String arenaName = args[1];
                Arena arena = new Arena(arenaName);
                gameManager.addArena(arena);
                p.sendMessage("§aArena creada: §e" + arenaName);
                return true;
            default:
                p.sendMessage("§cComando no reconocido.");
                return true;
        }
    }
}
