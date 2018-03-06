package com.example.bas_d.simpledungeon.model.entities;

import android.graphics.Rect;

import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.services.ImageService;

public class Sword extends Entity{

    private int attack = 5;
    private long cooldown = 1200;
    private long useTime = 600;
    private long lastUsed = 0;

    public Sword() {
        super(ImageService.basicSwordR);
    }

    public Rect getXBounds(int posX, int posY) {
        return new Rect(posX , posY + FixedValues.WIDTH/3*2,
                posX + FixedValues.WIDTH, posY + FixedValues.HEIGHT - FixedValues.WIDTH/3*2);
    }

    public Rect getYBounds(int posX, int posY) {
        return new Rect(posX + FixedValues.WIDTH/3*2, posY,
                posX + FixedValues.WIDTH - FixedValues.WIDTH/3*2, posY + FixedValues.HEIGHT);
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public long getCooldown() {
        return cooldown;
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }

    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }

    public long getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(long lastUsed) {
        this.lastUsed = lastUsed;
    }
}
