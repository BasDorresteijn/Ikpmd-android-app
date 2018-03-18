package com.example.bas_d.simpledungeon;

import com.example.bas_d.simpledungeon.model.creatures.Player;

public class ScoreCalc {

    private final int healthScore = 20;
    private long startTime;
    private final long oneMinute = 60000;

    public ScoreCalc() {
        startTime = System.currentTimeMillis();
    }

    public int calcScore(Player player) {
        int score = player.getScore() + player.getHealth() * healthScore;
        if(player.getHealth() > 0) {
            long timeNow = System.currentTimeMillis();
            score += getPlaytimeScore(timeNow - startTime);
        }
        return score;
    }

    private int getPlaytimeScore(long playtime) {
        if(playtime < oneMinute) {
            return 1000;
        } else if(playtime < oneMinute * 2) {
            return 700;
        } else if(playtime < oneMinute * 4) {
            return 300;
        } else if(playtime < oneMinute * 10) {
            return 100;
        } else {
            return 0;
        }
    }
}
