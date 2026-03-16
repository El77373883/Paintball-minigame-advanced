package me.adrian.paintball.command;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.Arena;
import me.adrian.paintball.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collection;

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
            sender.sendMessage(ChatColor.RED + "Solo los jugadores pueden ejecutar este comando.");
            return true;
        }

        // Check de permisos
        if (!player.hasPermission("paintball.admin")) {
            player.sendMessage(ChatColor.RED + "No tienes permisos para usar comandos de administración.");
            return true;
        }

        // Si no hay argumentos, abrir el panel
        if (args.length == 0 || args[0].equalsIgnoreCase("panel")) {
            openAdminPanel(player);
            return true;
        }

        // Subcomandos admin
        switch (args[0].toLowerCase()) {
            case "create" -> {
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Uso: /pa admin create <arena>");
                    return true;
                }
                String name = args[1];
                gameManager.createArena(name);
                player.sendMessage(ChatColor.GREEN + "Arena creada: " + ChatColor.AQUA + name);
            }
            case "delete" -> {
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Uso: /pa admin delete <arena>");
                    return true;
                }
                String name = args[1];
                Arena arena = gameManager.getArenas().stream()
                        .filter(a -> a.getName().equalsIgnoreCase(name))
                        .findFirst().orElse(null);
                if (arena == null) {
                    player.sendMessage(ChatColor.RED + "Arena no encontrada: " + name);
                    return true;
                }
                gameManager.getArenas().remove(arena);
                player.sendMessage(ChatColor.YELLOW + "Arena eliminada: " + ChatColor.AQUA + name);
            }
            case "set" -> {
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Uso: /pa admin set <arena>");
                    return true;
                }
                String name = args[1];
                gameManager.setCurrentArena(name);
                player.sendMessage(ChatColor.GREEN + "Arena actual: " + ChatColor.AQUA + name);
            }
            case "start" -> {
                if (gameManager.getCurrentArena() == null) {
                    player.sendMessage(ChatColor.RED + "Primero debes seleccionar un arena con /pa admin set <arena>");
                    return true;
                }
                gameManager.startGame();
                player.sendMessage(ChatColor.GREEN + "Juego iniciado en arena " + ChatColor.AQUA + gameManager.getCurrentArena().getName());
            }
            case "stop" -> {
                if (gameManager.getCurrentArena() == null) {
                    player.sendMessage(ChatColor.RED + "No hay un juego en curso para detener.");
                    return true;
                }
                gameManager.endGame();
                player.sendMessage(ChatColor.RED + "Juego finalizado en arena " + ChatColor.AQUA + gameManager.getCurrentArena().getName());
            }
            default -> player.sendMessage(ChatColor.RED + "Subcomando admin inválido. Usa /pa panel para abrir el menú de administración.");
        }

        return true;
    }

    private void openAdminPanel(Player player) {
        Collection<Arena> arenas = gameManager.getArenas();
        int size = 9 * ((arenas.size() + 8) / 9);
        Inventory menu = Bukkit.createInventory(null, size, ChatColor.DARK_GREEN + "Panel de Administración");

        for (Arena arena : arenas) {
            ItemStack item = new ItemStack(Material.SNOWBALL);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + arena.getName());
            meta.setLore(java.util.List.of(
                    ChatColor.YELLOW + "Jugadores conectados: " + arena.getPlayers().size(),
                    ChatColor.GRAY + "Haz click para gestionar"
            ));
            item.setItemMeta(meta);
            menu.addItem(item);
        }

        player.openInventory(menu);
    }
}
