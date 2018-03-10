package com.example.bas_d.simpledungeon.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static SQLiteDatabase mSQLDB;
    private static DatabaseHelper mInstance;
    public static final String dbName = "simpleDungeon.db";
    public static final int dbVersion = 3;

    private DatabaseHelper(Context ctx) {
        super(ctx, dbName, null, dbVersion);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version ){
        super(context,name,factory, version);
    }

    public static synchronized DatabaseHelper getHelper (Context ctx){
        if (mInstance == null){
            mInstance = new DatabaseHelper(ctx);
            mSQLDB = mInstance.getWritableDatabase();
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DatabaseInfo.SimpleDungeonTables.MAP + " ("  +
                DatabaseInfo.MapColumn.TERRAINS + " TEXT," +
                DatabaseInfo.MapColumn.ID + " INTEGER PRIMARY KEY AUTOINCREMENT);"
        );
        db.execSQL("CREATE TABLE " + DatabaseInfo.SimpleDungeonTables.CREATURES + " (" +
                DatabaseInfo.CreaturesColumn.NAME + " TEXT, " +
                DatabaseInfo.CreaturesColumn.MAPID + " INTEGER," +
                DatabaseInfo.CreaturesColumn.POSX + " INTEGER," +
                DatabaseInfo.CreaturesColumn.POSY + " INTEGER);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.SimpleDungeonTables.MAP);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.SimpleDungeonTables.CREATURES);
        onCreate(db);
    }

    public void insert(String table, String nullColumnHack, ContentValues values){
        mSQLDB.insert(table, nullColumnHack, values);
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectArgs, String groupBy, String having, String orderBy){
        return mSQLDB.query(table, columns, selection, selectArgs, groupBy, having, orderBy);
    }

}

