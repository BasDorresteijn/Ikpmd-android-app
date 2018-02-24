package com.example.bas_d.simpledungeon.model.creatures;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.services.ImageService;

/**
 * Created by bas_d on 24-2-2018.
 */

public class Skeleton extends Creature {

    public Skeleton(float posX, float posY) {
        super(posX, posY, ImageService.skeletonImage);
    }
    @Override
    public Rect getBounds() {
        return new Rect((int) this.getPosX() + FixedValues.WIDTH/4, (int) this.getPosY() + FixedValues.HEIGHT/16,
                (int) this.getPosX() + FixedValues.WIDTH/8*5, (int) this.getPosY() + FixedValues.HEIGHT/16*15);
    }
}
