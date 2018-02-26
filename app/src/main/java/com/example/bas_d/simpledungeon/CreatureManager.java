package com.example.bas_d.simpledungeon;

import android.graphics.Rect;
import android.util.Log;

import com.example.bas_d.simpledungeon.input.Inputs;
import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.model.creatures.Creature;
import com.example.bas_d.simpledungeon.model.creatures.Player;
import com.example.bas_d.simpledungeon.model.creatures.Skeleton;
import com.example.bas_d.simpledungeon.model.terrain.Terrain;

import java.util.ArrayList;
import java.util.Random;

public class CreatureManager {

    private ArrayList<Creature> creatures;
    private MapController mapController;
    private Player player;
    private int maxWidth;
    private int maxHeight;
    private long immumeTime = 0;
    private long immume = 600;

    public CreatureManager() {
        this.creatures = new ArrayList<>();
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public void removeCreature(Creature creature) {
        creatures.remove(creature);
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public void setCreatures(ArrayList<Creature> creatures) {
        this.creatures = creatures;
    }

    public void randomCreature() {
        Random random = new Random();
        if(random.nextInt(100) >= 99) {
            creatures.add(new Player(random.nextInt(maxWidth), random.nextInt(maxHeight)));
        }
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        addCreature(player);
    }

    public void updatePlayer() {
        if(Inputs.up) {
            moveUp(player);
        }
        if(Inputs.down) {
            moveDown(player);
        }
        if(Inputs.left) {
            moveLeft(player);
        }
        if(Inputs.right) {
            moveRight(player);
        }
    }

    public void creatureCollision() {
        long timeMillisNow = System.currentTimeMillis();
        if(timeMillisNow > immumeTime) {
            for(Creature c : creatures) {
                if(player.getBounds().intersect(c.getBounds())) {
                    if(!player.equals(c)) {
                        player.setHealth(player.getHealth() - 2);
                        immumeTime = System.currentTimeMillis() + immume;
                    }
                }
            }
        }
    }

    public void moveSkeletons() {
        for(Creature c : creatures) {
            if(c.getClass().equals(Skeleton.class)) {
                float deltaX = c.getPosX() - player.getPosX();
                float deltaY = c.getPosY() - player.getPosY();
                double distance = Math.sqrt((deltaX*deltaX)+(deltaY*deltaY));
                if(300 > distance && distance > 20) {
                    if(c.getPosX() < player.getPosX()) {
                        moveRight(c);
                    }
                    if(c.getPosX() > player.getPosX()) {
                        moveLeft(c);
                    }
                    if(c.getPosY() < player.getPosY()) {
                        moveDown(c);
                    }
                    if(c.getPosY() > player.getPosY()) {
                        moveUp(c);
                    }
                }
            }
        }
    }

    public boolean collisionWithTerrain(int x, int y) {
        return mapController.getTerrain(x / FixedValues.WIDTH, y / FixedValues.HEIGHT).isSolid();
    }

    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    private void moveUp(Creature c) {
        if(!collisionWithTerrain(c.getBounds().left, c.getBounds().top - (int) c.getSpeed()) &&
                !collisionWithTerrain(c.getBounds().right, c.getBounds().top - (int) c.getSpeed())) {
            c.setPosY(c.getPosY() - c.getSpeed());
        } else {
            int ty = ((int)Math.floor(c.getBounds().top + c.getSpeed()) / FixedValues.HEIGHT);
            c.setPosY(ty * FixedValues.HEIGHT - (c.getBounds().top - c.getPosY()));
        }
    }

    private void moveDown(Creature c) {
        if(!collisionWithTerrain(c.getBounds().left, c.getBounds().bottom + (int) c.getSpeed()) &&
                !collisionWithTerrain(c.getBounds().right, c.getBounds().bottom + (int) c.getSpeed())) {
            c.setPosY(c.getPosY() + c.getSpeed());
        } else {
            int ty = ((int)Math.floor(c.getBounds().bottom + c.getSpeed()) / FixedValues.HEIGHT);
            c.setPosY(ty * FixedValues.HEIGHT - (c.getBounds().bottom - c.getPosY()) - 1);
        }
    }

    private void moveLeft(Creature c) {
        if(!collisionWithTerrain(c.getBounds().left - (int) c.getSpeed(), c.getBounds().top) &&
                !collisionWithTerrain(c.getBounds().left - (int) c.getSpeed(), c.getBounds().bottom)) {
            c.setPosX(c.getPosX() - c.getSpeed());
        } else {
            int tx = ((int)Math.floor(c.getBounds().left + c.getSpeed()) / FixedValues.WIDTH);
            c.setPosX(tx * FixedValues.WIDTH - (c.getBounds().left - c.getPosX()));
        }
    }

    private void moveRight(Creature c) {
        if(!collisionWithTerrain(c.getBounds().right + (int) c.getSpeed(), c.getBounds().top ) &&
                !collisionWithTerrain(c.getBounds().right + (int) c.getSpeed(), c.getBounds().bottom)) {
            c.setPosX(c.getPosX() + c.getSpeed());
        } else {
            int tx = ((int)Math.floor(c.getBounds().right + c.getSpeed()) / FixedValues.WIDTH);
            c.setPosX(tx * FixedValues.WIDTH - (c.getBounds().right - c.getPosX()) - 1);
        }
    }
}
