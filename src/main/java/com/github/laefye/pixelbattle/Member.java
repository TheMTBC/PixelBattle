package com.github.laefye.pixelbattle;

import com.github.laefye.pixelbattle.wrappers.ItemBuilder;
import com.github.laefye.pixelbattle.wrappers.JsonIO;
import com.google.gson.JsonObject;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class Member {
    private PixelBattlePlugin plugin;
    private final UUID id;
    private final Color[] colors = new Color[]{
            null,
            null,
            null,
            null,
    };
    private int placed = 0;
    private long lastPlaceTimestamp = 0;

    public Player getPlayer() {
        return plugin.getServer().getPlayer(id);
    }

    public boolean isOnline() {
        var player = plugin.getServer().getPlayer(id);
        return player != null && player.isOnline();
    }

    private Inventory getInventory() {
        return getPlayer().getInventory();
    }

    public Member(PixelBattlePlugin plugin, Player player) {
        this.plugin = plugin;
        id = player.getUniqueId();
        updateInventory();
    }

    public void updateInventory() {
        if (!isOnline())
            return;
        var inventory = getInventory();
        inventory.clear();
        if (plugin.getCanvas().getMode() == Canvas.Mode.Build) {
            inventory.setItem(SomeConstants.PALLETE_SLOT, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("Choose Color").getItemStack());
            updateColorsInInventory();
        }
    }

    public void addColorToInventory(Color color) {
        colors[3] = colors[2];
        colors[2] = colors[1];
        colors[1] = colors[0];
        colors[0] = color;
        updateColorsInInventory();
    }

    public Color getColorFromInventory(int i) {
        return colors[i];
    }

    private void updateColorsInInventory() {
        var inventory = getInventory();
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] != null) {
                inventory.setItem(i, new ItemBuilder(Colors.getMaterial(colors[i])).getItemStack());
            } else {
                inventory.setItem(i, null);
            }
        }
    }

    public UUID getId() {
        return id;
    }

    public void place(int x, int y, int z, int slot) {
        var canvas = plugin.getCanvas();
        if (canvas.getMode() != Canvas.Mode.Build)
            return;
        var color = getColorFromInventory(slot);
        if (color == null || canvas.get(x, y, z) == color)
            return;
        if (!allowPlace()) {
            return;
        }
        canvas.set(x, y, z, getColorFromInventory(slot));
        lastPlaceTimestamp = System.currentTimeMillis();
        placed += 1;
        plugin.getTopList().sort(this);
    }

    public long getTime() {
        return Math.max(0, SomeConstants.DELAY - System.currentTimeMillis() + lastPlaceTimestamp);
    }

    public boolean allowPlace() {
        return System.currentTimeMillis() - lastPlaceTimestamp > SomeConstants.DELAY;
    }

    public JsonObject getJsonObject() {
        var jsonObject = new JsonObject();
        jsonObject.addProperty("placed", placed);
        return jsonObject;
    }

    public void save() {
        JsonIO.save(getJsonObject(), SomeConstants.MEMBERS_FOLDER + id);
    }

    public void load() {
        var jsonObject = JsonIO.load(SomeConstants.MEMBERS_FOLDER + id);
        if (jsonObject != null) {
            placed = jsonObject.get("placed").getAsInt();
        }
    }

    public int getPlaced() {
        return placed;
    }
}
