package com.example.bas_d.simpledungeon.model.terrain;

import android.util.Log;
import android.widget.Switch;

import java.util.ArrayList;

public class Map {

    private ArrayList<ArrayList<Terrain>> map;

    public Map(String map) {
        this.map = new ArrayList<>();
        String[] newMap = map.split("nl");
        for(String row : newMap) {
            addRow(row);
        }
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
            }

        }
        map.add(newRow);
    }

    public ArrayList<ArrayList<Terrain>> getMap() {
        return map;
    }

}
