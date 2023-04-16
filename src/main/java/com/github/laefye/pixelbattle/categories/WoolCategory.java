package com.github.laefye.pixelbattle.categories;

import com.github.laefye.pixelbattle.abstracts.PaletteCategory;
import com.github.laefye.pixelbattle.wrappers.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WoolCategory extends PaletteCategory {
    public WoolCategory() {
        addMaterial(Material.WHITE_WOOL);
        addMaterial(Material.ORANGE_WOOL);
        addMaterial(Material.MAGENTA_WOOL);
        addMaterial(Material.LIGHT_BLUE_WOOL);
        addMaterial(Material.YELLOW_WOOL);
        addMaterial(Material.LIME_WOOL);
        addMaterial(Material.PINK_WOOL);
        addMaterial(Material.GRAY_WOOL);
        addMaterial(Material.LIGHT_GRAY_WOOL);
        addMaterial(Material.CYAN_WOOL);
        addMaterial(Material.PURPLE_WOOL);
        addMaterial(Material.BLUE_WOOL);
        addMaterial(Material.BROWN_WOOL);
        addMaterial(Material.GREEN_WOOL);
        addMaterial(Material.RED_WOOL);
        addMaterial(Material.BLACK_WOOL);

    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.WHITE_WOOL).getItemStack();
    }

    @Override
    public String getName() {
        return "wool";
    }
}
