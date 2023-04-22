package com.github.laefye.pixelbattle.playerthings;

import com.github.laefye.pixelbattle.Member;
import com.github.laefye.pixelbattle.SomeConstants;
import com.github.laefye.pixelbattle.abstracts.CountableTool;

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
        var random = new Random();
        if (member.getPlayer().hasPermission("pixelbattle.vip.1")) {
            var tool = getRandomTool(random);
            if (random.nextInt(100) > 60) {
                tool.setAmount(tool.getAmount() + 1);
                tool.updateInventory();
                count = 0;
            }
        } else {
            var tool = getRandomTool(random);
            if (random.nextInt(100) > 90) {
                tool.setAmount(tool.getAmount() + 1);
                tool.updateInventory();
                count = 0;
            }
        }
    }

    private CountableTool getRandomTool(Random random) {
        if (random.nextInt(100) > 70) {
            return member.getBooster();
        } else {
            return member.getBomb();
        }
    }
}
