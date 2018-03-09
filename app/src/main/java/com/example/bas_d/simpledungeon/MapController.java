package com.example.bas_d.simpledungeon;

import android.graphics.Canvas;

import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.model.creatures.Creature;
import com.example.bas_d.simpledungeon.model.terrain.Grass;
import com.example.bas_d.simpledungeon.model.terrain.Map;
import com.example.bas_d.simpledungeon.model.terrain.Terrain;
import com.example.bas_d.simpledungeon.model.terrain.Wall;
import com.example.bas_d.simpledungeon.views.GameCamera;

import java.util.ArrayList;

public class MapController {

    private Map map;
    private int mapID;

    private GameCamera gameCamera;
    private CreatureManager creatureManager;

    public MapController(GameEngine gameEngine) {
        gameEngine.setMapController(this);
        gameCamera = gameEngine.getGameCamera();
        creatureManager = gameEngine.getCreatureManager();
        mapID = 0;
        map = new Map("GR GR GR GR WA WA WA WA nl" +
                "GR GR GR GR WA nl" +
                "GR GR GR GR WA WA nl" +
                "ST");
    }

    public void drawMap(Canvas canvas) {
        int width = 0, height = 0;
        for(ArrayList<Terrain> row : map.getMap()) {
            for(Terrain terrain : row) {
                canvas.drawBitmap(terrain.getTerrainImage(), width - gameCamera.getxOffSet(), height - gameCamera.getyOffSet(), null);
                width += FixedValues.WIDTH;
            }
            width = 0;
            height += FixedValues.HEIGHT;
        }
    }

    public Terrain getTerrain(int x, int y) {
        Terrain t;
        try {
            t = map.getMap().get(y / FixedValues.HEIGHT).get(x / FixedValues.WIDTH);
        } catch (IndexOutOfBoundsException ie) {
            t = new Grass();
        }
        return t;
    }

    public void loadNewMap() {
        map = new Map("GR GR GR GR GR");
    }
}
