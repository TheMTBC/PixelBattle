package com.github.laefye.pixelbattle.wrappers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setDisplayName(String name) {
        if (itemMeta != null) {
            itemMeta.setDisplayName(name);
        }
        return this;
    }

    public ItemStack getItemStack() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }
}
