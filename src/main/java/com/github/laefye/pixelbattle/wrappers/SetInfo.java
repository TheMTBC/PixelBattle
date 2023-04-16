package com.github.laefye.pixelbattle.wrappers;

public class SetInfo {
    private boolean toTriggerDynmap = false;
    private long delay = 0;

    public SetInfo triggerDynmap() {
        this.toTriggerDynmap = true;
        return this;
    }

    public boolean isTriggerDynmap() {
        return toTriggerDynmap;
    }

    public SetInfo delay(long delay) {
        this.delay = delay;
        return this;
    }

    public SetInfo copy() {
        var clone = new SetInfo();
        clone.delay = delay;
        clone.toTriggerDynmap = toTriggerDynmap;
        return clone;
    }

    public long getDelay() {
        return delay;
    }
}
