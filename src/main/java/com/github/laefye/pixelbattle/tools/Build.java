package com.github.laefye.pixelbattle.tools;

import com.github.laefye.pixelbattle.Color;
import com.github.laefye.pixelbattle.Colors;
import com.github.laefye.pixelbattle.Member;
import com.github.laefye.pixelbattle.abstracts.Tool;
import com.github.laefye.pixelbattle.wrappers.ItemBuilder;
import com.github.laefye.pixelbattle.wrappers.SetInfo;

public class Build extends Tool {
    public Build(Member member) {
        super(member);
    }

    private final Color[] colors = new Color[]{
            null,
            null,
            null,
            null,
    };

    @Override
    public boolean use(int x, int y, int z, int slot) {
        var color = getColor(slot);
        var canvas = member.getPlugin().getCanvas();
        if (color == null || canvas.get(x, y, z) == color)
            return false;
        canvas.set(x, y, z, getColor(slot), new SetInfo().triggerDynmap());
        member.place(1);
        return true;
    }

    public void addColor(Color color) {
        colors[3] = colors[2];
        colors[2] = colors[1];
        colors[1] = colors[0];
        colors[0] = color;
        updateColors();
    }

    public Color getColor(int i) {
        return colors[i];
    }

    public void updateColors() {
        var inventory = member.getInventory();
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] != null) {
                inventory.setItem(i, new ItemBuilder(Colors.getMaterial(colors[i])).getItemStack());
            } else {
                inventory.setItem(i, null);
            }
        }
    }
}
