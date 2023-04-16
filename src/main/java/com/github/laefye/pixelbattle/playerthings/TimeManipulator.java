package com.github.laefye.pixelbattle.playerthings;

import com.github.laefye.pixelbattle.SomeConstants;

public class TimeManipulator {
    private long lastUseTimestamp = 0;
    private long delay = 0;

    public boolean allow() {
        return System.currentTimeMillis() - lastUseTimestamp > delay;
    }

    public long getTime() {
        return Math.max(0, delay - System.currentTimeMillis() + lastUseTimestamp);
    }

    public void setDelay(long delay) {
        this.delay = delay;
        lastUseTimestamp = System.currentTimeMillis();
    }
}
