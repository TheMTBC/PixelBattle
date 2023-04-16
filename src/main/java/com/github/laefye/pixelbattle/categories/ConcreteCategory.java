package com.github.laefye.pixelbattle.categories;

import com.github.laefye.pixelbattle.abstracts.PaletteCategory;
import com.github.laefye.pixelbattle.wrappers.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ConcreteCategory extends PaletteCategory {
    public ConcreteCategory() {
        addMaterial(Material.WHITE_CONCRETE);
        addMaterial(Material.ORANGE_CONCRETE);
        addMaterial(Material.MAGENTA_CONCRETE);
        addMaterial(Material.LIGHT_BLUE_CONCRETE);
        addMaterial(Material.YELLOW_CONCRETE);
        addMaterial(Material.LIME_CONCRETE);
        addMaterial(Material.PINK_CONCRETE);
        addMaterial(Material.GRAY_CONCRETE);
        addMaterial(Material.LIGHT_GRAY_CONCRETE);
        addMaterial(Material.CYAN_CONCRETE);
        addMaterial(Material.PURPLE_CONCRETE);
        addMaterial(Material.BLUE_CONCRETE);
        addMaterial(Material.BROWN_CONCRETE);
        addMaterial(Material.GREEN_CONCRETE);
        addMaterial(Material.RED_CONCRETE);
        addMaterial(Material.BLACK_CONCRETE);

        addMaterial(Material.WHITE_CONCRETE_POWDER);
        addMaterial(Material.ORANGE_CONCRETE_POWDER);
        addMaterial(Material.MAGENTA_CONCRETE_POWDER);
        addMaterial(Material.LIGHT_BLUE_CONCRETE_POWDER);
        addMaterial(Material.YELLOW_CONCRETE_POWDER);
        addMaterial(Material.LIME_CONCRETE_POWDER);
        addMaterial(Material.PINK_CONCRETE_POWDER);
        addMaterial(Material.GRAY_CONCRETE_POWDER);
        addMaterial(Material.LIGHT_GRAY_CONCRETE_POWDER);
        addMaterial(Material.CYAN_CONCRETE_POWDER);
        addMaterial(Material.PURPLE_CONCRETE_POWDER);
        addMaterial(Material.BLUE_CONCRETE_POWDER);
        addMaterial(Material.BROWN_CONCRETE_POWDER);
        addMaterial(Material.GREEN_CONCRETE_POWDER);
        addMaterial(Material.RED_CONCRETE_POWDER);
        addMaterial(Material.BLACK_CONCRETE_POWDER);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.WHITE_CONCRETE).getItemStack();
    }

    @Override
    public String getName() {
        return "concrete";
    }
}
