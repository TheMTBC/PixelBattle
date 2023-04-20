package com.github.laefye.pixelbattle;

import com.github.laefye.pixelbattle.abstracts.Tool;
import com.github.laefye.pixelbattle.playerthings.Luck;
import com.github.laefye.pixelbattle.playerthings.TimeManipulator;
import com.github.laefye.pixelbattle.tools.Bomb;
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
    private final Build build = new Build(this);
    private final Bomb bomb = new Bomb(this);
    private final Luck luck = new Luck(this);
    private final TimeManipulator timeManipulator = new TimeManipulator();

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
            bomb.updateInventory();
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
        if (!timeManipulator.allow()) {
            return;
        }
        if (!tool.use(x, y, z, slot)) {
            return;
        }
        plugin.getTopList().sort(this);
    }

    public JsonObject getJsonObject() {
        var jsonObject = new JsonObject();
        jsonObject.addProperty("placed", placed);
        jsonObject.addProperty("bombs", bomb.getAmount());
        return jsonObject;
    }

    public void save() {
        JsonIO.save(getJsonObject(), SomeConstants.MEMBERS_FOLDER + id);
    }

    public void load() {
        var jsonObject = JsonIO.load(SomeConstants.MEMBERS_FOLDER + id);
        if (jsonObject != null) {
            placed = jsonObject.get("placed") == null ? 0 : jsonObject.get("placed").getAsInt();
            bomb.setAmount(jsonObject.get("bombs") == null ? 0 : jsonObject.get("bombs").getAsInt());
        }
        updateInventory();
    }

    public int getPlaced() {
        return placed;
    }

    public void place(int count) {
        placed += count;
    }

    public PixelBattlePlugin getPlugin() {
        return plugin;
    }

    public Build getBuild() {
        return build;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public Luck getLuck() {
        return luck;
    }

    public TimeManipulator getTimeManipulator() {
        return timeManipulator;
    }
}
