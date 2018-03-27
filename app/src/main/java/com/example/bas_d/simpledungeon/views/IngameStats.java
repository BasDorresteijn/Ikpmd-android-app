package com.example.bas_d.simpledungeon.views;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.bas_d.simpledungeon.R;
import com.example.bas_d.simpledungeon.model.FixedValues;
import com.example.bas_d.simpledungeon.model.creatures.Player;
import com.example.bas_d.simpledungeon.services.ImageService;

public class IngameStats {

    private Player player;
    private Paint pCooldown, pReady, pBlack, pWhiteSmall, pWhiteLarge;
    private int maxWidth, maxHeight;
    private final int STATSHEIGHT = FixedValues.STATSHEIGHT;
    private Resources resources;

    public IngameStats(Player player, Resources resources) {
        this.resources = resources;
        this.player = player;
        pCooldown = new Paint();
        pCooldown.setColor(Color.BLUE);
        pReady = new Paint();
        pReady.setColor(Color.RED);
        pBlack = new Paint();
        pBlack.setColor(Color.BLACK);
        pWhiteSmall = new Paint();
        pWhiteSmall.setColor(Color.WHITE);
        pWhiteSmall.setTextSize(32);
        pWhiteLarge = new Paint();
        pWhiteLarge.setColor(Color.WHITE);
        pWhiteLarge.setTextSize(64);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(0,0, maxWidth, STATSHEIGHT, pBlack);
        drawCooldown(canvas);
        drawHealth(canvas);
        drawScore(canvas);
    }

    private void drawScore(Canvas canvas) {
        String score = String.valueOf(player.getScore());
        for(int i = score.length(); i < 7; i++) {
            score = "0" + score;
        }
        canvas.drawText(score, maxWidth/4, STATSHEIGHT, pWhiteLarge);
    }

    private void drawCooldown(Canvas canvas) {
        double maxCooldown = player.getSword().getCooldown() + player.getSword().getUseTime();
        long now = System.currentTimeMillis();
        double cooldown = (player.getSword().getLastUsed() + player.getSword().getCooldown() + player.getSword().getUseTime()) - now;
        if(cooldown < 0) {
            cooldown = 0;
        }
        double procent = cooldown/maxCooldown;
        canvas.drawRect(maxWidth/3*2, STATSHEIGHT/2, maxWidth, STATSHEIGHT - 4, pReady);
        canvas.drawRect( maxWidth/3*2 + (float) ((maxWidth/3) * (1 - procent)), STATSHEIGHT/2, maxWidth/3*2, STATSHEIGHT - 3, pCooldown);
        canvas.drawText(resources.getString(R.string.cooldown), maxWidth/3*2, STATSHEIGHT/2, pWhiteSmall);
    }

    private void drawHealth(Canvas canvas) {
        canvas.drawBitmap(ImageService.heart, 0, 0, null);
        canvas.drawText(String.valueOf(player.getHealth()), STATSHEIGHT, STATSHEIGHT, pWhiteLarge);
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

}
