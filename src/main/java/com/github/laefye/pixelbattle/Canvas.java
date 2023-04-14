package com.github.laefye.pixelbattle;

import com.github.laefye.pixelbattle.async.AsyncBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.dynmap.DynmapAPI;

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
        var data = Colors.getMaterial(Color.WHITE).createBlockData();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                asyncBuilder.put(beginX + x, beginY, beginZ + y, world, data);
            }
        }
        asyncBuilder.run();
    }

    public void set(int x, int y, Color color) {
        world.setBlockData(beginX + x, beginY,beginZ + y, Material.AIR.createBlockData());
        world.spawnFallingBlock(new Location(world, beginX + x + 0.5, beginY + 1, beginZ + y + 0.5), Colors.getMaterial(color).createBlockData());
        plugin.getDynmapModuleAPI().render(world, beginX + x, beginY,beginZ + y);
    }

    private int getPosition(int x, int y) {
        return y * width + x;
    }

    public void set(int x, int y, int z, Color color) {
        if (x - beginX < 0 || x - beginX >= width)
            return;
        if (y != beginY)
            return;
        if (z - beginZ < 0 || z - beginZ >= height)
            return;
        set(x - beginX, z - beginZ, color);
    }

    public Color get(int x, int y) {
        return Colors.getColor(world.getBlockAt(beginX + x, beginY, beginZ + y).getType());
    }

    public Color get(int x, int y, int z) {
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

    public World getWorld() {
        return world;
    }
}
