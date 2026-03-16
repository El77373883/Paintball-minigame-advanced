package me.adrian.paintball.command;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.Arena;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.shop.ShopGUI;
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

public class PaintballCommand implements CommandExecutor {

    private final PaintballPlugin plugin;
    private final GameManager gameManager;
    private final ShopGUI shopGUI;

    public PaintballCommand(PaintballPlugin plugin) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
        this.shopGUI = plugin.getShopGUI();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Solo los jugadores pueden ejecutar este comando.");
            return true;
        }

        if (args.length == 0) {
            openArenaMenu(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "list" -> {
                Collection<Arena> arenas = gameManager.getArenas();
                if (arenas.isEmpty()) {
                    player.sendMessage(ChatColor.RED + "No hay arenas creadas.");
                    return true;
                }
                player.sendMessage(ChatColor.GREEN + "--- Arenas disponibles ---");
                for (Arena arena : arenas) {
                    int count = arena.getPlayers().size();
                    player.sendMessage(ChatColor.AQUA + arena.getName() + ChatColor.GRAY + " - " + count + " jugadores conectados");
                }
            }
            case "creator" -> {
                player.sendMessage(ChatColor.GOLD + "Plugin Paintball by soyadrianyt001");
                player.sendMessage(ChatColor.GREEN + "Creador: soyadrianyt001");
                player.sendMessage(ChatColor.AQUA + "Disfruta el juego y personaliza tus arenas!");
            }
            case "join" -> {
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Uso correcto: /pa join <arena>");
                    return true;
                }
                Arena arena = gameManager.getArenas().stream()
                        .filter(a -> a.getName().equalsIgnoreCase(args[1]))
                        .findFirst().orElse(null);
                if (arena == null) {
                    player.sendMessage(ChatColor.RED + "Arena " + args[1] + " no encontrada.");
                    return true;
                }
                arena.addPlayer(player);
                player.sendMessage(ChatColor.GREEN + "Te has unido a la arena " + ChatColor.AQUA + arena.getName());
            }
            case "leave" -> {
                gameManager.removePlayer(player);
                player.sendMessage(ChatColor.YELLOW + "Has salido del juego.");
            }
            case "stats" -> {
                int kills = gameManager.getKills(player);
                int coins = gameManager.getCoins(player);
                int snowballs = gameManager.getSnowballs(player);

                player.sendMessage(ChatColor.GREEN + "--- Tus Stats ---");
                player.sendMessage(ChatColor.AQUA + "Kills: " + ChatColor.YELLOW + kills);
                player.sendMessage(ChatColor.AQUA + "Coins: " + ChatColor.YELLOW + coins);
                player.sendMessage(ChatColor.AQUA + "Snowballs: " + ChatColor.YELLOW + snowballs);
            }
            case "admin" -> {
                if (!player.hasPermission("paintball.admin")) {
                    player.sendMessage(ChatColor.RED + "No tienes permisos para esto.");
                    return true;
                }
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Uso: /pa admin <create|delete|set|start|stop>");
                    return true;
                }
                switch (args[1].toLowerCase()) {
                    case "create" -> {
                        if (args.length < 3) {
                            player.sendMessage(ChatColor.RED + "Uso: /pa admin create <arena>");
                            return true;
                        }
                        gameManager.createArena(args[2]);
                        player.sendMessage(ChatColor.GREEN + "Arena creada: " + ChatColor.AQUA + args[2]);
                    }
                    case "delete" -> {
                        if (args.length < 3) {
                            player.sendMessage(ChatColor.RED + "Uso: /pa admin delete <arena>");
                            return true;
                        }
                        Arena arena = gameManager.getArenas().stream()
                                .filter(a -> a.getName().equalsIgnoreCase(args[2]))
                                .findFirst().orElse(null);
                        if (arena == null) {
                            player.sendMessage(ChatColor.RED + "Arena no encontrada: " + args[2]);
                            return true;
                        }
                        gameManager.getArenas().remove(arena);
                        player.sendMessage(ChatColor.YELLOW + "Arena eliminada: " + ChatColor.AQUA + args[2]);
                    }
                    case "set" -> {
                        if (args.length < 3) {
                            player.sendMessage(ChatColor.RED + "Uso: /pa admin set <arena>");
                            return true;
                        }
                        gameManager.setCurrentArena(args[2]);
                        player.sendMessage(ChatColor.GREEN + "Arena actual: " + ChatColor.AQUA + args[2]);
                    }
                    case "start" -> {
                        gameManager.startGame();
                        player.sendMessage(ChatColor.GREEN + "Juego iniciado en arena " + ChatColor.AQUA + gameManager.getCurrentArena().getName());
                    }
                    case "stop" -> {
                        gameManager.endGame();
                        player.sendMessage(ChatColor.RED + "Juego finalizado en arena " + ChatColor.AQUA + gameManager.getCurrentArena().getName());
                    }
                    default -> player.sendMessage(ChatColor.RED + "Subcomando admin inválido.");
                }
            }
            default -> player.sendMessage(ChatColor.RED + "Comando no válido. Usa /pa o /pa help");
        }

        return true;
    }

    private void openArenaMenu(Player player) {
        Collection<Arena> arenas = gameManager.getArenas();
        if (arenas.isEmpty()) {
            player.sendMessage(ChatColor.RED + "No hay arenas creadas.");
            return;
        }

        Inventory menu = Bukkit.createInventory(null, 9 * ((arenas.size() + 8) / 9), ChatColor.GREEN + "Arenas de Paintball");

        for (Arena arena : arenas) {
            ItemStack item = new ItemStack(Material.SNOWBALL);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + arena.getName());
            meta.setLore(java.util.List.of(
                    ChatColor.YELLOW + "Jugadores conectados: " + arena.getPlayers().size(),
                    ChatColor.GRAY + "Haz click para unirte"
            ));
            item.setItemMeta(meta);
            menu.addItem(item);
        }

        player.openInventory(menu);
    }
}
