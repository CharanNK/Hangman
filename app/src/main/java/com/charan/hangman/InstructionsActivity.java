package com.charan.hangman;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by CHARAN on 7/15/2016.
 */
public class InstructionsActivity extends AppCompatActivity {
    private TextView instructions_title;
    private TextView instructions;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        instructions_title = (TextView)findViewById(R.id.instruction_title);
        instructions = (TextView)findViewById(R.id.instructions);

        Typeface instNametypeface = Typeface.createFromAsset(getAssets(), "blackjack.ttf");
        instructions_title.setTypeface(instNametypeface);

        Typeface instTypeface = Typeface.createFromAsset(getAssets(), "milfont.ttf");
        instructions.setTypeface(instTypeface);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void goToCategory(View view) {
        Intent intent = new Intent(this,CategorySelect.class);
        startActivity(intent);
    }
}
