package com.github.laefye.pixelbattle.tools;

import com.github.laefye.pixelbattle.Canvas;
import com.github.laefye.pixelbattle.Member;
import com.github.laefye.pixelbattle.SomeConstants;
import com.github.laefye.pixelbattle.abstracts.CountableTool;
import com.github.laefye.pixelbattle.wrappers.ItemBuilder;
import com.github.laefye.pixelbattle.wrappers.SetBlockInfo;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Bomb extends CountableTool {
    public Bomb(Member member) {
        super(member, SomeConstants.MAX_BONUS_IN_STACK, SomeConstants.BOMB_SLOT);
    }

    @Override
    protected ItemStack getIcon() {
        return new ItemBuilder(Material.FIRE_CHARGE)
                .setDisplayName(member.getPlugin().getLangConfig().getString("tool-bomb"))
                .setLore(member.getPlugin().getLangConfig().getString("tool-bomb-lore"))
                .getItemStack();
    }

    @Override
    public boolean useWhenAvailable(int x, int y, int z, int slot) {
        var canvas = member.getPlugin().getCanvas();
        var color = member.getBuild().getColor(0);
        if (color == null) {
            return false;
        }
        var info = new SetBlockInfo();
        var result = 0;
        result += set(canvas, x, y, z - 2, color, info.copy().delay(6).triggerDynmap());

        result += set(canvas, x - 1, y, z - 1, color, info.copy().delay(4));
        result += set(canvas, x, y, z - 1, color, info.copy().delay(3));
        result += set(canvas, x + 1, y, z - 1, color, info.copy().delay(4));

        result += set(canvas, x - 2, y, z, color, info.copy().delay(6).triggerDynmap());
        result += set(canvas, x - 1, y, z, color, info.copy().delay(3));
        result += set(canvas, x, y, z, color, new SetBlockInfo().triggerDynmap());
        result += set(canvas, x + 1, y, z, color, info.copy().delay(3));
        result += set(canvas, x + 2, y, z, color, info.copy().delay(6).triggerDynmap());

        result += set(canvas, x - 1, y, z + 1, color, info.copy().delay(4));
        result += set(canvas, x, y, z + 1, color, info.copy().delay(3));
        result += set(canvas, x + 1, y, z + 1, color, info.copy().delay(4));

        result += set(canvas, x, y, z + 2, color, info.copy().delay(6).triggerDynmap());

        if (result == 0) {
            return false;
        }

        amount--;
        updateInventory();
        member.place(result);
        if (result < 3) {
            member.getTimeManipulator().setDelay(SomeConstants.DELAY);
        } else if (result < 6) {
            member.getTimeManipulator().setDelay(SomeConstants.DELAY * (result - 2));
        } else {
            member.getTimeManipulator().setDelay(SomeConstants.DELAY * (result - 5));
        }
        return true;
    }

    public int set(Canvas canvas, int x, int y, int z, Material material, SetBlockInfo base) {
        if (canvas.get(x, y, z) == material) {
            return 0;
        }
        canvas.set(x, y, z, material, base);
        return 1;
    }
}
