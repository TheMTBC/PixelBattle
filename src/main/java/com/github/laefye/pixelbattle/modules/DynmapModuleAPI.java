package com.github.laefye.pixelbattle.modules;

import com.github.laefye.pixelbattle.PixelBattlePlugin;
import org.bukkit.World;
import org.dynmap.DynmapCommonAPI;
import org.dynmap.DynmapCommonAPIListener;

public class DynmapModuleAPI extends DynmapCommonAPIListener {
    private PixelBattlePlugin plugin;
    private DynmapCommonAPI api;

    public DynmapModuleAPI(PixelBattlePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void apiEnabled(DynmapCommonAPI dynmapCommonAPI) {
        api = dynmapCommonAPI;
    }

    public void render(World world, int x, int y, int z) {
        if (api != null) {
            api.triggerRenderOfBlock(world.getName(), x, y, z);
        }
    }

    public void renderCuboid(World world, int bx, int by, int bz, int ex, int ey, int ez) {
        if (api != null) {
            api.triggerRenderOfVolume(world.getName(), bx, by, bz, ex, ey, ez);
        }
    }
}
