package com.nowater;

import android.graphics.Bitmap;

public class Sprite {
    private int framesAmount;
    private int currentFrame;
    private int frameSizeX;
    private int frameSizeY;
    protected Bitmap sprite;

    public Sprite(Bitmap sprite, int frameSizeX, int frameSizeY, int framesAmount, int startingFrame) {
        this.currentFrame = startingFrame;
        this.sprite = sprite;
        this.frameSizeX = frameSizeX;
        this.frameSizeY = frameSizeY;
        this.framesAmount = framesAmount;
    }

    public Bitmap getCurrentFrame() {
        return Bitmap.createBitmap(sprite, currentFrame * frameSizeX, 0, frameSizeX, frameSizeY);
    }

    public void nextFrame() {
        currentFrame = (currentFrame + 1) % framesAmount;
    }

    public boolean isLastFrame() {
        return currentFrame + 1 >= framesAmount;
    }

}
