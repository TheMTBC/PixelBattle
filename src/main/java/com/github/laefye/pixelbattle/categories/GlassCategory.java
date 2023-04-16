package com.github.laefye.pixelbattle.categories;

import com.github.laefye.pixelbattle.abstracts.PaletteCategory;
import com.github.laefye.pixelbattle.wrappers.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GlassCategory extends PaletteCategory {
    public GlassCategory() {
        addMaterial(Material.GLASS);
        addMaterial(Material.WHITE_STAINED_GLASS);
        addMaterial(Material.ORANGE_STAINED_GLASS);
        addMaterial(Material.MAGENTA_STAINED_GLASS);
        addMaterial(Material.LIGHT_BLUE_STAINED_GLASS);
        addMaterial(Material.YELLOW_STAINED_GLASS);
        addMaterial(Material.LIME_STAINED_GLASS);
        addMaterial(Material.PINK_STAINED_GLASS);
        addMaterial(Material.GRAY_STAINED_GLASS);
        addMaterial(Material.LIGHT_GRAY_STAINED_GLASS);
        addMaterial(Material.CYAN_STAINED_GLASS);
        addMaterial(Material.PURPLE_STAINED_GLASS);
        addMaterial(Material.BLUE_STAINED_GLASS);
        addMaterial(Material.BROWN_STAINED_GLASS);
        addMaterial(Material.GREEN_STAINED_GLASS);
        addMaterial(Material.RED_STAINED_GLASS);
        addMaterial(Material.BLACK_STAINED_GLASS);

    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.GLASS).getItemStack();
    }

    @Override
    public String getName() {
        return "glass";
    }
}
