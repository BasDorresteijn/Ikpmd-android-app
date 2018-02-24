package com.example.bas_d.simpledungeon;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.bas_d.simpledungeon.input.InputDetector;
import com.example.bas_d.simpledungeon.model.creatures.Creature;
import com.example.bas_d.simpledungeon.model.creatures.Player;
import com.example.bas_d.simpledungeon.model.creatures.Skeleton;

public class GameEngine {

    private InputDetector inputDetector;
    private Player player;
    private Paint p;
    private CreatureManager creatureManager;
    private MapController mapController;
    private int width, height;

    public GameEngine(CreatureManager creatureManager) {
        this.creatureManager = creatureManager;

        creatureManager.setPlayer(new Player(10, 10));
        player = creatureManager.getPlayer();

        p = new Paint();
        p.setColor(Color.RED);
        p.setTextSize(48);

        test();

    }

    private void test() {
        creatureManager.addCreature(new Skeleton(500, 700));
        creatureManager.addCreature(new Skeleton(400, 400));
        creatureManager.addCreature(new Skeleton(300, 200));
    }

    public void draw(Canvas canvas) {
        mapController.drawMap(canvas);
        for(Creature creature : creatureManager.getCreatures()) {
            canvas.drawBitmap(creature.getResImage(), creature.getPosX(), creature.getPosY(), p);
        }
        inputDetector.draw(canvas);
        canvas.drawText("HEALTH: " + player.getHealth(), 300, 700, p);
    }

    public void update() {
        creatureManager.moveSkeletons();
        creatureManager.creatureCollision();
        creatureManager.updatePlayer();
    }

    public void setHeight(int height) {
        this.height = height;
        this.inputDetector.setHeight(height);
        this.creatureManager.setMaxHeight(height);
    }

    public void setWidth(int width) {
        this.width = width;
        this.inputDetector.setWidth(width);
        this.creatureManager.setMaxWidth(width);
    }

    public void setInputDetector(InputDetector inputDetector) {
        this.inputDetector = inputDetector;
    }

    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }
}
