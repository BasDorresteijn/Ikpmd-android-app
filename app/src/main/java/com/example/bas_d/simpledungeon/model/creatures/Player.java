package com.example.bas_d.simpledungeon.model.creatures;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.model.entities.Sword;
import com.example.bas_d.simpledungeon.services.ImageService;

public class Player extends Creature{

    private final static int HEALTH = 20;
    private final static float SPEED = FixedValues.SPEED * 2;
    private final static Bitmap IMAGE = ImageService.playerImage;
    private Sword sword;

    public Player() {
        super(0,0, ImageService.playerImage);
    }

    public Player(int posX, int posY) {
        super(posX, posY, SPEED, HEALTH, IMAGE);
        this.sword = new Sword();
    }

    @Override
    public Rect getBounds() {
        return new Rect((int) this.getPosX() + FixedValues.WIDTH/4, (int) this.getPosY() + FixedValues.HEIGHT/6,
                (int) this.getPosX() + FixedValues.WIDTH/4*3, (int) this.getPosY() + FixedValues.HEIGHT/25*24);
    }

    public Sword getSword() {
        return sword;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }
}
