package com.example.bas_d.simpledungeon;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.bas_d.simpledungeon.input.InputDetector;
import com.example.bas_d.simpledungeon.input.Inputs;
import com.example.bas_d.simpledungeon.model.creatures.Creature;
import com.example.bas_d.simpledungeon.model.creatures.Player;

public class GameEngine {

    private InputDetector inputDetector;
    private Player player;
    private Paint p;
    private CreatureManager creatureManager;
    private int width, height;

    public GameEngine(CreatureManager creatureManager) {
        this.creatureManager = creatureManager;

        creatureManager.setPlayer(new Player(10, 10));
        player = creatureManager.getPlayer();

        p = new Paint();
    }

    public void draw(Canvas canvas) {
        for(Creature creature : creatureManager.getCreatures()) {
            canvas.drawBitmap(creature.getResImage(), creature.getPosX(), creature.getPosY(), p);
        }
        inputDetector.draw(canvas);
    }

    public void update() {
        if(Inputs.up) {
            player.setPosY(player.getPosY() + player.getSpeed());
        }
        if(Inputs.down) {
            player.setPosY(player.getPosY() - player.getSpeed());
        }
        if(Inputs.left) {
            player.setPosX(player.getPosX() - player.getSpeed());
        }
        if(Inputs.right) {
            player.setPosX(player.getPosX() + player.getSpeed());
        }
//        creatureManager.randomCreature();
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

    public InputDetector getInputDetector() {
        return inputDetector;
    }

    public void setInputDetector(InputDetector inputDetector) {
        this.inputDetector = inputDetector;
    }
}
