package com.github.laefye.pixelbattle.playerthings;

import com.github.laefye.pixelbattle.Member;
import com.github.laefye.pixelbattle.SomeConstants;

import java.util.Random;

public class Luck {
    private int count = 0;
    private final Member member;

    public Luck(Member member) {
        this.member = member;
    }

    public void add() {
        count++;
        if (count >= 10) {
            giveRandomSomething();
        }
    }

    private void giveRandomSomething() {
        if (member.getPlayer().hasPermission("pixelbattle.bonus.bomb")) {
            var bomb = member.getBomb();
            if (new Random().nextInt(100) > 60 && bomb.getAmount() < SomeConstants.MAX_BONUS_IN_STACK) {
                bomb.setAmount(bomb.getAmount() + 1);
                bomb.updateInventory();
                count = 0;
            }
        } else {
            var bomb = member.getBomb();
            if (new Random().nextInt(100) > 90 && bomb.getAmount() < SomeConstants.MAX_BONUS_IN_STACK) {
                bomb.setAmount(bomb.getAmount() + 1);
                bomb.updateInventory();
                count = 0;
            }
        }
    }
}
