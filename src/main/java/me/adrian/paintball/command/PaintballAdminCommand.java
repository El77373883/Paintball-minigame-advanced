package me.adrian.paintball.command;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Solo jugadores pueden usar este comando.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usa /paadmin <create|delete|setspawn|start|end|list|reload|setlobby>");
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "create":
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Usa: /paadmin create <arena>");
                    return true;
                }
                gameManager.createArena(args[1]);
                player.sendMessage(ChatColor.GREEN + "Arena creada: " + ChatColor.GOLD + args[1]);
                return true;

            case "delete":
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Usa: /paadmin delete <arena>");
                    return true;
                }
                gameManager.getArenas().removeIf(a -> a.getName().equalsIgnoreCase(args[1]));
                player.sendMessage(ChatColor.YELLOW + "Arena eliminada: " + ChatColor.GOLD + args[1]);
                return true;

            case "list":
                String list = gameManager.getArenas().stream()
                        .map(a -> a.getName() + " (" + a.getPlayers().size() + " jugadores)")
                        .reduce((a1, a2) -> a1 + ", " + a2).orElse("No hay arenas");
                player.sendMessage(ChatColor.AQUA + "Arenas: " + ChatColor.GREEN + list);
                return true;

            case "reload":
                plugin.reloadConfig();
                player.sendMessage(ChatColor.GREEN + "Plugin recargado.");
                return true;

            default:
                player.sendMessage(ChatColor.RED + "Comando desconocido. Usa /paadmin create/delete/list/reload");
                return true;
        }
    }
}
