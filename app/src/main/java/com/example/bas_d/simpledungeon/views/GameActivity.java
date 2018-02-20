package com.example.bas_d.simpledungeon.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;

import com.example.bas_d.simpledungeon.CreatureManager;
import com.example.bas_d.simpledungeon.GameEngine;
import com.example.bas_d.simpledungeon.input.InputDetector;
import com.example.bas_d.simpledungeon.services.ImageService;

public class GameActivity extends AppCompatActivity {

    private DrawingCanvas drawingCanvas;
    private SurfaceHolder surfaceHolder;
    private GameEngine gameEngine;
    private ImageService imageService;
    private CreatureManager creatureManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageService = new ImageService(getResources());
        creatureManager = new CreatureManager();
        gameEngine = new GameEngine(creatureManager);
        setupCanvas(gameEngine);
    }

    private void setupCanvas(GameEngine gameEngine) {
        drawingCanvas = new DrawingCanvas(this, gameEngine);
        this.setContentView(drawingCanvas);
        surfaceHolder = drawingCanvas.getHolder();
        surfaceHolder.addCallback(drawingCanvas);
    }
}
