package com.example.bas_d.simpledungeon.services;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.bas_d.simpledungeon.R;
import com.example.bas_d.simpledungeon.model.FixedValues;

public class ImageService {

    public static Bitmap playerImage, skeletonImage;
    public static Bitmap controlsImage;
    public static Bitmap wall;
    public static Bitmap defaultImage;

    private Resources resources;

    public ImageService(Resources resources) {
        this.resources = resources;
        loadCreatures();
        loadControls();
        loadTerrains();
        loadOthers();
    }

    private void loadCreatures() {
        playerImage = BitmapFactory.decodeResource(resources, R.drawable.player);
        playerImage = Bitmap.createScaledBitmap(playerImage, FixedValues.WIDTH, FixedValues.HEIGHT, true);
        skeletonImage = BitmapFactory.decodeResource(resources, R.drawable.skeleton);
        skeletonImage = Bitmap.createScaledBitmap(skeletonImage, FixedValues.WIDTH, FixedValues.HEIGHT, true);
    }

    private void loadControls() {
        controlsImage = BitmapFactory.decodeResource(resources, R.drawable.dpad);
        controlsImage = Bitmap.createScaledBitmap(controlsImage, FixedValues.CONTROLSWIDTH, FixedValues.CONTROLSHEIGHT, true);
    }

    private void loadTerrains() {
        wall = BitmapFactory.decodeResource(resources, R.drawable.wall);
        wall = Bitmap.createScaledBitmap(wall, FixedValues.WIDTH, FixedValues.HEIGHT, true);
    }

    private void loadOthers() {
        defaultImage = BitmapFactory.decodeResource(resources, R.drawable.test);
        defaultImage = Bitmap.createScaledBitmap(defaultImage, FixedValues.WIDTH, FixedValues.HEIGHT, true);
    }

}
