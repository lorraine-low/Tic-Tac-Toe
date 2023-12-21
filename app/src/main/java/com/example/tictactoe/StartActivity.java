package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Lorraine Low
 * @version Android Studio Hedgehog 2023.1.1
 */

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void playWithComputer(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("gameMode", "computer");
        startActivity(intent);
        Log.i("info", "The user selected computer mode");
    }

    public void playWithPlayer(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("gameMode", "player");
        startActivity(intent);
        Log.i("info", "The user selected player mode");
    }
}
