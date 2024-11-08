package com.github.laefye.pixelbattle.abstracts;

import com.github.laefye.pixelbattle.PixelBattlePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class Menu {
    protected Inventory inventory;
    protected Player player;
    protected PixelBattlePlugin plugin;

    protected void createInventory(int size, String title) {
        inventory = Bukkit.createInventory(null, size, title);
    }

    protected Menu(PixelBattlePlugin plugin) {
        this.plugin = plugin;
    }

    public void show(Player player) {
        this.player = player;
        plugin.getMenuList().add(this);
        player.openInventory(inventory);
    }

    public abstract void click(int slot, Player player);

    public Inventory getInventory() {
        return inventory;
    }
}
