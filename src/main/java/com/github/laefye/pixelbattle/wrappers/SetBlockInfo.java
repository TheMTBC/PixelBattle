package com.github.laefye.pixelbattle.wrappers;

public class SetBlockInfo {
    private boolean toTriggerDynmap = false;
    private long delay = 0;

    public SetBlockInfo triggerDynmap() {
        this.toTriggerDynmap = true;
        return this;
    }

    public boolean isTriggerDynmap() {
        return toTriggerDynmap;
    }

    public SetBlockInfo delay(long delay) {
        this.delay = delay;
        return this;
    }

    public SetBlockInfo copy() {
        var clone = new SetBlockInfo();
        clone.delay = delay;
        clone.toTriggerDynmap = toTriggerDynmap;
        return clone;
    }

    public long getDelay() {
        return delay;
    }
}
