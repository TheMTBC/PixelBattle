package com.github.laefye.pixelbattle.abstracts;

import com.github.laefye.pixelbattle.PixelBattlePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class Menu {
    protected Inventory inventory;
    protected Player player;

    protected void createInventory(int size, String title) {
        inventory = Bukkit.createInventory(null, size, title);
    }

    public void show(Player player) {
        this.player = player;
        PixelBattlePlugin.getInstance().getMenuList().add(this);
        player.openInventory(inventory);
    }

    public abstract void click(int slot, Player player);

    public Inventory getInventory() {
        return inventory;
    }
}
