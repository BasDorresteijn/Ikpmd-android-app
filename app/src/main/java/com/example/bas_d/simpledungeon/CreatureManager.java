package com.example.bas_d.simpledungeon;

import android.graphics.Canvas;

import com.example.bas_d.simpledungeon.input.Inputs;
import com.example.bas_d.simpledungeon.input.Pressed;
import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.model.creatures.Creature;
import com.example.bas_d.simpledungeon.model.creatures.Player;
import com.example.bas_d.simpledungeon.model.creatures.Skeleton;
import com.example.bas_d.simpledungeon.model.entities.Entity;
import com.example.bas_d.simpledungeon.services.ImageService;

import java.util.ArrayList;
import java.util.Random;

public class CreatureManager {

    private ArrayList<Creature> creatures;
    private ArrayList<Entity> entities;
    private MapController mapController;
    private Player player;
    private int maxWidth;
    private int maxHeight;
    private long immumeTime = 0;
    private long immume = 600;
    private Pressed lastpressed = Pressed.Left;

    public CreatureManager() {
        this.creatures = new ArrayList<>();
        this.entities = new ArrayList<>();
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public void removeCreature(Creature creature) {
        creatures.remove(creature);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        creatures.remove(entity);
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
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
        if(Inputs.down) {
            lastpressed = Pressed.Down;
            moveDown(player);
        }
        if(Inputs.up) {
            lastpressed = Pressed.Up;
            moveUp(player);
        }
        if(Inputs.left) {
            lastpressed = Pressed.Left;
            moveLeft(player);
        }
        if(Inputs.right) {
            lastpressed = Pressed.Right;
            moveRight(player);
        }
    }

    public void creatureCollision() {
        for(Creature c : creatures) {
            if(player.getBounds().intersect(c.getBounds())) {
                if(!player.equals(c)) {
                        doCombat(c, player);
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

    public void draw(Canvas canvas) {
        for(Creature creature : this.getCreatures()) {
            canvas.drawBitmap(creature.getResImage(), creature.getPosX(), creature.getPosY(), null);
        }
        drawPlayersSword(canvas);
    }

    private void drawPlayersSword(Canvas canvas) {
        long now = System.currentTimeMillis();
        if (now < player.getSword().getLastUsed() + player.getSword().getUseTime()) {
            if (lastpressed == Pressed.Right) {
                player.getSword().setResImage(ImageService.basicSwordR);
                canvas.drawBitmap(player.getSword().getResImage(), player.getPosX() + FixedValues.WIDTH, player.getPosY(), null);
            }
            if (lastpressed == Pressed.Left) {
                player.getSword().setResImage(ImageService.basicSwordL);
                canvas.drawBitmap(player.getSword().getResImage(), player.getPosX() - FixedValues.WIDTH, player.getPosY(), null);
            }
            if (lastpressed == Pressed.Up) {
                player.getSword().setResImage(ImageService.basicSwordU);
                canvas.drawBitmap(player.getSword().getResImage(), player.getPosX(), player.getPosY() - FixedValues.HEIGHT, null);
            }
            if (lastpressed == Pressed.Down) {
                player.getSword().setResImage(ImageService.basicSwordD);
                canvas.drawBitmap(player.getSword().getResImage(), player.getPosX(), player.getPosY() + FixedValues.HEIGHT, null);
            }
        }
    }

    public void doAttack() {
        ArrayList<Creature> deadCreatures = new ArrayList<>();
        long now = System.currentTimeMillis();
        if(Inputs.a) {
            if(now > player.getSword().getLastUsed() + player.getSword().getCooldown() + player.getSword().getUseTime()) {
                player.getSword().setLastUsed(now);
            }
        }
        if(now < player.getSword().getLastUsed() + player.getSword().getUseTime()) {
            if (lastpressed == Pressed.Right) {
                player.setResImage(ImageService.playerAttackingR);
                for (Creature c : creatures) {
                    if (player.getSword().getXBounds((int) player.getPosX() + FixedValues.WIDTH, (int) player.getPosY()).intersect(c.getBounds())) {
                        if (!player.equals(c)) {
                            doPlayerCombat(player, c);
                        }
                    }
                }
            }
            if (lastpressed == Pressed.Left) {
                for (Creature c : creatures) {
                    if (player.getSword().getXBounds((int) player.getPosX() - FixedValues.WIDTH, (int) player.getPosY()).intersect(c.getBounds())) {
                        if (!player.equals(c)) {
                            doPlayerCombat(player, c);
                        }
                    }
                }
            }
            if (lastpressed == Pressed.Up) {
                for (Creature c : creatures) {
                    if (player.getSword().getYBounds((int) player.getPosX(), (int) player.getPosY() - FixedValues.HEIGHT).intersect(c.getBounds())) {
                        if (!player.equals(c)) {
                            doPlayerCombat(player, c);
                        }
                    }
                }
            }
            if (lastpressed == Pressed.Down) {
                for (Creature c : creatures) {
                    if (player.getSword().getYBounds((int) player.getPosX(), (int) player.getPosY() + FixedValues.HEIGHT).intersect(c.getBounds())) {
                        if (!player.equals(c)) {
                            doPlayerCombat(player, c);
                        }
                    }
                }
            }
        }
        else {
            player.setResImage(ImageService.playerImage);
        }
        for(Creature c : creatures) {
            if(c.getHealth() <= 0) {
                deadCreatures.add(c);
            }
        }
        creatures.removeAll(deadCreatures);
    }

    private void doCombat(Creature attacking, Creature defending) {
        long now = System.currentTimeMillis();
        if(now > defending.getImmumeSince() + defending.getImmumeTime()) {
            defending.setHealth(defending.getHealth() - attacking.getAttack());
            defending.setImmumeSince(now);
        }
    }

    private void doPlayerCombat(Player attacking, Creature defending) {
        long now = System.currentTimeMillis();
        if(now > defending.getImmumeSince() + defending.getImmumeTime()) {
            defending.setHealth(defending.getHealth() - attacking.getSword().getAttack());
            defending.setImmumeSince(now);
        }
    }
}
