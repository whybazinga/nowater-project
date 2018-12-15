package com.nowater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    public static int clicks = 0;
    public static int pages = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GameView gameView = new GameView(this); // создаём gameView

        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout); // находим gameLayout
        gameView.setOnClickListener(this);
        gameLayout.addView(gameView); // и добавляем в него gameView
    }

    public void onClick(View view) {
        clicks++;
        TextView textView = (TextView) findViewById(R.id.clicksView);
        textView.setText(Integer.toString(clicks));
    }
}
