package com.example.bas_d.simpledungeon.model.terrain;

import android.util.Log;
import android.widget.Switch;

import com.example.bas_d.simpledungeon.model.FixedValues;

import java.util.ArrayList;

public class Map {

    private ArrayList<ArrayList<Terrain>> map;
    private int maxX;
    private int maxY;

    public Map(String map) {
        this.map = new ArrayList<>();
        String[] newMap = map.split("nl");
        for(String row : newMap) {
            addRow(row);
        }
        maxX = maxX * FixedValues.WIDTH;
        maxY = newMap.length * FixedValues.HEIGHT;
    }

    private void addRow(String row) {
        String[] pos = row.split(" ");
        ArrayList<Terrain> newRow = new ArrayList<>();
        for(String terrain : pos) {
            switch (terrain) {
                case "WA":
                    newRow.add(new Wall());
                    break;
                case "GR":
                    newRow.add(new Grass());
                    break;
                case "ST":
                    newRow.add(new Stairs());
                    break;
            }

        }
        if(maxX < newRow.size()) {
            maxX = newRow.size();
        }
        map.add(newRow);
    }

    public ArrayList<ArrayList<Terrain>> getMap() {
        return map;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
