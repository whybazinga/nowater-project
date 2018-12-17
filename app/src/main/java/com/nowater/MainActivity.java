package com.nowater;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button playButton;
//    Button scoreButton;
    ImageView appLogo;

    public static final String MESSAGE_NAME = "com.nowater.extra.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = (Button) findViewById(R.id.Main_PlayButton);
        playButton.setOnClickListener(this);
//        scoreButton = (Button) findViewById(R.id.Main_ScoreButton);
        appLogo = (ImageView) findViewById(R.id.Main_AppLogo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Main_PlayButton:

                if (GameActivity.isActive) {
                    Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(gameActivityIntent);
                } else {
                    LayoutInflater layoutInflater = getLayoutInflater();
                    final View view = layoutInflater.inflate(R.layout.dialog_name, null);
                    final EditText name = (EditText) view.findViewById(R.id.Main_NameDialog);
                    new AlertDialog.Builder(this, R.style.DialogTheme)
                            .setIcon(R.mipmap.ic_launcher)
                            .setTitle(getString(R.string.main__dialog_text_title))
                            .setMessage(getString(R.string.main__dialog_text_message))
                            .setView(view)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String playerName = name.getText().toString();
                                    if (!"".equals(playerName)) {
                                        Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                                        gameActivityIntent.putExtra(MESSAGE_NAME, playerName);
                                        startActivity(gameActivityIntent);
                                    } else {
                                        Toast.makeText(MainActivity.this, getString(R.string.main__toast_empty_name), Toast.LENGTH_LONG).show();
                                    }
                                }
                            })
                            .setNegativeButton(android.R.string.cancel, null)
                            .create()
                            .show();
                }
                break;
//            case R.id.Main_ScoreButton:
// handle button B click;
//                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        appLogo.setImageBitmap(BitmapFactory.decodeResource(appLogo.getResources(), R.drawable.logo));
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((BitmapDrawable) appLogo.getDrawable()).getBitmap().recycle();
        appLogo.setImageBitmap(null);
    }
}
