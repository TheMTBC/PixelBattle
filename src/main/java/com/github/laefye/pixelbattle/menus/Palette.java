package com.github.laefye.pixelbattle.menus;

import com.github.laefye.pixelbattle.SomeConstants;
import com.github.laefye.pixelbattle.abstracts.PaletteCategory;
import com.github.laefye.pixelbattle.categories.ConcreteCategory;
import com.github.laefye.pixelbattle.categories.GlassCategory;
import com.github.laefye.pixelbattle.categories.TerracottaCategory;
import com.github.laefye.pixelbattle.categories.WoolCategory;
import com.github.laefye.pixelbattle.wrappers.ItemBuilder;
import com.github.laefye.pixelbattle.PixelBattlePlugin;
import com.github.laefye.pixelbattle.abstracts.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.function.Consumer;

public class Palette extends Menu {
    private final Consumer<Material> consumer;
    private final HashMap<Integer, PaletteCategory> categoryHashMap = new HashMap<>();

    public Palette(PixelBattlePlugin plugin, Consumer<Material> consumer) {
        super(plugin);
        this.consumer = consumer;
        createInventory(9 * 6, plugin.getLangConfig().getString("tool-palette"));

        categoryHashMap.put(0, new ConcreteCategory());
        categoryHashMap.put(1, new WoolCategory());
        categoryHashMap.put(2, new GlassCategory());
        categoryHashMap.put(3, new TerracottaCategory());

        for (var v : categoryHashMap.keySet()) {
            inventory.setItem(v, categoryHashMap.get(v).getIcon());
        }
    }

    @Override
    public void click(int slot, Player player) {
        if (categoryHashMap.containsKey(slot)) {
            player.closeInventory();
            new PaletteCategoryMenu(plugin, consumer, categoryHashMap.get(slot)).show(player);
        }
    }
}
