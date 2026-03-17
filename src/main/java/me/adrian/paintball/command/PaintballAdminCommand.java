package me.adrian.paintball.command;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.gui.AdminPanelGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PaintballAdminCommand implements CommandExecutor {

    private PaintballPlugin plugin;

    public PaintballAdminCommand(PaintballPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(args.length == 1){

            if(args[0].equalsIgnoreCase("panel")){

                new AdminPanelGUI(plugin).open(p);

                return true;

            }

        }

        p.sendMessage("§bPaintballAdvanced §7/pa panel");

        return true;

    }

}
