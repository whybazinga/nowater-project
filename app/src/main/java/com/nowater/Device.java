package com.nowater;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Device {
    public String name;
    public int price;

    protected int stagesAmount;
    protected int stageCost;
    protected int handledClicks;
    protected int points;
    protected int clickPower;
    protected int bitmapId;
    protected double size;
    Sprite pic;

    public void init(Context context, int startingStage) { // сжимаем картинку до нужных размеров
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap p = BitmapFactory.decodeResource(context.getResources(), bitmapId, options);
        int frameSizeX = (int) (size * GameView.sizeX);
        int frameSizeY = (int) (size * GameView.sizeY);
        Bitmap scaledPic = Bitmap.createScaledBitmap(
                p,
                frameSizeX * stagesAmount,
                frameSizeY,
                false
        );
        pic = new Sprite(scaledPic, frameSizeX, frameSizeY, stagesAmount, startingStage);
    }

    void update() {
        int unhandledClicks = GameActivity.clicks - handledClicks;
        handledClicks = GameActivity.clicks;
        if (unhandledClicks > 0) {
            points += unhandledClicks * clickPower;
        }
        if (points >= stageCost) {
            points -= stageCost;
            if (pic.isLastFrame()) {
                GameActivity.pages.add(1);
            }
            pic.nextFrame();
        }
    }

    void draw(Paint paint, Canvas canvas) { // рисуем картинку
        canvas.drawBitmap(pic.getCurrentFrame(),
                (int) (Math.abs(GameView.sizeX - size * GameView.sizeX)) / 2,
                (int) (Math.abs(GameView.sizeY - size * GameView.sizeY)) / 2,
                paint);
    }

}
