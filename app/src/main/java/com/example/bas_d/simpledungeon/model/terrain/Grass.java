package com.example.bas_d.simpledungeon.model.terrain;

import com.example.bas_d.simpledungeon.services.ImageService;

public class Grass extends Terrain {

    public Grass() {
        super(ImageService.grass, false);
    }

}
