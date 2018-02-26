package com.example.bas_d.simpledungeon.model.terrain;

import android.graphics.Bitmap;

import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.services.ImageService;

public abstract class Terrain {

    private Bitmap terrainImage;
    protected boolean solid;

    public Terrain() {
        this.terrainImage = ImageService.defaultImage;
    }

    public Terrain(Bitmap terrainImage, boolean solid) {
        this.terrainImage = terrainImage;
        this.solid = solid;
    }

    public Bitmap getTerrainImage() {
        return terrainImage;
    }

    public void setTerrainImage(Bitmap terrainImage) {
        this.terrainImage = terrainImage;
    }

    public boolean isSolid() {
        return solid;
    }
}
