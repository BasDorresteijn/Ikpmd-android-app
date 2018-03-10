package com.example.bas_d.simpledungeon.views;

import com.example.bas_d.simpledungeon.GameEngine;
import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.model.creatures.Creature;

public class GameCamera {

    private float yOffSet = 0, xOffSet = 0;
    private int maxX, maxY;
    private GameEngine gameEngine;

    public GameCamera(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void centerOnEntity(Creature c) {
        xOffSet = c.getPosX() - gameEngine.getWidth() / 2 + c.getResImage().getWidth() / 2;
        yOffSet = c.getPosY() - gameEngine.getHeight() / 3 + c.getResImage().getHeight() / 2;
        if(xOffSet < 0) {
            xOffSet = 0;
        }
        if(yOffSet < 0) {
            yOffSet = 0;
        }
        if(xOffSet > maxX - gameEngine.getWidth()) {
            xOffSet = maxX - gameEngine.getWidth();
        }
        if(yOffSet > maxY - gameEngine.getHeight() + FixedValues.CONTROLSHEIGHT + 16) {
            yOffSet = maxY - gameEngine.getHeight() + FixedValues.CONTROLSHEIGHT + 16;
        }
    }

    public float getyOffSet() {
        return yOffSet;
    }

    public float getxOffSet() {
        return xOffSet;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }
}
