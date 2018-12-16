package com.nowater;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    public static int clicks = 0;
    public static int pages = 0;

    public static String playerName;
    public static Device device = new Typewriter(R.drawable.mimpc_luigi_big, 9, "Luigi", 0);

    TextView playerNameView;
    TextView pagesView;
    GameView gameView;
    Button shopButton;
    Button finishButton;

    boolean isFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();

        playerNameView = (TextView) findViewById(R.id.Game_PlayerName);
        playerName = intent.getStringExtra(MainActivity.MESSAGE_NAME);
        playerNameView.setText(playerName);

        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.Game_Layout); // находим gameLayout
        gameView = new GameView(this); // создаём gameView
        gameView.setOnClickListener(this);
        gameLayout.addView(gameView); // и добавляем в него gameView

        shopButton = (Button)findViewById(R.id.Game_ShopButton);
        shopButton.setOnClickListener(this);
        finishButton = (Button)findViewById(R.id.Game_FinishButton);
        finishButton.setOnClickListener(this);

        pagesView = (TextView) findViewById(R.id.Game_PagesView);
        pagesView.setText(getString(R.string.game__text_pages) + ": " + Integer.toString(pages));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Game_ShopButton:
                break;
            case R.id.Game_FinishButton:
                new AlertDialog.Builder(this, R.style.DialogTheme)
                        .setTitle(getString(R.string.game__dialog_text_title))
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage(getString(R.string.game__dialog_text_message))
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                isFinished = true;
                                Toast.makeText(GameActivity.this, getString(R.string.game__toast_text_finish), Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
                break;
            default:
                clicks++;
                pagesView.setText(getString(R.string.game__text_pages) + ": " + Integer.toString(pages));
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.stopGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
    }

}
