package com.github.laefye.pixelbattle.menus;

import com.github.laefye.pixelbattle.PixelBattlePlugin;
import com.github.laefye.pixelbattle.SomeConstants;
import com.github.laefye.pixelbattle.abstracts.Menu;
import com.github.laefye.pixelbattle.abstracts.PaletteCategory;
import com.github.laefye.pixelbattle.wrappers.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class PaletteCategoryMenu extends Menu {
    private Consumer<Material> consumer;
    private PaletteCategory category;

    public PaletteCategoryMenu(PixelBattlePlugin plugin, Consumer<Material> consumer, PaletteCategory category) {
        super(plugin);
        this.consumer = consumer;
        this.category = category;
        createInventory(9 * 6, plugin.getLangConfig().getString("tool-palette"));
        for (int i = 0; i < category.getMaterials().size(); i++) {
            inventory.setItem(i, new ItemBuilder(category.getMaterials().get(i)).getItemStack());
        }
    }

    @Override
    public void click(int slot, Player player) {
        if (slot < category.getMaterials().size()) {
            player.closeInventory();
            consumer.accept(category.getMaterials().get(slot));
        }
    }
}
