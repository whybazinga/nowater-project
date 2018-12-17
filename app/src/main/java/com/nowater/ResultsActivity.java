package com.nowater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView playerNameView;
    TextView totalClicksView;
    TextView totalPagesView;
    TextView mainTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();

        playerNameView = (TextView) findViewById(R.id.Results_PlayerName);
        playerNameView.setText(intent.getStringExtra(MainActivity.MESSAGE_NAME));

        totalClicksView = (TextView) findViewById(R.id.Results_TotalClicks);
        totalClicksView.setText(getString(R.string.results__text_total_clicks) + ": " + intent.getStringExtra(GameActivity.MESSAGE_CLICKS));

        totalPagesView = (TextView) findViewById(R.id.Results_TotalPages);
        String totalPages = intent.getStringExtra(GameActivity.MESSAGE_PAGES);
        totalPagesView.setText(getString(R.string.results__text_total_pages) + ": " + totalPages);

        mainTextView = (TextView) findViewById(R.id.Results_Text);
        mainTextView.setText(getString(R.string.results__text_main_teacher) + ": " + (Integer.parseInt(totalPages) >= 10 ? getString(R.string.results__text_main_good) : getString(R.string.results__text_main_bad)));

    }
}
