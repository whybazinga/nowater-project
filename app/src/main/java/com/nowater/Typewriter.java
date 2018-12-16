package com.nowater;

public class Typewriter extends Device {

    public Typewriter(int bitmapId, int stagesAmount, String name, int price) {
        this.bitmapId = bitmapId; //R.drawable.mimpc_luigi_big;
        this.stagesAmount = stagesAmount;//9;
        stageCost = 5;
        points = 0;
        clickPower = 1;
        size = 0.8;
        pic = null;

        this.name = name;
        this.price = price;
    }
}
