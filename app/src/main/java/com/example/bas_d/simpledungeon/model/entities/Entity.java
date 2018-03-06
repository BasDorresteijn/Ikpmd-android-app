package com.example.bas_d.simpledungeon.model.entities;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.bas_d.simpledungeon.model.FixedValues;

public abstract class Entity {

    private Bitmap image;
    private float posX;
    private float posY;

    public Entity(Bitmap image) {
        this.image = image;
    }

    public Entity(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Entity(Bitmap image, float posX, float posY) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
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


}
