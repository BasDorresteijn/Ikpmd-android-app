package com.example.bas_d.simpledungeon.model.terrain;

import com.example.bas_d.simpledungeon.services.ImageService;

public class Wall extends Terrain {

    public Wall() {
        super(ImageService.wall);
    }
}
