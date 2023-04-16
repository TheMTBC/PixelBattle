package com.github.laefye.pixelbattle;

import com.github.laefye.pixelbattle.async.AsyncBuilder;
import com.github.laefye.pixelbattle.wrappers.SetBlockInfo;
import org.bukkit.Material;
import org.bukkit.World;

public class Canvas {
    private final int width;
    private final int height;
    private final int beginX;
    private final int beginY;
    private final int beginZ;
    private final World world;
    private final PixelBattlePlugin plugin;
    public enum Mode {
        Build,
        View,
    }

    private Mode mode = Mode.Build;

    public Canvas(PixelBattlePlugin plugin, int width, int height, World world, int beginX, int beginY, int beginZ) {
        this.plugin = plugin;
        this.width = width;
        this.height = height;
        this.beginX = beginX;
        this.beginY = beginY;
        this.beginZ = beginZ;
        this.world = world;
    }

    public void fill() {
        var asyncBuilder = new AsyncBuilder(plugin);
        var data = Material.WHITE_CONCRETE.createBlockData();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                asyncBuilder.put(beginX + x, beginY, beginZ + y, world, data);
            }
        }
        asyncBuilder.run();
    }

    public void set(int x, int y, Material material, SetBlockInfo info) {
        if (info.getDelay() == 0) {
            world.setBlockData(beginX + x, beginY,beginZ + y, material.createBlockData());
            if (info.isTriggerDynmap() && plugin.getDynmapModuleAPI() != null) {
                plugin.getDynmapModuleAPI().render(world, beginX + x, beginY,beginZ + y);
            }
        } else {
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> set(x, y, material, info.copy().delay(0)), info.getDelay());
        }
    }

    public void set(int x, int y, int z, Material material, SetBlockInfo info) {
        if (x - beginX < 0 || x - beginX >= width)
            return;
        if (y != beginY)
            return;
        if (z - beginZ < 0 || z - beginZ >= height)
            return;
        set(x - beginX, z - beginZ, material, info);
    }

    public Material get(int x, int y) {
        return world.getBlockAt(beginX + x, beginY, beginZ + y).getType();
    }

    public Material get(int x, int y, int z) {
        if (x - beginX < 0 || x - beginX >= width)
            return null;
        if (y != beginY)
            return null;
        if (z - beginZ < 0 || z - beginZ >= height)
            return null;
        return get(x - beginX, z - beginZ);
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        for (var member : plugin.getMembers() ) {
            member.updateInventory();
        }
    }
}
