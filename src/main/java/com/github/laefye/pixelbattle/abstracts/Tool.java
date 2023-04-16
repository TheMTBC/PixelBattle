package com.github.laefye.pixelbattle.abstracts;

import com.github.laefye.pixelbattle.Member;
import org.bukkit.plugin.Plugin;

public abstract class Tool {
    protected Member member;

    public Tool(Member member) {
        this.member = member;
    }

    public abstract boolean use(int x, int y, int z, int slot);
}
