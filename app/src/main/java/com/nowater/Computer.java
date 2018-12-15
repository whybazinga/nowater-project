package com.nowater;

public class Computer extends Device {

    public Computer(int bitmapId, int stagesAmount, String name, int price) {
        this.bitmapId = bitmapId; //R.drawable.mimpc_luigi_big;
        this.stagesAmount = stagesAmount;//9;
        stageCost = 3;
        points = 0;
        clickPower = 1;
        size = 1;

        this.name = name;
        this.price = price;
//        init(context, startingStage);
    }
}
