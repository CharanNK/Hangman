package com.charan.hangman;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class GameplayActivity extends AppCompatActivity {
    Button buttonHome;
    ImageView imageHanging;
    int i = 0;
    Runnable runnable;
    public static Integer[] mThumbIds = {

            R.drawable.hang0,R.drawable.hang1,R.drawable.hang2,R.drawable.hang3,
            R.drawable.hang5,R.drawable.hang6,R.drawable.hang7,R.drawable.hang9,R.drawable.hang10

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        buttonHome = (Button) findViewById(R.id.buttonHome);
        imageHanging = (ImageView)findViewById(R.id.imageHanging);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Change the image after some delay continuously and stop after all images
                final Handler handler = new Handler();
                runnable = new Runnable() {
                    int i=0;
                    public void run() {
                        if(i<mThumbIds.length)
                        {
                            imageHanging.setImageResource(mThumbIds[i]);
                            i++;

                        }
                        else {
                            //stop the handler
                            handler.removeCallbacks(runnable);
                        }
                        handler.postDelayed(this, 400);
                    }
                };
                handler.postDelayed(runnable, 1000); //for initial delay..
            }
        });

    }

}
