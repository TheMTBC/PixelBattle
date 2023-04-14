package com.github.laefye.pixelbattle.async;

import com.github.laefye.pixelbattle.PixelBattlePlugin;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.LinkedBlockingQueue;

public class AsyncBuilder {
    private PixelBattlePlugin plugin;
    private LinkedBlockingQueue<ChangeState> states = new LinkedBlockingQueue<>();
    private BukkitTask task;

    public AsyncBuilder(PixelBattlePlugin plugin) {
        this.plugin = plugin;
    }

    public void run() {
        task = plugin.getServer().getScheduler().runTaskTimer(plugin, this::tick, 0L, 20L);
    }

    private void tick() {
        for (int i = 0; i < Math.min(5000, states.size()); i++) {
            try {
                var state = states.take();
                state.world().setBlockData(state.x(), state.y(), state.z(), state.data());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        plugin.getLogger().info("Left: " + states.size());
        if (states.isEmpty()) {
            plugin.getLogger().info("Async builder finished");
            task.cancel();
        }
    }

    public void put(int x, int y, int z, World world, BlockData data) {
        try {
            states.put(new ChangeState(x, y, z, data, world));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
