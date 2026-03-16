package me.adrian.paintball.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.menu.ArenaPanel;

public class PaintballCommand implements CommandExecutor {

    private final PaintballPlugin plugin;
    private final GameManager gameManager;

    public PaintballCommand(PaintballPlugin plugin) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cSolo jugadores pueden usar estos comandos!");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§6§lPAINTBALL §f- Comandos disponibles: /pa help");
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "join":
                if (!gameManager.isInArena(player)) {
                    gameManager.joinArena(player);
                    player.sendMessage("§aTe uniste a la arena!");
                } else {
                    player.sendMessage("§cYa estas en una arena!");
                }
                break;

            case "help":
                player.sendMessage("§6§lPA Commands:");
                player.sendMessage("§7/pa join §f- Entrar al juego");
                player.sendMessage("§7/pa edit arena §f- Editar tu arena");
                player.sendMessage("§7/pa creator §f- Mostrar creador del plugin");
                player.sendMessage("§7/pa admin panel §f- Panel de administración de arenas");
                player.sendMessage("§7/pa admin wand §f- Seleccionar area con hacha");
                player.sendMessage("§7/pa admin create <arena> §f- Crear arena nueva");
                player.sendMessage("§7/pa admin reload §f- Recargar plugin");
                break;

            case "creator":
                player.sendMessage("§bPlugin creado por §aAdrianelPror");
                break;

            case "edit":
                if (args.length > 1 && args[1].equalsIgnoreCase("arena")) {
                    ArenaPanel panel = new ArenaPanel(player, gameManager.getArena(player));
                    panel.open();
                } else {
                    player.sendMessage("§cUso: /pa edit arena");
                }
                break;

            default:
                player.sendMessage("§cComando invalido! Usa /pa help");
                break;
        }

        return true;
    }
}
