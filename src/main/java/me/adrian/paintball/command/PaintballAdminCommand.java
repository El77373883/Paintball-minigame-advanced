package me.adrian.paintball.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.Arena;

public class PaintballAdminCommand implements CommandExecutor {

    private final PaintballPlugin plugin;

    public PaintballAdminCommand(PaintballPlugin plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cSolo jugadores pueden ejecutar comandos admin.");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage("§6=== Admin Paintball ===");
            p.sendMessage("§e/paadmin create <arena> §7- Crear arena");
            p.sendMessage("§e/paadmin edit <arena> §7- Editar arena");
            p.sendMessage("§e/paadmin reload §7- Recargar plugin");
            p.sendMessage("§e/paadmin panel <arena> §7- Abrir panel GUI");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                try {
                    plugin.reloadConfig();
                    p.sendMessage("§aPlugin recargado correctamente.");
                } catch (Exception e) {
                    p.sendMessage("§cError detectado al recargar config: " + e.getMessage());
                }
                return true;
            case "create":
                if (args.length < 2) { p.sendMessage("§cUsa /paadmin create <nombre>"); return true; }
                Arena arena = new Arena(args[1]);
                plugin.getGameManager().addArena(arena);
                p.sendMessage("§aArena creada: §e" + args[1]);
                return true;
            case "panel":
                if (args.length < 2) { p.sendMessage("§cUsa /paadmin panel <arena>"); return true; }
                Arena a = plugin.getGameManager().getArenas().stream()
                        .filter(ar -> ar.getName().equalsIgnoreCase(args[1])).findFirst().orElse(null);
                if (a == null) { p.sendMessage("§cArena no encontrada."); return true; }
                new me.adrian.paintball.gui.ArenaPanelGUI().open(p, a);
                return true;
            default:
                p.sendMessage("§cComando admin no reconocido.");
                return true;
        }
    }
}
