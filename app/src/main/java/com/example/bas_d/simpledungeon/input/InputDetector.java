package com.example.bas_d.simpledungeon.input;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.bas_d.simpledungeon.services.ImageService;
import com.example.bas_d.simpledungeon.views.DrawingCanvas;

public class InputDetector {

    private DrawingCanvas canvas;
    private int width, height;
    private Paint p;

    public InputDetector(DrawingCanvas canvas) {
        this.canvas = canvas;
        p = new Paint();
        setupListeners();
    }

    public void setupListeners() {
        canvas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
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
    }

    private void checkControls(float x, float y) {
        int controlsWidth = ImageService.controlsImage.getWidth();
        int controlsPart = controlsWidth/3;
        if(x < width && x > width - controlsWidth + 2*controlsPart && y < height && y > height - controlsWidth) {
            Inputs.right = true;
        }
        if(x < width - 2*controlsPart && x > width - controlsWidth && y < height && y > height - controlsWidth) {
            Inputs.left = true;
        }
        if(x < width && x > width - controlsWidth && y < height - 2*controlsPart && y > height - controlsWidth) {
            Inputs.down = true;
        }
        if(x < width && x > width - controlsWidth && y < height && y > height - controlsWidth + 2*controlsPart) {
            Inputs.up = true;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(ImageService.controlsImage, this.width - ImageService.controlsImage.getWidth(),
                this.height - ImageService.controlsImage.getHeight(), p);
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
