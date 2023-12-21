package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Lorraine Low
 * @version Android Studio Hedgehog 2023.1.1
 */

public class GameController {
    private GameState gameState;
    private GameView gameView;
    private String gameMode;
    private static final String CELLS_KEY = "CELLS";
    private static final String PLAYER_TURN_KEY = "PLAYER_TURN";
    private static final String ROUND_KEY = "ROUND";
    private static final String GAME_ENDS_KEY = "GAME_ENDS";
    private static final String MESSAGE_KEY = "MESSAGE";
    private static final String LAST_MATRIX_SIZE_KEY = "LAST_MATRIX_SIZE";

    public GameController(GameState gameState, GameView gameView, String gameMode) {
        this.gameState = gameState;
        this.gameView = gameView;
        this.gameMode = gameMode;
    }

    public Bundle saveGameState() {
        Bundle gameStateBundle = new Bundle();
        gameStateBundle.putSerializable(CELLS_KEY, gameState.getCells());
        gameStateBundle.putInt(PLAYER_TURN_KEY, gameState.getPlayerTurn());
        gameStateBundle.putInt(ROUND_KEY, gameState.getRound());
        gameStateBundle.putBoolean(GAME_ENDS_KEY, gameState.isGameEnd());
        gameStateBundle.putString(MESSAGE_KEY, gameView.getMessage().getText().toString());
        gameStateBundle.putInt(LAST_MATRIX_SIZE_KEY, gameState.getMatrixSize());
        return gameStateBundle;
    }

    public void restoreGameState(Bundle savedInstanceState) {
        Object savedCells = savedInstanceState.getSerializable(CELLS_KEY);
        if (savedCells instanceof Matrix) {
            gameState.setCells((Matrix) savedCells);
        }
        gameState.setPlayerTurn(savedInstanceState.getInt(PLAYER_TURN_KEY));
        gameState.setRound(savedInstanceState.getInt(ROUND_KEY));
        gameState.setGameEnd(savedInstanceState.getBoolean(GAME_ENDS_KEY));
        gameView.setMessage(savedInstanceState.getString(MESSAGE_KEY));
        int lastMatrixSize = savedInstanceState.getInt(LAST_MATRIX_SIZE_KEY, -1);

        if (lastMatrixSize > gameState.getMatrixSize()) {
            resetGame();
            gameView.restoreCellValue(false, gameState.getMatrixSize(), gameState.getCells());
        } else {
            gameView.restoreCellValue(true, gameState.getMatrixSize(), gameState.getCells());
        }
    }

    public void resetGame() {
        gameState.setCells(new Matrix());
        gameState.setRound(0);
        gameState.setPlayerTurn(1);
        gameState.setGameEnd(false);
        gameView.setMessage("Player 1 Turn");
        gameView.resetButtons(gameState.getMatrixSize());
    }

    private void executeGame(View view){
        Button button = (Button)view;

        if (button.getText().toString().isEmpty()) {
            gameState.setRound(gameState.getRound() + 1);
            button.setText(""+gameState.getPlayerTurn());
            String buttonIdString = view.getResources().getResourceEntryName(view.getId());
            int buttonNumber = Integer.parseInt(buttonIdString.replace("button", "")) - 1;
            int rowIndex = buttonNumber / gameState.getMatrixSize();
            int colIndex = buttonNumber % gameState.getMatrixSize();

            gameState.setCell(rowIndex, colIndex, gameState.getPlayerTurn());
            int winner = gameState.returnWinner();

            handleGameResult(winner);

            if (!gameState.isGameEnd()) {
                handlePlayerTurn();
            }
        }
    }

    public void handlePlayerTurn() {
        int playerTurn = gameState.togglePlayerTurn();
        String messageText = "Player " + playerTurn + " Turn";
        gameView.setMessage(messageText);

        if ("computer".equals(gameMode) && playerTurn == 2 && !gameState.isGameEnd()) {
            computerMove();
        }
    }

    private void handleGameResult(int winner) {
        String endGameMessage = gameState.returnGameResult(winner, gameState.getRound(), gameState.getMatrixSize());
        gameView.setMessage(endGameMessage);
    }

    private void computerMove() {
        List<Integer> emptyCells = new ArrayList<>();
        for (int row = 0; row < gameState.getMatrixSize(); row++) {
            for (int col = 0; col < gameState.getMatrixSize(); col++) {
                if (gameState.getCell(row, col) == 0) {
                    emptyCells.add((row * gameState.getMatrixSize()) + col);
                }
            }
        }
        if (!emptyCells.isEmpty()) {
            Random random = new Random();
            int randomCell = emptyCells.get(random.nextInt(emptyCells.size()));
            gameView.performButtonClick(randomCell);
        }
    }

    public void cellClicked(View view) {
        if (!gameState.isGameEnd()) {
            executeGame(view);
        }
    }

}