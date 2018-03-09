package com.example.bas_d.simpledungeon.database;

public class DatabaseInfo {

    public class SimpleDungeonTables {
        public static final String MAP = "Map";   // NAAM VAN JE TABEL
        public static final String CREATURES = "Creatures";
    }

    public class MapColumn {
        public static final String TERRAINS  = "terrains";	// VASTE WAARDES
        public static final String ID = "id";   // NAAM VAN DE KOLOMMEN
    }

    public class CreaturesColumn {
        public static final String NAME = "name";
        public static final String HEALTH = "health";
        public static final String POSX = "posy";
        public static final String POSY = "posx";
    }

}
