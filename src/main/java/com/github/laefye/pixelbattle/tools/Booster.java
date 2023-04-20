package com.github.laefye.pixelbattle.tools;

import com.github.laefye.pixelbattle.Member;
import com.github.laefye.pixelbattle.abstracts.Tool;

public class Booster extends Tool {


    public Booster(Member member) {
        super(member);
    }

    @Override
    public boolean use(int x, int y, int z, int slot) {
        return false;
    }
}
