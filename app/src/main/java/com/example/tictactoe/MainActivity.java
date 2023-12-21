package com.example.tictactoe;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Lorraine Low
 * @version Android Studio Hedgehog 2023.1.1
 */

public class MainActivity extends AppCompatActivity {
    private GameState gameState;
    private GameController gameController;
    private GameView gameView;
    private String gameMode;
    private static final int PORTRAIT_MATRIX_SIZE = 3;
    private static final int LANDSCAPE_MATRIX_SIZE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();

        gameMode = getIntent().getStringExtra("gameMode");
        TextView message = findViewById(R.id.message);

        setGameComponents(message);

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        } else {
            gameController.resetGame();
        }
    }

    private void setContentView() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape);
        }
    }

    private void setGameComponents(TextView message) {
        int orientation = getResources().getConfiguration().orientation;
        int matrixSize = orientationMatrixSize(orientation);

        gameState = new GameState(matrixSize);
        gameView = new GameView(this, this, message);
        gameController = new GameController(gameState, gameView, gameMode);

        gameView.restoreCellValue(false, matrixSize, gameState.getCells());
        gameState.setMatrixSize(matrixSize);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("GAME_STATE", gameController.saveGameState());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        gameController.restoreGameState(savedInstanceState.getBundle("GAME_STATE"));
    }

    public void cellClicked(View view) {
        gameController.cellClicked(view);
    }

    private int orientationMatrixSize(int orientation) {
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            return PORTRAIT_MATRIX_SIZE;
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return LANDSCAPE_MATRIX_SIZE;
        } else {
            return PORTRAIT_MATRIX_SIZE;
        }
    }

}