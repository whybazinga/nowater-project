package com.nowater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    public static int clicks = 0;
    public static int pages = 0;
    public static Device device = new Typewriter(R.drawable.mimpc_luigi_big, 9, "Luigi", 0);
    TextView clicksView;
    TextView pagesView;
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameView = new GameView(this); // создаём gameView
        clicksView = (TextView) findViewById(R.id.clicksView);
        pagesView = (TextView) findViewById(R.id.pagesView);
        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout); // находим gameLayout
        gameView.setOnClickListener(this);
        gameLayout.addView(gameView); // и добавляем в него gameView
        clicksView.setText(getString(R.string.game__text_clicks) + ": " + Integer.toString(clicks));
        pagesView.setText(getString(R.string.game__text_pages) + ": " + Integer.toString(pages));
    }

    public void onClick(View view) {
        clicks++;
        clicksView.setText(getString(R.string.game__text_clicks) + ": " + Integer.toString(clicks));
        pagesView.setText(getString(R.string.game__text_pages) + ": " + Integer.toString(pages));
    }
}
