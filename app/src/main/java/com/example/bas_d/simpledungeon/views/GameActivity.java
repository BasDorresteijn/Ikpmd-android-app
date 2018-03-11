package com.example.bas_d.simpledungeon.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.Window;

import com.example.bas_d.simpledungeon.CreatureManager;
import com.example.bas_d.simpledungeon.GameEngine;
import com.example.bas_d.simpledungeon.database.DatabaseHelper;
import com.example.bas_d.simpledungeon.input.InputDetector;
import com.example.bas_d.simpledungeon.model.terrain.Map;
import com.example.bas_d.simpledungeon.services.ImageService;

public class GameActivity extends AppCompatActivity {

    private DrawingCanvas drawingCanvas;
    private SurfaceHolder surfaceHolder;
    private DatabaseHelper databaseHelper;
    private GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameEngine.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameEngine.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        gameEngine.stop();
        //SAVING DATA
    }

    private void startUp() {
        new ImageService(getResources());
        this.databaseHelper = DatabaseHelper.getHelper(this);
        gameEngine = new GameEngine(new CreatureManager(), databaseHelper, getResources());
        setupCanvas(gameEngine);
    }

    private void setupCanvas(GameEngine gameEngine) {
        drawingCanvas = new DrawingCanvas(this, gameEngine);
        this.setContentView(drawingCanvas);
        surfaceHolder = drawingCanvas.getHolder();
        surfaceHolder.addCallback(drawingCanvas);
    }

}
