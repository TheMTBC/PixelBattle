package com.github.laefye.pixelbattle.playerthings;

import com.github.laefye.pixelbattle.Member;
import com.github.laefye.pixelbattle.SomeConstants;

import java.util.Random;

public class Luck {
    private int count = 0;
    private Member member;

    public Luck(Member member) {
        this.member = member;
    }

    public void add() {
        count++;
        if (count >= 10) {
            giveRandomSomething();
            count = 0;
        }
    }

    private void giveRandomSomething() {
        if (member.getPlayer().hasPermission("pixelbattle.bonus.bomb")) {
            var bomb = member.getBomb();
            if (new Random().nextInt(100) > 60 && bomb.getCount() < SomeConstants.MAX_BONUS_IN_STACK) {
                bomb.setCount(bomb.getCount() + 1);
            }
        }
    }
}
