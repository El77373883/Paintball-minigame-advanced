package me.adrian.paintball.shop;

import me.adrian.paintball.PaintballPlugin;
import me.adrian.paintball.game.GameManager;
import me.adrian.paintball.utils.CoinsManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ShopGUI {

    private final PaintballPlugin plugin;
    private final GameManager gameManager;

    public ShopGUI(GameManager gameManager) {
        this.plugin = PaintballPlugin.getInstance();
        this.gameManager = gameManager;
    }

    public void open(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9*3, "§6Paintball Shop");

        // Snowballs Pack
        ItemStack snowballs = new ItemStack(Material.SNOWBALL, 32);
        ItemMeta meta = snowballs.getItemMeta();
        meta.setDisplayName("§bPaquete de 32 Snowballs");
        List<String> lore = new ArrayList<>();
        lore.add("§7Precio: 5 coins");
        meta.setLore(lore);
        snowballs.setItemMeta(meta);
        inv.setItem(0, snowballs);

        // Paquete de 64 Snowballs
        ItemStack snowballs64 = new ItemStack(Material.SNOWBALL, 64);
        meta = snowballs64.getItemMeta();
        meta.setDisplayName("§bPaquete de 64 Snowballs");
        lore = new ArrayList<>();
        lore.add("§7Precio: 9 coins");
        meta.setLore(lore);
        snowballs64.setItemMeta(meta);
        inv.setItem(1, snowballs64);

        // Armas (ejemplo: paintball gun)
        ItemStack gun = new ItemStack(Material.BLAZE_ROD);
        meta = gun.getItemMeta();
        meta.setDisplayName("§cPaintball Gun");
        lore = new ArrayList<>();
        lore.add("§7Precio: 20 coins");
        meta.setLore(lore);
        gun.setItemMeta(meta);
        inv.setItem(2, gun);

        // Volver
        ItemStack back = new ItemStack(Material.BARRIER);
        meta = back.getItemMeta();
        meta.setDisplayName("§4Volver");
        back.setItemMeta(meta);
        inv.setItem(8, back);

        p.openInventory(inv);
    }

    // Comprar item
    public boolean buy(Player p, ItemStack item) {
        String name = item.getItemMeta().getDisplayName();

        if (name.contains("32 Snowballs")) {
            if (CoinsManager.getCoins(p) >= 5) {
                CoinsManager.removeCoins(p,5);
                p.getInventory().addItem(new ItemStack(Material.SNOWBALL,32));
                p.sendMessage("§6[PaintballAdvanced] §aCompraste 32 Snowballs");
                return true;
            } else {
                p.sendMessage("§6[PaintballAdvanced] §cNo tienes coins suficientes");
                return false;
            }
        }

        if (name.contains("64 Snowballs")) {
            if (CoinsManager.getCoins(p) >= 9) {
                CoinsManager.removeCoins(p,9);
                p.getInventory().addItem(new ItemStack(Material.SNOWBALL,64));
                p.sendMessage("§6[PaintballAdvanced] §aCompraste 64 Snowballs");
                return true;
            } else {
                p.sendMessage("§6[PaintballAdvanced] §cNo tienes coins suficientes");
                return false;
            }
        }

        if (name.contains("Paintball Gun")) {
            if (CoinsManager.getCoins(p) >= 20) {
                CoinsManager.removeCoins(p,20);
                p.getInventory().addItem(new ItemStack(Material.BLAZE_ROD));
                p.sendMessage("§6[PaintballAdvanced] §aCompraste Paintball Gun");
                return true;
            } else {
                p.sendMessage("
