package com.example.bas_d.simpledungeon.model.creatures;

import android.graphics.Bitmap;

import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.services.ImageService;

public abstract class Creature {

    private int width = FixedValues.WIDTH;
    private int height = FixedValues.HEIGHT;

    private float speed = FixedValues.SPEED;

    private int health = FixedValues.HEALTH;

    private Bitmap image;
    private float posX;
    private float posY;

    public Creature() {

    }

    public Creature(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        this.image = ImageService.defaultImage;
    }

    public Creature(float posX, float posY, float speed, int health, Bitmap resImage) {
        this.speed = speed;
        this.health = health;
        this.posX = posX;
        this.posY = posY;
        this.image = resImage;
    }

    public Creature(float posX, float posY, int width, int height, float speed, int health, Bitmap resImage) {
        this.speed = speed;
        this.health = health;
        this.posX = posX;
        this.posY = posY;
        this.image = resImage;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
}
