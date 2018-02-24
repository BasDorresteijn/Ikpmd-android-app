package com.example.bas_d.simpledungeon;

import android.graphics.Rect;
import android.util.Log;

import com.example.bas_d.simpledungeon.input.Inputs;
import com.example.bas_d.simpledungeon.model.creatures.Creature;
import com.example.bas_d.simpledungeon.model.creatures.Player;
import com.example.bas_d.simpledungeon.model.creatures.Skeleton;

import java.util.ArrayList;
import java.util.Random;

public class CreatureManager {

    private ArrayList<Creature> creatures;
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
                        c.setPosX(c.getPosX() + c.getSpeed());
                    }
                    if(c.getPosX() > player.getPosX()) {
                        c.setPosX(c.getPosX() - c.getSpeed());
                    }
                    if(c.getPosY() < player.getPosY()) {
                        c.setPosY(c.getPosY() + c.getSpeed());
                    }
                    if(c.getPosY() > player.getPosY()) {
                        c.setPosY(c.getPosY() - c.getSpeed());
                    }
                }
            }
        }
    }
}
