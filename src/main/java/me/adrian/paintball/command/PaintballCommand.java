package me.adrian.paintball.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.shop.ShopGUI;

public class PaintballCommand implements CommandExecutor {

    private final PaintballPlugin plugin;
    private final ShopGUI shopGUI;

    public PaintballCommand(PaintballPlugin plugin) {
        this.plugin = plugin;
        this.shopGUI = new ShopGUI(plugin.getGameManager());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cSolo jugadores pueden ejecutar este comando.");
            return true;
        }

        GameManager gm = plugin.getGameManager();

        if (args.length == 0) {
            p.sendMessage("§6=== Paintball ===");
            p.sendMessage("§e/pa join §7- Entrar a una arena");
            p.sendMessage("§e/pa shop §7- Abrir tienda");
            p.sendMessage("§e/pa help §7- Comandos disponibles");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "join":
                if (gm.getArenas().isEmpty()) {
                    p.sendMessage("§cNo hay arenas disponibles.");
                    return true;
                }
                // Tomamos la primera arena del Map
                String firstArenaName = gm.getArenas().iterator().next().getName();
                gm.setCurrentArena(firstArenaName);
                gm.addPlayer(p);
                p.sendMessage("§aHas entrado a la arena §e" + firstArenaName);
                return true;
            case "shop":
                shopGUI.open(p);
                return true;
            case "help":
                p.sendMessage("§6Comandos de Paintball:");
                p.sendMessage("§e/pa join §7- Entrar a la arena");
                p.sendMessage("§e/pa shop §7- Abrir tienda");
                return true;
            default:
                p.sendMessage("§cComando no reconocido. Usa /pa help");
                return true;
        }
    }
}
