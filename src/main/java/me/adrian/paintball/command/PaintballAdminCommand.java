package me.adrian.paintball.command;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.gui.AdminPanelGUI;
import me.adrian.paintball.game.GameManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PaintballAdminCommand implements CommandExecutor {

    private final GameManager gm = PaintballPlugin.getInstance().getGameManager();
    private final AdminPanelGUI panelGUI = new AdminPanelGUI(gm);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) return true;

        if (args.length == 0) {
            p.sendMessage("§6/paadmin help - Mostrar comandos admin");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "panel" -> panelGUI.openPanel(p);
            case "wand" -> giveWand(p);
            case "create" -> p.sendMessage("§eArena creada correctamente");
            case "reload" -> {
                PaintballPlugin.getInstance().reloadConfig();
                p.sendMessage("§aPlugin recargado");
            }
            default -> p.sendMessage("§cSubcomando inválido");
        }

        return true;
    }

    private void giveWand(Player p) {
        ItemStack axe = new ItemStack(Material.WOODEN_AXE);
        p.getInventory().addItem(axe);
        p.sendMessage("§eHas recibido el hacha para seleccionar el área de la arena.");
    }
}
