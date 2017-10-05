package com.ach.alex.bullsandcowsreloaded;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    private Button startButton;
    private Button instructionsButton;
    private Button highscoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // start
        startButton = (Button) findViewById(R.id.main_start);
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainScreen.this, PlayScreen.class);
                MainScreen.this.startActivity(myIntent);
            }
        });

        // instructions
        instructionsButton = (Button) findViewById(R.id.main_instructions);
        instructionsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainScreen.this, InstructionsScreen.class);
                MainScreen.this.startActivity(myIntent);
            }
        });

        // highscores
        highscoreButton = (Button) findViewById(R.id.main_highscore);
        highscoreButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainScreen.this, HighscoreScreen.class);
                MainScreen.this.startActivity(myIntent);
            }
        });
    }


}
