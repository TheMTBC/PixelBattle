package com.github.laefye.pixelbattle.tools;

import com.github.laefye.pixelbattle.Member;
import com.github.laefye.pixelbattle.SomeConstants;
import com.github.laefye.pixelbattle.abstracts.Tool;
import com.github.laefye.pixelbattle.wrappers.ItemBuilder;
import com.github.laefye.pixelbattle.wrappers.SetBlockInfo;
import org.bukkit.Material;

public class Build extends Tool {
    public Build(Member member) {
        super(member);
    }

    private final Material[] materials = new Material[]{
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
        canvas.set(x, y, z, getColor(slot), new SetBlockInfo().triggerDynmap());
        member.getLuck().add();
        member.place(1);
        member.getTimeManipulator().setDelay(SomeConstants.DELAY);
        return true;
    }

    public void addMaterial(Material material) {
        materials[3] = materials[2];
        materials[2] = materials[1];
        materials[1] = materials[0];
        materials[0] = material;
        updateColors();
    }

    public Material getColor(int i) {
        return materials[i];
    }

    public void updateColors() {
        var inventory = member.getInventory();
        for (int i = 0; i < materials.length; i++) {
            if (materials[i] != null) {
                inventory.setItem(i, new ItemBuilder(materials[i]).getItemStack());
            } else {
                inventory.setItem(i, null);
            }
        }
    }
}
