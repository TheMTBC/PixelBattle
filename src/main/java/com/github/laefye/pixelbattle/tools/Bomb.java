package com.github.laefye.pixelbattle.tools;

import com.github.laefye.pixelbattle.Canvas;
import com.github.laefye.pixelbattle.Color;
import com.github.laefye.pixelbattle.Member;
import com.github.laefye.pixelbattle.SomeConstants;
import com.github.laefye.pixelbattle.abstracts.Tool;
import com.github.laefye.pixelbattle.wrappers.ItemBuilder;
import com.github.laefye.pixelbattle.wrappers.SetInfo;
import org.bukkit.Material;

public class Bomb extends Tool {
    private int count = 0;

    public Bomb(Member member) {
        super(member);
    }

    public void setCount(int count) {
        this.count = Math.min(count, 50);
    }

    public void updateInventory() {
        if (count > 0) {
            member.getInventory().setItem(SomeConstants.BOMB_SLOT, new ItemBuilder(Material.FIRE_CHARGE)
                            .setDisplayName(member.getPlugin().getLangConfig().getString("tool-bomb"))
                            .setAmount(count)
                            .setLore(member.getPlugin().getLangConfig().getString("tool-bomb-lore"))
                            .getItemStack()
            );
        } else {
            member.getInventory().clear(SomeConstants.BOMB_SLOT);
        }
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean use(int x, int y, int z, int slot) {
        var canvas = member.getPlugin().getCanvas();
        var color = member.getBuild().getColor(0);
        if (color == null || count <= 0) {
            return false;
        }
        var info = new SetInfo();
        var result = 0;
        result += set(canvas, x, y, z-2, color, info.copy().delay(6).triggerDynmap());

        result += set(canvas, x-1, y, z-1, color, info.copy().delay(4));
        result += set(canvas, x, y, z-1, color, info.copy().delay(3));
        result += set(canvas, x+1, y, z-1, color, info.copy().delay(4));

        result += set(canvas, x-2, y, z, color, info.copy().delay(6).triggerDynmap());
        result += set(canvas, x-1, y, z, color, info.copy().delay(3));
        result += set(canvas, x, y, z, color, new SetInfo().triggerDynmap());
        result += set(canvas, x+1, y, z, color, info.copy().delay(3));
        result += set(canvas, x+2, y, z, color, info.copy().delay(6).triggerDynmap());

        result += set(canvas, x-1, y, z+1, color, info.copy().delay(4));
        result += set(canvas, x, y, z+1, color, info.copy().delay(3));
        result += set(canvas, x+1, y, z+1, color, info.copy().delay(4));

        result += set(canvas, x, y, z+2, color, info.copy().delay(6).triggerDynmap());

        if (result != 0) {
            count--;
        }
        updateInventory();
        member.place(result);
        return result != 0;
    }

    public int set(Canvas canvas, int x, int y, int z, Color color, SetInfo base) {
        if (canvas.get(x, y, z) == color) {
            return 0;
        }
        canvas.set(x, y, z, color, base);
        return 1;
    }
}
