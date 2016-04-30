package com.charan.hangman;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;

public class MainActivity extends AppCompatActivity {
    private KenBurnsView swag;
    private Button play_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swag=(KenBurnsView)findViewById(R.id.swag);
        play_button = (Button)this.findViewById(R.id.play_button);
        if(Build.VERSION.SDK_INT>=21) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
        AccelerateDecelerateInterpolator ACCELERATE_DECELERATE = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(6000, ACCELERATE_DECELERATE);
        swag.setTransitionGenerator(generator); //set new transition on kenburns view

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CategorySelect.class);
                startActivity(intent);

                overridePendingTransition(R.anim.push_left_out,R.anim.appear);
            }
        });

    }
}