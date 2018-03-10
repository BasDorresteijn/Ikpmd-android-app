package com.example.bas_d.simpledungeon.model.creatures;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.services.ImageService;

public abstract class Creature {

    private float speed = FixedValues.SPEED;

    private int maxHealth = FixedValues.HEALTH;
    private int health;

    private Bitmap image;
    private float posX;
    private float posY;
    private int attack = FixedValues.ATTACK;

    protected long immumeSince = 0;
    protected long immumeTime = FixedValues.DEFAULTIMMUME;

    public Creature() {

    }

    public Creature(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        this.image = ImageService.defaultImage;
        this.health = maxHealth;
    }

    public Creature(float posX, float posY, float speed, int health, Bitmap resImage) {
        this.speed = speed;
        this.maxHealth = health;
        this.health = maxHealth;
        this.posX = posX;
        this.posY = posY;
        this.image = resImage;
    }

    public Creature(float posX, float posY, Bitmap resImage) {
        this.posX = posX;
        this.posY = posY;
        this.image = resImage;
        this.health = maxHealth;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Bitmap getResImage() {
        return image;
    }

    public void setResImage(Bitmap image) {
        this.image = image;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public Rect getBounds() {
        return new Rect((int) this.getPosX(), (int) this.getPosY(),
                (int) this.getPosX() + FixedValues.WIDTH, (int) this.getPosY() + FixedValues.HEIGHT);
    }

    public long getImmumeSince() {
        return immumeSince;
    }

    public void setImmumeSince(long immumeSince) {
        this.immumeSince = immumeSince;
    }

    public long getImmumeTime() {
        return immumeTime;
    }

    public void setImmumeTime(long immumeTime) {
        this.immumeTime = immumeTime;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
