package com.example.bas_d.simpledungeon.services;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.bas_d.simpledungeon.R;
import com.example.bas_d.simpledungeon.model.FixedValues;

public class ImageService {

    public static Bitmap playerImage, defaultImage;
    public static Bitmap controlsImage;

    public ImageService(Resources resources) {
        playerImage = BitmapFactory.decodeResource(resources, R.drawable.player);
        playerImage = Bitmap.createScaledBitmap(playerImage, FixedValues.WIDTH, FixedValues.HEIGHT, true);
        defaultImage = BitmapFactory.decodeResource(resources, R.drawable.test);
        defaultImage = Bitmap.createScaledBitmap(defaultImage, FixedValues.WIDTH, FixedValues.HEIGHT, true);
        controlsImage = BitmapFactory.decodeResource(resources, R.drawable.dpad);
        controlsImage = Bitmap.createScaledBitmap(controlsImage, FixedValues.WIDTH * 2, FixedValues.HEIGHT * 2, true);
    }

}
