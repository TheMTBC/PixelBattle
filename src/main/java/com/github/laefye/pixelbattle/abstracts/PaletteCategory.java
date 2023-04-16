package com.github.laefye.pixelbattle.abstracts;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public abstract class PaletteCategory {
    private ArrayList<Material> materials = new ArrayList<>();

    public abstract ItemStack getIcon();
    public abstract String getName();

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    protected void addMaterial(Material material) {
        materials.add(material);
    }
}
