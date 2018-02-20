package com.example.bas_d.simpledungeon.model.creatures;

import android.graphics.Bitmap;

import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.services.ImageService;

public class Player extends Creature{

    private final static int HEALTH = 20;
    private final static float SPEED = FixedValues.SPEED * 2;
    private final static Bitmap IMAGE = ImageService.playerImage;

    public Player(int posX, int posY) {
        super(posX, posY, SPEED, HEALTH, IMAGE);
    }
}
