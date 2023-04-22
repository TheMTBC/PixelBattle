package com.github.laefye.pixelbattle.tools;

import com.github.laefye.pixelbattle.Member;
import com.github.laefye.pixelbattle.SomeConstants;
import com.github.laefye.pixelbattle.abstracts.CountableTool;
import com.github.laefye.pixelbattle.playerthings.TimeManipulator;
import com.github.laefye.pixelbattle.wrappers.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Booster extends CountableTool {

    public Booster(Member member) {
        super(member, SomeConstants.MAX_BONUS_IN_STACK, SomeConstants.BOOSTER_SLOT);
    }

    private final TimeManipulator time = new TimeManipulator();

    @Override
    protected ItemStack getIcon() {
        return new ItemBuilder(Material.SUGAR)
                .setDisplayName(member.getPlugin().getLangConfig().getString("tool-booster"))
                .setLore(member.getPlugin().getLangConfig().getString("tool-booster-lore"))
                .getItemStack();
    }

    public long getReloadTime() {
        if (!time.allow()) {
            return SomeConstants.DELAY - 7000;
        }
        return SomeConstants.DELAY;
    }

    @Override
    public boolean useWhenAvailable(int x, int y, int z, int slot) {
        if (!time.allow()) {
            return false;
        }
        subtract();
        updateInventory();
        time.setDelay(SomeConstants.BONUS_TIME);
        return true;
    }

    public TimeManipulator getTimeManipulator() {
        return time;
    }
}
