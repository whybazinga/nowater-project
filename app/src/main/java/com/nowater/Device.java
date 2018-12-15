package com.nowater;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Device {
    protected int stagesAmount;
    protected int stageCost;
    protected int handledClicks;
    protected int points;
    protected int clickPower;
    protected int bitmapId;
    protected double size;
    Sprite pic;

    void init(Context context) { // сжимаем картинку до нужных размеров
        Bitmap p = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        int frameSizeX = (int)(size * GameView.sizeX);
        int frameSizeY = (int)(size * GameView.sizeY);
        Bitmap scaledPic = Bitmap.createScaledBitmap(
                p,
                frameSizeX * stagesAmount,
                frameSizeY,
                false
        );
        pic = new Sprite(scaledPic, frameSizeX, frameSizeY, stagesAmount);
    }

    void update(){
        int unhandledClicks = GameActivity.clicks - handledClicks;
        handledClicks = GameActivity.clicks;
        if(unhandledClicks > 0){
            points += unhandledClicks * clickPower;
        }
        if(points >= stageCost){
            points -= stageCost;
            if(pic.nextFrame()) {
                GameActivity.pages++;
            }
        }
    }

    void draw(Paint paint, Canvas canvas){ // рисуем картинку
        canvas.drawBitmap(pic.getCurrentFrame(), 0, 0, paint);
    }

}
