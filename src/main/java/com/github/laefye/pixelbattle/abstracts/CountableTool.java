package com.github.laefye.pixelbattle.abstracts;

import com.github.laefye.pixelbattle.Member;
import org.bukkit.inventory.ItemStack;

public abstract class CountableTool extends Tool {
    private int amount = 0;
    private int maxInStack = 0;
    private int slot = 0;

    public void setAmount(int amount) {
        this.amount = Math.min(amount, maxInStack);
    }

    public int getAmount() {
        return amount;
    }

    protected void subtract() {
        this.amount -= 1;
    }

    protected abstract ItemStack getIcon();

    public void updateInventory() {
        if (amount > 0) {
            var item = getIcon();
            item.setAmount(amount);
            member.getInventory().setItem(slot, item);
        } else {
            member.getInventory().clear(slot);
        }
    }

    public CountableTool(Member member, int maxInStack, int slot) {
        super(member);
        this.maxInStack = maxInStack;
        this.slot = slot;
    }

    @Override
    public boolean use(int x, int y, int z, int slot) {
        if (amount <= 0) {
            return false;
        }
        return useWhenAvailable(x, y, z, slot);
    }

    public abstract boolean useWhenAvailable(int x, int y, int z, int slot);
}
