package com.example.bas_d.simpledungeon;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Canvas;
import android.util.Log;

import com.example.bas_d.simpledungeon.database.DatabaseHelper;
import com.example.bas_d.simpledungeon.database.DatabaseInfo;
import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.model.creatures.Creature;
import com.example.bas_d.simpledungeon.model.creatures.Skeleton;
import com.example.bas_d.simpledungeon.model.terrain.Grass;
import com.example.bas_d.simpledungeon.model.terrain.Map;
import com.example.bas_d.simpledungeon.model.terrain.Terrain;
import com.example.bas_d.simpledungeon.views.GameCamera;

import java.util.ArrayList;
import java.util.List;

public class MapController {

    private Map map;
    private int mapID;

    private GameCamera gameCamera;
    private CreatureManager creatureManager;
    private DatabaseHelper databaseHelper;

    public MapController(GameEngine gameEngine) {
        gameEngine.setMapController(this);
        this.gameCamera = gameEngine.getGameCamera();
        this.creatureManager = gameEngine.getCreatureManager();
        this.databaseHelper = gameEngine.getDatabaseHelper();
        mapID = 1;
        checkMaps();
        loadNewMap();
    }

    private void checkMaps() {
        Cursor rs = databaseHelper.query(DatabaseInfo.SimpleDungeonTables.MAP, new String[]{DatabaseInfo.MapColumn.TERRAINS}, null,null, null, null, null);
        if(!rs.moveToFirst()) {
            insertMaps();
            insertCreatures();
        }
        rs.close();
    }

    private String getMap() {
        Cursor rs = databaseHelper.query(DatabaseInfo.SimpleDungeonTables.MAP, new String[]{DatabaseInfo.MapColumn.TERRAINS},
                DatabaseInfo.MapColumn.ID +"=?", new String[]{String.valueOf(mapID)}, null, null, null);
        if(rs.moveToFirst()) {
            return  rs.getString(rs.getColumnIndex(DatabaseInfo.MapColumn.TERRAINS));
        } else {
            //VICTORY
            return "";
        }
    }

    private ArrayList<Creature> getCreatures() {
        ArrayList<Creature> creatures = new ArrayList<>();
        Cursor rs = databaseHelper.query(DatabaseInfo.SimpleDungeonTables.CREATURES, new String[]{},
                DatabaseInfo.CreaturesColumn.MAPID +"=?", new String[]{String.valueOf(mapID)}, null, null, null);
        while(rs.moveToNext()) {
            Creature c = (Creature) getCreature("com.example.bas_d.simpledungeon.model.creatures." + rs.getString(rs.getColumnIndex(DatabaseInfo.CreaturesColumn.NAME)));
            if (c != null) {
                c.setPosX(rs.getInt(rs.getColumnIndex(DatabaseInfo.CreaturesColumn.POSX)));
                c.setPosY(rs.getInt(rs.getColumnIndex(DatabaseInfo.CreaturesColumn.POSY)));
                creatures.add(c);
            }
        }
        return creatures;
    }
    
    private Object getCreature(String classname) {
        try {
            Class c = Class.forName(classname);
            return c.newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void insertMap(String mapString) {
        ContentValues map = new ContentValues();
        map.put(DatabaseInfo.MapColumn.TERRAINS, mapString);
        databaseHelper.insert(DatabaseInfo.SimpleDungeonTables.MAP, null, map);
    }

    private void insertCreature(String creatureName, int id, int posX, int posY) {
        ContentValues creature = new ContentValues();
        creature.put(DatabaseInfo.CreaturesColumn.MAPID, id);
        creature.put(DatabaseInfo.CreaturesColumn.NAME, creatureName);
        creature.put(DatabaseInfo.CreaturesColumn.POSX, posX);
        creature.put(DatabaseInfo.CreaturesColumn.POSY, posY);
        databaseHelper.insert(DatabaseInfo.SimpleDungeonTables.CREATURES, null, creature);
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
        map = new Map(getMap());
        creatureManager.setCreatures(getCreatures());
        mapID += 1;
    }

    private void insertMaps() {
        insertMap("WA WA WA WA WA WA WA WA WA WA WA WA WA WA nl" +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl " +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR GR GR GR ST GR GR WA nl" +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl" +
                "WA WA WA WA WA WA WA WA WA WA WA WA WA WA nl");
    }

    private void insertCreatures() {
        insertCreature("Skeleton", 1, 500, 600);
        insertCreature("Skeleton", 1, 700, 900);
        insertCreature("Skeleton", 1, 300, 1000);
    }

}
