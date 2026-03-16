package me.adrian.paintball.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.Arena;
import me.adrian.paintball.game.GameManager;

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
            sender.sendMessage("§cSolo jugadores pueden usar estos comandos!");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§6§lPA Admin Commands:");
            player.sendMessage("§7/pa admin panel §f- Abrir panel");
            player.sendMessage("§7/pa admin wand §f- Seleccionar area con hacha");
            player.sendMessage("§7/pa admin create <arena> §f- Crear arena");
            player.sendMessage("§7/pa admin reload §f- Recargar plugin");
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "panel":
                Arena arena = gameManager.getArena(player);
                if (arena != null) {
                    arena.openAdminPanel(player);
                } else {
                    player.sendMessage("§cNo estas en una arena!");
                }
                break;

            case "wand":
                ItemStack wand = new ItemStack(Material.WOODEN_AXE);
                player.getInventory().addItem(wand);
                player.sendMessage("§aHacha de selección otorgada!");
                break;

            case "create":
                if (args.length < 2) {
                    player.sendMessage("§cUso: /pa admin create <arena>");
                    return true;
                }
                String arenaName = args[1];
                gameManager.createArena(arenaName);
                player.sendMessage("§aArena " + arenaName + " creada correctamente!");
                break;

            case "reload":
                plugin.reloadConfig();
                player.sendMessage("§aPlugin recargado correctamente!");
                break;

            default:
                player.sendMessage("§cComando invalido!");
                break;
        }

        return true;
    }
}
