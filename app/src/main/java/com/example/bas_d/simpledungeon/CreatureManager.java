package com.example.bas_d.simpledungeon;

import com.example.bas_d.simpledungeon.model.creatures.Creature;
import com.example.bas_d.simpledungeon.model.creatures.Player;

import java.util.ArrayList;
import java.util.Random;

public class CreatureManager {

    private ArrayList<Creature> creatures;
    private Player player;
    private int maxWidth;
    private int maxHeight;

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
}
