package com.github.laefye.pixelbattle.categories;

import com.github.laefye.pixelbattle.abstracts.PaletteCategory;
import com.github.laefye.pixelbattle.wrappers.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TerracottaCategory extends PaletteCategory {
    public TerracottaCategory() {
        addMaterial(Material.TERRACOTTA);
        addMaterial(Material.WHITE_TERRACOTTA);
        addMaterial(Material.ORANGE_TERRACOTTA);
        addMaterial(Material.MAGENTA_TERRACOTTA);
        addMaterial(Material.LIGHT_BLUE_TERRACOTTA);
        addMaterial(Material.YELLOW_TERRACOTTA);
        addMaterial(Material.LIME_TERRACOTTA);
        addMaterial(Material.PINK_TERRACOTTA);
        addMaterial(Material.GRAY_TERRACOTTA);
        addMaterial(Material.LIGHT_GRAY_TERRACOTTA);
        addMaterial(Material.CYAN_TERRACOTTA);
        addMaterial(Material.PURPLE_TERRACOTTA);
        addMaterial(Material.BLUE_TERRACOTTA);
        addMaterial(Material.BROWN_TERRACOTTA);
        addMaterial(Material.GREEN_TERRACOTTA);
        addMaterial(Material.RED_TERRACOTTA);
        addMaterial(Material.BLACK_TERRACOTTA);

        addMaterial(Material.WHITE_GLAZED_TERRACOTTA);
        addMaterial(Material.ORANGE_GLAZED_TERRACOTTA);
        addMaterial(Material.MAGENTA_GLAZED_TERRACOTTA);
        addMaterial(Material.LIGHT_BLUE_GLAZED_TERRACOTTA);
        addMaterial(Material.YELLOW_GLAZED_TERRACOTTA);
        addMaterial(Material.LIME_GLAZED_TERRACOTTA);
        addMaterial(Material.PINK_GLAZED_TERRACOTTA);
        addMaterial(Material.GRAY_GLAZED_TERRACOTTA);
        addMaterial(Material.LIGHT_GRAY_GLAZED_TERRACOTTA);
        addMaterial(Material.CYAN_GLAZED_TERRACOTTA);
        addMaterial(Material.PURPLE_GLAZED_TERRACOTTA);
        addMaterial(Material.BLUE_GLAZED_TERRACOTTA);
        addMaterial(Material.BROWN_GLAZED_TERRACOTTA);
        addMaterial(Material.GREEN_GLAZED_TERRACOTTA);
        addMaterial(Material.RED_GLAZED_TERRACOTTA);
        addMaterial(Material.BLACK_GLAZED_TERRACOTTA);

    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.TERRACOTTA).getItemStack();
    }

    @Override
    public String getName() {
        return "terracotta";
    }
}
