package com.nowater;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

class PagesCounter {
    private int pages = 0;

    public synchronized void set(int amount) {
        pages=amount;
    }

    public synchronized void add(int amount) {
        pages+=amount;
    }

    public synchronized void sub(int amount) {
        pages-=amount;
    }

    public synchronized int amount() {
        return pages;
    }
}

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String MESSAGE_CLICKS = "com.nowater.extra.CLICKS";
    public static final String MESSAGE_PAGES = "com.nowater.extra.PAGES";

    public static int clicks;
    public static PagesCounter pages = new PagesCounter();

    String playerName;
    public static Device device;

    TextView playerNameView;
    TextView pagesView;
    GameView gameView;
//    Button shopButton;
    Button finishButton;

    public static boolean isActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        if (!isActive) {
            startNewGame(getIntent());
            playerName = intent.getStringExtra(MainActivity.MESSAGE_NAME);
        }
        playerNameView = (TextView) findViewById(R.id.Game_PlayerName);
        playerNameView.setText(playerName);

        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.Game_Layout); // находим gameLayout
        gameView = new GameView(this); // создаём gameView
        gameView.setOnClickListener(this);
        gameLayout.addView(gameView); // и добавляем в него gameView

//        shopButton = (Button) findViewById(R.id.Game_ShopButton);
//        shopButton.setOnClickListener(this);
        finishButton = (Button) findViewById(R.id.Game_FinishButton);
        finishButton.setOnClickListener(this);

        pagesView = (TextView) findViewById(R.id.Game_PagesView);
        pagesView.setText(getString(R.string.game__text_pages) + ": " + Integer.toString(pages.amount()));
    }

    public static void startNewGame(Intent intent) {
        device = new Typewriter(R.drawable.typewriter, 6, "NowaterTypewriter", 0);
        clicks = 0;
        pages.set(0);
        isActive = true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.Game_ShopButton:
//                break;
            case R.id.Game_FinishButton:
                new AlertDialog.Builder(this, R.style.DialogTheme)
                        .setTitle(getString(R.string.game__dialog_text_title))
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage(getString(R.string.game__dialog_text_message))
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                isActive = false;
                                Toast.makeText(GameActivity.this, getString(R.string.game__toast_text_finish), Toast.LENGTH_LONG).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent resultsActivityIntent = new Intent(GameActivity.this, ResultsActivity.class);
                                        resultsActivityIntent.putExtra(MainActivity.MESSAGE_NAME, playerName);
                                        resultsActivityIntent.putExtra(GameActivity.MESSAGE_CLICKS, "" + clicks);
                                        resultsActivityIntent.putExtra(GameActivity.MESSAGE_PAGES, "" + pages.amount());
                                        GameActivity.this.finish();
                                        startActivity(resultsActivityIntent);
                                    }
                                }, 1000);

                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
                break;
            default:
                clicks++;
                pagesView.setText(getString(R.string.game__text_pages) + ": " + Integer.toString(pages.amount()));
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
