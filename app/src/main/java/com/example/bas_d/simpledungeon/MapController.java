package com.example.bas_d.simpledungeon;

import android.graphics.Canvas;

import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.model.terrain.Map;
import com.example.bas_d.simpledungeon.model.terrain.Terrain;

import java.util.ArrayList;

public class MapController {

    private Map map;

    private float xOffset, yOffset;

    public MapController() {
        map = new Map("WA WA WA WA nl WA nl WA WA");
        xOffset = 0;
        yOffset = 0;
    }

    public void drawMap(Canvas canvas) {
        int width = 0, height = 0;
        for(ArrayList<Terrain> row : map.getMap()) {
            for(Terrain terrain : row) {
                canvas.drawBitmap(terrain.getTerrainImage(), width, height, null);
                width += FixedValues.WIDTH;
            }
            width = 0;
            height += FixedValues.HEIGHT;
        }
    }
}
