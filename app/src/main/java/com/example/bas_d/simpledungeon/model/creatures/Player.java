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
    private int score = 0;
    private int damageDealt = 0;
    private int damageTaken = 0;

    public Player() {
        super(0,0, ImageService.playerImage, 0);
    }

    public Player(int posX, int posY) {
        super(posX, posY, SPEED, HEALTH, IMAGE, 0);
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

    public void addPoints(int points) {
        this.score += points;
    }

    public int getScore() {
        return score;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public void addDamageDealt(int damageDealt) {
        this.damageDealt += damageDealt;
    }

    public int getDamageTaken() {
        return damageTaken;
    }

    public void addDamageTaken(int damageTaken) {
        this.damageTaken += damageTaken;
    }
}
