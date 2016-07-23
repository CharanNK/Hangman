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
public class GameLose extends AppCompatActivity {
    private TextView lose_sorry;
    private TextView you_lose;
    private FloatingActionButton button_Home;
    private FloatingActionButton button_next;
    private TextView lose_answer;
    int curlevel =0;
    String answer = null;
    AdView mAdView;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,CategorySelect.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_lose);
        Bundle extras = getIntent().getExtras();

        lose_sorry = (TextView)findViewById(R.id.lose_sorry);
        you_lose = (TextView)findViewById(R.id.you_lose);
        lose_answer = (TextView)findViewById(R.id.lose_answer);

        Typeface appNametypeface = Typeface.createFromAsset(getAssets(), "blackjack.ttf");
        lose_sorry.setTypeface(appNametypeface);
        you_lose.setTypeface(appNametypeface);

        curlevel = extras.getInt("level");
        answer = extras.getString("answer");

        lose_answer.setText("Answer :  "+answer);

        button_Home = (FloatingActionButton)findViewById(R.id.button_Home);
        button_next = (FloatingActionButton)findViewById(R.id.button_next);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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
