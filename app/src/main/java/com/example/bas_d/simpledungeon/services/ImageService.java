package com.example.bas_d.simpledungeon.services;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.example.bas_d.simpledungeon.R;
import com.example.bas_d.simpledungeon.model.FixedValues;

public class ImageService {

    public static Bitmap playerImage, skeletonImage, playerAttackingR;
    public static Bitmap controlsImage, aImage;
    public static Bitmap basicSwordR, basicSwordL, basicSwordU, basicSwordD;
    public static Bitmap wall, grass, stairs;
    public static Bitmap defaultImage, heart;

    private Resources resources;

    public ImageService(Resources resources) {
        this.resources = resources;
        loadCreatures();
        loadEntities();
        loadControls();
        loadTerrains();
        loadOthers();
    }

    private void loadCreatures() {
        playerImage = BitmapFactory.decodeResource(resources, R.drawable.player);
        playerImage = Bitmap.createScaledBitmap(playerImage, FixedValues.WIDTH, FixedValues.HEIGHT, true);
        playerAttackingR = BitmapFactory.decodeResource(resources, R.drawable.playerattacking);
        playerAttackingR = Bitmap.createScaledBitmap(playerAttackingR, FixedValues.WIDTH, FixedValues.HEIGHT, true);
        skeletonImage = BitmapFactory.decodeResource(resources, R.drawable.skeleton);
        skeletonImage = Bitmap.createScaledBitmap(skeletonImage, FixedValues.WIDTH, FixedValues.HEIGHT, true);
    }

    private void loadEntities() {
        basicSwordR = BitmapFactory.decodeResource(resources, R.drawable.swordright);
        Matrix m = new Matrix();
        m.postRotate(90);
        basicSwordR = Bitmap.createScaledBitmap(basicSwordR, FixedValues.WIDTH, FixedValues.HEIGHT, true);
        basicSwordD = Bitmap.createBitmap(basicSwordR,0,0, FixedValues.WIDTH, FixedValues.HEIGHT,m, true);
        basicSwordL = Bitmap.createBitmap(basicSwordD,0,0, FixedValues.WIDTH, FixedValues.HEIGHT,m, true);
        basicSwordU = Bitmap.createBitmap(basicSwordL,0,0, FixedValues.WIDTH, FixedValues.HEIGHT,m, true);
    }

    private void loadControls() {
        controlsImage = BitmapFactory.decodeResource(resources, R.drawable.dpad);
        controlsImage = Bitmap.createScaledBitmap(controlsImage, FixedValues.CONTROLSWIDTH, FixedValues.CONTROLSHEIGHT, true);
        aImage = BitmapFactory.decodeResource(resources, R.drawable.a);
        aImage = Bitmap.createScaledBitmap(aImage, FixedValues.CONTROLSWIDTH/3, FixedValues.CONTROLSHEIGHT/3, true);
    }

    private void loadTerrains() {
        wall = BitmapFactory.decodeResource(resources, R.drawable.wall);
        wall = Bitmap.createScaledBitmap(wall, FixedValues.WIDTH, FixedValues.HEIGHT, true);
        grass = BitmapFactory.decodeResource(resources, R.drawable.grass);
        grass = Bitmap.createScaledBitmap(grass, FixedValues.WIDTH, FixedValues.HEIGHT, true);
        stairs = BitmapFactory.decodeResource(resources, R.drawable.stairs);
        stairs = Bitmap.createScaledBitmap(stairs, FixedValues.WIDTH, FixedValues.HEIGHT, true);
    }

    private void loadOthers() {
        defaultImage = BitmapFactory.decodeResource(resources, R.drawable.test);
        defaultImage = Bitmap.createScaledBitmap(defaultImage, FixedValues.WIDTH, FixedValues.HEIGHT, true);
        heart = BitmapFactory.decodeResource(resources, R.drawable.heart);
        heart = Bitmap.createScaledBitmap(heart, FixedValues.STATSHEIGHT, FixedValues.STATSHEIGHT, true);
    }

}
