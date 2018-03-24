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
    private GameEngine gameEngine;
    private SoundEngine soundEngine;

    public MapController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.gameEngine.setMapController(this);
        this.soundEngine = this.gameEngine.getSoundEngine();
        this.gameCamera = gameEngine.getGameCamera();
        this.creatureManager = gameEngine.getCreatureManager();
        this.databaseHelper = gameEngine.getDatabaseHelper();
        mapID = 1;
        checkMaps();
        loadNewMap();
        this.gameCamera.setMaxX(map.getMaxX());
        this.gameCamera.setMaxY(map.getMaxY());
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
            gameEngine.showScores();
            gameEngine.stop();
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
        int xStart = (int) Math.max(0, gameCamera.getxOffSet() / FixedValues.WIDTH);
        int xEnd = (int) Math.min(map.getMaxX() / FixedValues.WIDTH, (gameCamera.getxOffSet() + gameEngine.getWidth()) / FixedValues.WIDTH + 1);
        int yStart = (int) Math.max(0, gameCamera.getyOffSet() / FixedValues.HEIGHT);
        int yEnd = (int) Math.min(map.getMaxY() / FixedValues.HEIGHT, (gameCamera.getyOffSet() + gameEngine.getHeight() - FixedValues.CONTROLSHEIGHT - 16) / FixedValues.HEIGHT + 1);


        int width = xStart*FixedValues.WIDTH, height = yStart*FixedValues.HEIGHT;
        for(int j = yStart; j < yEnd; j++) {
            ArrayList<Terrain> row = map.getMap().get(j);
            for(int i = xStart; i < xEnd; i++) {
                Terrain terrain = row.get(i);
                canvas.drawBitmap(terrain.getTerrainImage(), width - gameCamera.getxOffSet(), height - gameCamera.getyOffSet(), null);
                width += FixedValues.WIDTH;
            }
            width = xStart*FixedValues.WIDTH;
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
        soundEngine.resetSounds(false);
        soundEngine.playBackgroundMusic();
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

        insertMap("WA WA WA WA WA WA WA WA WA WA WA WA WA WA nl" +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl " +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl" +
                "WA GR GR GR WA WA WA WA GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR WA GR GR GR GR GR WA nl" +
                "WA GR GR ST GR GR GR WA GR WA WA GR GR WA nl" +
                "WA GR GR GR GR GR GR WA GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR WA GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR GR GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR WA GR GR GR GR GR WA nl" +
                "WA GR GR GR GR GR GR WA GR GR GR GR GR WA nl" +
                "WA WA WA WA WA WA WA WA WA WA WA WA WA WA nl");
    }

    private void insertCreatures() {
        insertCreature("Skeleton", 1, 500, 600);
        insertCreature("Skeleton", 1, 700, 900);
        insertCreature("Skeleton", 1, 300, 1000);
        insertCreature("Skeleton", 2, 400, 300);
        insertCreature("Skeleton", 2, 700, 900);
        insertCreature("Skeleton", 2, 300, 1000);
        insertCreature("Skeleton", 2, 500, 600);
        insertCreature("Skeleton", 2, 100, 900);
        insertCreature("Skeleton", 2, 600, 100);
    }

}
