package com.example.bas_d.simpledungeon;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.bas_d.simpledungeon.database.DatabaseHelper;
import com.example.bas_d.simpledungeon.input.InputDetector;
import com.example.bas_d.simpledungeon.model.creatures.Player;
import com.example.bas_d.simpledungeon.model.creatures.Skeleton;
import com.example.bas_d.simpledungeon.views.GameCamera;

public class GameEngine {

    private InputDetector inputDetector;
    private Player player;
    private Paint pr, pb;
    private CreatureManager creatureManager;
    private MapController mapController;
    private GameCamera gameCamera;
    private int width, height;
    private RenderThread renderThread;
    private DatabaseHelper databaseHelper;

    public GameEngine(CreatureManager creatureManager, DatabaseHelper databaseHelper) {
        this.gameCamera = new GameCamera(this);
        this.creatureManager = creatureManager;
        this.creatureManager.setGameCamera(gameCamera);
        this.creatureManager.setGameEngine(this);
        this.databaseHelper = databaseHelper;

        creatureManager.setPlayer(new Player(150, 150));
        player = creatureManager.getPlayer();

        pr = new Paint();
        pr.setColor(Color.RED);
        pr.setTextSize(48);

        pb = new Paint();
        pb.setColor(Color.BLUE);
        pb.setTextSize(48);

//        test();

    }

    private void test() {
        creatureManager.addCreature(new Skeleton(500, 700));
        creatureManager.addCreature(new Skeleton(400, 400));
        creatureManager.addCreature(new Skeleton(300, 200));
    }

    public void draw(Canvas canvas) {
        mapController.drawMap(canvas);
        creatureManager.draw(canvas);
        inputDetector.draw(canvas);
        canvas.drawText("HEALTH: " + player.getHealth(), 300, 650, pr);
        long now = System.currentTimeMillis();
        long cooldown = (player.getSword().getLastUsed() + player.getSword().getCooldown() + player.getSword().getUseTime()) - now;
        if(cooldown < 0) {
            cooldown = 0;
        }
        canvas.drawText("COOLDOWN: " + cooldown, 300, 700, pb);

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
    }

    public void setWidth(int width) {
        this.width = width;
        this.inputDetector.setWidth(width);
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

}
