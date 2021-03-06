package com.example.bas_d.simpledungeon;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import com.example.bas_d.simpledungeon.database.DatabaseHelper;
import com.example.bas_d.simpledungeon.input.InputDetector;
import com.example.bas_d.simpledungeon.model.creatures.Player;
import com.example.bas_d.simpledungeon.views.GameCamera;
import com.example.bas_d.simpledungeon.views.IngameStats;
import com.example.bas_d.simpledungeon.views.activity.GameActivity;
import com.example.bas_d.simpledungeon.views.activity.ScoreActivity;

public class GameEngine {

    private InputDetector inputDetector;
    private Player player;
    private CreatureManager creatureManager;
    private MapController mapController;
    private GameCamera gameCamera;
    private IngameStats ingameStats;
    private int width, height;
    private RenderThread renderThread;
    private DatabaseHelper databaseHelper;
    private Resources resources;
    private GameActivity gameActivity;
    private ScoreCalc scoreCalc;
    private SoundEngine soundEngine;

    public GameEngine(CreatureManager creatureManager, DatabaseHelper databaseHelper, Resources resources, GameActivity gameActivity) {
        this.gameActivity = gameActivity;
        this.resources = resources;
        this.gameCamera = new GameCamera(this);
        this.soundEngine = new SoundEngine(gameActivity, resources, false);
        this.creatureManager = creatureManager;
        this.creatureManager.setGameCamera(gameCamera);
        this.creatureManager.setGameEngine(this);
        this.creatureManager.setSoundEngine(soundEngine);
        this.databaseHelper = databaseHelper;
        this.scoreCalc = new ScoreCalc(gameActivity);

        creatureManager.setPlayer(new Player(150, 150));
        player = creatureManager.getPlayer();
        ingameStats = new IngameStats(player, resources);
    }

    public void draw(Canvas canvas) {
        mapController.drawMap(canvas);
        creatureManager.draw(canvas);
        inputDetector.draw(canvas);
        ingameStats.draw(canvas);
    }

    public void update() {
        creatureManager.moveSkeletons();
        creatureManager.creatureCollision();
        creatureManager.updatePlayer();
        creatureManager.doAttack();
        creatureManager.checkTile();
    }

    public void setHeight(int height) {
        this.height = height;
        this.inputDetector.setHeight(height);
        this.ingameStats.setMaxHeight(height);
    }

    public void setWidth(int width) {
        this.width = width;
        this.inputDetector.setWidth(width);
        this.ingameStats.setMaxWidth(width);
    }

    public void setInputDetector(InputDetector inputDetector) {
        this.inputDetector = inputDetector;
    }

    public void setMapController(MapController mapController) {
        this.mapController = mapController;
        this.creatureManager.setMapController(mapController);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public CreatureManager getCreatureManager() {
        return creatureManager;
    }

    public void stop() {
        if(renderThread != null) {
            renderThread.pause();
        }
        soundEngine.stopSounds();
    }

    public void start() {
        if(renderThread != null) {
            renderThread.unpause();
        }
    }

    public void setRenderThread(RenderThread renderThread) {
        this.renderThread = renderThread;
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    public IngameStats getIngameStats() {
        return ingameStats;
    }

    public void showScores() {
        soundEngine.stopSounds();
        Intent intent = new Intent(this.gameActivity, ScoreActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("score", scoreCalc.calcScore(player));
        scoreCalc.saveDamageData(player);
        if(player.getHealth() <= 0) {
            bundle.putBoolean("died", true);
        } else {
            bundle.putBoolean("died", false);
        }
        intent.putExtras(bundle);
        this.gameActivity.startActivity(intent);
        this.gameActivity.finish();
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public SoundEngine getSoundEngine() {
        return soundEngine;
    }
}
