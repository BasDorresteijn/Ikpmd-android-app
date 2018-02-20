package com.example.bas_d.simpledungeon.views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.bas_d.simpledungeon.GameEngine;
import com.example.bas_d.simpledungeon.RenderThread;
import com.example.bas_d.simpledungeon.input.InputDetector;

public class DrawingCanvas extends SurfaceView implements SurfaceHolder.Callback {

    private RenderThread render;
    private GameEngine gameEngine;

    public DrawingCanvas(Context context, GameEngine gameEngine) {
        super(context);
        this.gameEngine = gameEngine;
        InputDetector inputDetector = new InputDetector(this);
        this.gameEngine.setInputDetector(inputDetector);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Canvas c = surfaceHolder.lockCanvas();
        gameEngine.setWidth(c.getWidth());
        gameEngine.setHeight(c.getHeight());
        surfaceHolder.unlockCanvasAndPost(c);
        if(render == null) {
            render = new RenderThread(surfaceHolder, gameEngine);
            render.start();
        }
        else {
            render.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

}
