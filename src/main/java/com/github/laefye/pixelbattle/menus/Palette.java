package com.github.laefye.pixelbattle.menus;

import com.github.laefye.pixelbattle.Color;
import com.github.laefye.pixelbattle.Colors;
import com.github.laefye.pixelbattle.wrappers.ItemBuilder;
import com.github.laefye.pixelbattle.PixelBattlePlugin;
import com.github.laefye.pixelbattle.abstracts.Menu;
import org.bukkit.entity.Player;

public class Palette extends Menu {
    public Palette(PixelBattlePlugin plugin) {
        super(plugin);
        createInventory(9 * 3, plugin.getLangConfig().getString("tool-palette"));
        for (int i = 0; i < 16; i++) {
            inventory.setItem(i, new ItemBuilder(Colors.getMaterial(Color.values()[i])).getItemStack());
        }
    }

    @Override
    public void click(int slot, Player player) {
        if (slot < 16) {
            player.closeInventory();
            plugin.getMember(player).addColorToInventory(Color.values()[slot]);
        }
    }
}
