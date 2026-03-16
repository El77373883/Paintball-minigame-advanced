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

import java.util.List;
import java.util.stream.Collectors;

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
            sender.sendMessage(ChatColor.RED + "Solo jugadores pueden usar este comando.");
            return true;
        }

        if (args.length == 0) {
            openArenaMenu(player);
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "join":
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Usa: /pa join <arena>");
                    return true;
                }
                Arena arena = gameManager.getArenas().stream()
                        .filter(a -> a.getName().equalsIgnoreCase(args[1]))
                        .findFirst().orElse(null);
                if (arena == null) {
                    player.sendMessage(ChatColor.RED + "Arena no encontrada!");
                    return true;
                }
                gameManager.addPlayer(player);
                gameManager.setCurrentArena(arena.getName());
                player.sendMessage(ChatColor.GREEN + "Te has unido a la arena: " + ChatColor.GOLD + arena.getName());
                return true;

            case "leave":
                if (!gameManager.isPlaying(player)) {
                    player.sendMessage(ChatColor.RED + "No estás en ninguna arena.");
                    return true;
                }
                gameManager.removePlayer(player);
                player.sendMessage(ChatColor.YELLOW + "Has salido de la arena.");
                return true;

            case "list":
                String list = gameManager.getArenas().stream()
                        .map(a -> a.getName() + " (" + a.getPlayers().size() + " jugadores)")
                        .collect(Collectors.joining(", "));
                player.sendMessage(ChatColor.AQUA + "Arenas disponibles: " + ChatColor.GREEN + list);
                return true;

            case "creator":
                player.sendMessage(ChatColor.GOLD + "§lPaintball Plugin");
                player.sendMessage(ChatColor.WHITE + "Creado con ❤️ por " + ChatColor.AQUA + "Adrianyt001");
                return true;

            default:
                player.sendMessage(ChatColor.RED + "Comando desconocido. Usa /pa, /pa join <arena>, /pa leave, /pa list, /pa creator");
                return true;
        }
    }

    private void openArenaMenu(Player player) {
        List<Arena> arenas = gameManager.getArenas().stream().toList();
        Inventory menu = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Selecciona una Arena");

        for (Arena arena : arenas) {
            ItemStack item = new ItemStack(Material.GRASS_BLOCK);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GOLD + arena.getName());
            meta.setLore(List.of(ChatColor.GREEN + "Jugadores: " + arena.getPlayers().size()));
            item.setItemMeta(meta);
            menu.addItem(item);
        }

        player.openInventory(menu);
    }
}
