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
                    player.sendMessage(ChatColor.AQUA + arena.getName() + ChatColor.GRAY + " - " + arena.getPlayers().size() + " jugadores");
                }
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
                // Asignar equipo automáticamente
                GameManager.GameTeam team = Math.random() < 0.5 ? GameManager.GameTeam.BLUE : GameManager.GameTeam.GREEN;
                gameManager.addPlayer(player, team);

                player.sendMessage(ChatColor.GREEN + "Te has unido a la arena " + ChatColor.AQUA + arena.getName() + ChatColor.GREEN + " en el equipo " + ChatColor.AQUA + team);
            }
            case "leave" -> {
                gameManager.removePlayer(player);
                player.sendMessage(ChatColor.YELLOW + "Has salido del juego.");
            }
            case "stats" -> {
                player.sendMessage(ChatColor.GREEN + "--- Tus Stats ---");
                player.sendMessage(ChatColor.AQUA + "Kills: " + ChatColor.YELLOW + gameManager.getKills(player));
                player.sendMessage(ChatColor.AQUA + "Coins: " + ChatColor.YELLOW + gameManager.getCoins(player));
                player.sendMessage(ChatColor.AQUA + "Snowballs: " + ChatColor.YELLOW + gameManager.getSnowballs(player));
            }
            case "creator" -> {
                player.sendMessage(ChatColor.GOLD + "Plugin Paintball by soyadrianyt001");
            }
            default -> player.sendMessage(ChatColor.RED + "Comando no válido. Usa /pa list, join, leave, stats");
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
