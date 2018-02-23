package com.example.bas_d.simpledungeon;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

public class RenderThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private boolean isOnRun;
    private Paint backgroundPaint;
    private final long DELAY = 1000/30;
    private GameEngine gameEngine;

    public RenderThread(SurfaceHolder surfaceHolder, GameEngine gameEngine) {
        this.surfaceHolder = surfaceHolder;
        this.gameEngine = gameEngine;
        isOnRun = true;
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);
    }

    @Override
    public void run()
    {
        //Looping until the boolean is false
        while (isOnRun)
        {
            //Updates the game objects buisiness logic
            gameEngine.update();

            //locking the canvas
            Canvas canvas = surfaceHolder.lockCanvas();

            if (canvas != null)
            {
                //Clears the screen with black paint and draws
                //object on the canvas
                synchronized (surfaceHolder)
                {
                    canvas.drawRect(0, 0, canvas.getWidth(),canvas.getHeight(), backgroundPaint);
                    gameEngine.draw(canvas);
                }
                //unlocking the Canvas
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
            //delay time
            try
            {
                Thread.sleep(DELAY);
            }
            catch (InterruptedException ex)
            {
                Log.d("Error gameThread", ex.toString());
            }
        }
    }

    public boolean isOnRun() {
        return isOnRun;
    }

    public void setOnRun(boolean onRun) {
        isOnRun = onRun;
    }
}
