package com.github.laefye.pixelbattle;

import com.github.laefye.pixelbattle.abstracts.Tool;
import com.github.laefye.pixelbattle.tools.Build;
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
    private int placed = 0;
    private long lastUseTimestamp = 0;
    private final Build build = new Build(this);

    public Player getPlayer() {
        return plugin.getServer().getPlayer(id);
    }

    public boolean isOnline() {
        var player = plugin.getServer().getPlayer(id);
        return player != null && player.isOnline();
    }

    public Inventory getInventory() {
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
            inventory.setItem(SomeConstants.PALLETE_SLOT, new ItemBuilder(Material.DIAMOND_SWORD)
                    .setDisplayName(plugin.getLangConfig().getString("tool-palette"))
                    .getItemStack());
            build.updateColors();
        }
    }

    public UUID getId() {
        return id;
    }

    public void use(Tool tool, int x, int y, int z, int slot) {
        var canvas = plugin.getCanvas();
        if (canvas.getMode() != Canvas.Mode.Build)
            return;
        if (!allowUse()) {
            return;
        }
        tool.use(x, y, z, slot);
        lastUseTimestamp = System.currentTimeMillis();
        plugin.getTopList().sort(this);
    }

    public long getTime() {
        return Math.max(0, SomeConstants.DELAY - System.currentTimeMillis() + lastUseTimestamp);
    }

    public boolean allowUse() {
        return System.currentTimeMillis() - lastUseTimestamp > SomeConstants.DELAY;
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

    public void place() {
        placed++;
    }

    public PixelBattlePlugin getPlugin() {
        return plugin;
    }

    public Build getBuild() {
        return build;
    }
}
