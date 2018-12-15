package com.nowater;

import android.content.Context;

public class Typewriter extends Device{

    public Typewriter(Context context){
        bitmapId = R.drawable.mimpc_luigi_big;

        stagesAmount = 9;
        stageCost = 5;
        points = 0;
        clickPower = 5;
        size = 1;

        init(context);
    }
}
