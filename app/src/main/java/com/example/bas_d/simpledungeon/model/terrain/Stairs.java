package com.example.bas_d.simpledungeon.model.terrain;

import com.example.bas_d.simpledungeon.services.ImageService;

public class Stairs extends Terrain{

    public Stairs() {
        super(ImageService.stairs, false);
    }
}
