package com.example.bas_d.simpledungeon;

import android.graphics.Canvas;

import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.model.terrain.Grass;
import com.example.bas_d.simpledungeon.model.terrain.Map;
import com.example.bas_d.simpledungeon.model.terrain.Terrain;
import com.example.bas_d.simpledungeon.model.terrain.Wall;

import java.util.ArrayList;

public class MapController {

    private Map map;

    private float xOffset, yOffset;

    public MapController() {
        map = new Map("GR GR GR GR WA WA WA WA nl GR GR GR GR WA nl GR GR GR GR WA WA");
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

    public Terrain getTerrain(int x, int y) {
        Terrain t;
        try {
            t = map.getMap().get(y).get(x);
        } catch (IndexOutOfBoundsException ie) {
            t = new Grass();
        }
        return t;
    }
}
