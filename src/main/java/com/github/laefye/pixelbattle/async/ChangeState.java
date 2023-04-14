package com.github.laefye.pixelbattle.async;

import org.bukkit.World;
import org.bukkit.block.data.BlockData;

public record ChangeState(int x, int y, int z, BlockData data, World world) {
}
