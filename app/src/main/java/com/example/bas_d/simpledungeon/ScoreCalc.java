package com.example.bas_d.simpledungeon;

import android.content.SharedPreferences;

import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.model.creatures.Player;
import com.example.bas_d.simpledungeon.views.activity.GameActivity;

public class ScoreCalc {

    private final int healthScore = 20;
    private long startTime;
    private final long oneMinute = 60000;
    private GameActivity gameActivity;
    private final String damageDealt = "damagedealt", damageTaken = "damagetaken";

    public ScoreCalc(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
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

    public void saveDamageData(Player player) {
        SharedPreferences damageData = gameActivity.getSharedPreferences(FixedValues.DAMAGE, 0);
        SharedPreferences.Editor editor = damageData.edit();
        int oldDealt = damageData.getInt(damageDealt, 0);
        int oldTaken = damageData.getInt(damageDealt, 0);
        editor.putInt(damageDealt, player.getDamageDealt() + oldDealt);
        editor.putInt(damageTaken, player.getDamageTaken() + oldTaken);
        editor.apply();
    }
}
