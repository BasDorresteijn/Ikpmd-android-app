package com.example.bas_d.simpledungeon.model.terrain;

import android.graphics.Bitmap;

import com.example.bas_d.simpledungeon.model.FixedValues;

public abstract class Terrain {

    private Bitmap terrainImage;

    public Terrain() {
        this.terrainImage = terrainImage;
    }

    public Terrain(Bitmap terrainImage) {
        this.terrainImage = terrainImage;
    }

    public Bitmap getTerrainImage() {
        return terrainImage;
    }

    public void setTerrainImage(Bitmap terrainImage) {
        this.terrainImage = terrainImage;
    }

}
