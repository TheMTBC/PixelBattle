package com.github.laefye.pixelbattle.menus;

import com.github.laefye.pixelbattle.SomeConstants;
import com.github.laefye.pixelbattle.wrappers.ItemBuilder;
import com.github.laefye.pixelbattle.PixelBattlePlugin;
import com.github.laefye.pixelbattle.abstracts.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class Palette extends Menu {
    private Consumer<Material> consumer;

    public Palette(PixelBattlePlugin plugin, Consumer<Material> consumer) {
        super(plugin);
        this.consumer = consumer;
        createInventory(9 * 6, plugin.getLangConfig().getString("tool-palette"));
        for (int i = 0; i < SomeConstants.PALETTE.length; i++) {
            inventory.setItem(i, new ItemBuilder(SomeConstants.PALETTE[i]).getItemStack());
        }
    }

    @Override
    public void click(int slot, Player player) {
        if (slot < SomeConstants.PALETTE.length) {
            player.closeInventory();
            consumer.accept(SomeConstants.PALETTE[slot]);
        }
    }
}
