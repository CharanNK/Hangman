package com.charan.hangman;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by CHARAN on 7/14/2016.
 */
public class GameWin extends AppCompatActivity {
    private TextView win_congrats;
    private TextView you_won;
    private FloatingActionButton button_Home;
    private FloatingActionButton button_next;
    int curlevel =0;
    AdView mAdView;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,CategorySelect.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_win);
        Bundle extras = getIntent().getExtras();

        win_congrats = (TextView)findViewById(R.id.win_congrats);
        you_won = (TextView)findViewById(R.id.you_won);

        Typeface appNametypeface = Typeface.createFromAsset(getAssets(), "blackjack.ttf");
        win_congrats.setTypeface(appNametypeface);
        you_won.setTypeface(appNametypeface);

        button_Home = (FloatingActionButton)findViewById(R.id.button_Home);
        button_next = (FloatingActionButton)findViewById(R.id.button_next);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        curlevel = extras.getInt("level");

        button_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CategorySelect.class);
                startActivity(intent);
            }
        });

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GameplayActivity.class);
                intent.putExtra("level", curlevel);
                startActivity(intent);
            }
        });


    }
}
