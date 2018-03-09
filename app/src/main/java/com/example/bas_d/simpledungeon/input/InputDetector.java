package com.example.bas_d.simpledungeon.input;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.bas_d.simpledungeon.GameEngine;
import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.services.ImageService;
import com.example.bas_d.simpledungeon.views.DrawingCanvas;

public class InputDetector {

    private DrawingCanvas canvas;
    private GameEngine gameEngine;
    private int width, height;
    private Paint p;

    public InputDetector(GameEngine gameEngine, DrawingCanvas canvas) {
        this.gameEngine = gameEngine;
        this.canvas = canvas;
        this.gameEngine.setInputDetector(this);
        p = new Paint();
        p.setColor(Color.BLACK);
        setupListeners();
    }

    public void setupListeners() {
        canvas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        //MULTITOUCH
                        for(int i = 0; i < event.getPointerCount(); i++) {
                            checkControls(event.getX(i), event.getY(i));
                        }
                        return true;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        //MULTITOUCH
                        for(int i = 0; i < event.getPointerCount(); i++) {
                            checkControls(event.getX(i), event.getY(i));
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        resetControls();
                        //MULTITOUCH
                        for(int i = 0; i < event.getPointerCount(); i++) {
                            checkControls(event.getX(i), event.getY(i));
                        }
                        return false;
                    case MotionEvent.ACTION_POINTER_UP:
                        resetControls();
                        return false;
                    case MotionEvent.ACTION_UP:
                        resetControls();
                        return false;
                    default: return false;
                }
            }
        });
    }

    private void resetControls() {
        Inputs.right = false;
        Inputs.down = false;
        Inputs.up = false;
        Inputs.left = false;
        Inputs.a = false;
    }

    private void checkControls(float x, float y) {
        int controlsWidth = ImageService.controlsImage.getWidth();
        int controlsheight = ImageService.controlsImage.getHeight();
        int controlsPart = controlsWidth/3;
        //DPAD
        if(x < controlsWidth && y > height - controlsWidth) {
            if(x < controlsWidth && x > controlsWidth - controlsPart && y < height && y > height - controlsheight) {
                Inputs.right = true;
            }
            if(x < controlsWidth - 2*controlsPart && x > 0 && y < height && y > height - controlsheight) {
                Inputs.left = true;
            }
            if(x > 0 && x < controlsWidth && y < height - 2*controlsPart && y > height - controlsheight) {
                Inputs.up = true;
            }
            if(x > 0 && x < controlsWidth && y < height && y > height - controlsheight + 2*controlsPart) {
                Inputs.down = true;
            }
        }
        //A button
        if(x > width - controlsPart && x < width && y > height - height/4 && y < height - height/4 + controlsPart) {
            Inputs.a = true;
        }

    }

    public void draw(Canvas canvas) {
        canvas.drawRect(0, this.height - ImageService.controlsImage.getHeight() - 16, width, height, p);
        canvas.drawBitmap(ImageService.controlsImage, 0,
                this.height - ImageService.controlsImage.getHeight(), p);
        canvas.drawBitmap(ImageService.aImage, width - FixedValues.CONTROLSWIDTH/3, this.height - this.height/4, p);
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
