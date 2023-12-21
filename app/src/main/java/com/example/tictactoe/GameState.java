package com.example.tictactoe;

/**
 * @author Lorraine Low
 * @version Android Studio Hedgehog 2023.1.1
 */

public class GameState {
    private Matrix cells;
    private int matrixSize;
    private int round;
    private int playerTurn;
    private boolean gameEnd;

    public GameState(int matrixSize) {
        this.matrixSize = matrixSize;
        this.cells = new Matrix();
    }

    public Matrix getCells() { return cells; }

    protected void setCells(Matrix cells) { this.cells = cells; }

    public int getCell(int row, int col) { return cells.get(row, col); }

    protected void setCell(int row, int col, int playerNumber) { cells.set(row, col, playerNumber); }
    
    public int getMatrixSize() { return matrixSize; }

    protected void setMatrixSize(int matrixSize) { this.matrixSize = matrixSize; }

    public int getRound() { return round; }

    protected void setRound(int round) { this.round = round; }

    public int getPlayerTurn() { return playerTurn; }

    protected void setPlayerTurn(int playerTurn) { this.playerTurn = playerTurn; }

    public boolean isGameEnd() { return gameEnd; }

    protected void setGameEnd(boolean gameEnd) { this.gameEnd = gameEnd; }

    public String returnGameResult(int winner, int round, int matrixSize) {
        String gameResult = "";

        if(winner == 1){
            gameResult = "Player 1 Wins!";
            this.gameEnd = true;
        }
        else if(winner == 2){
            gameResult = "Player 2 Wins!";
            this.gameEnd = true;
        }
        else if(round == matrixSize * matrixSize){
            gameResult = "Draw!";
            this.gameEnd = true;
        }

        return gameResult;
    }

    public int returnWinner(){
        if(checkWinner(1)){
            return 1;
        }
        if(checkWinner(2)){
            return 2;
        }
        return 0;
    }

    private boolean checkWinner(int playerNumber) {
        for (int i = 0; i < matrixSize; i++) {
            if (checkRow(i, playerNumber) || checkColumn(i, playerNumber)) {
                return true;
            }
        }
        return checkDiagonals(playerNumber);
    }

    private boolean checkRow(int row, int playerNumber) {
        for (int col = 0; col < matrixSize; col++) {
            if (cells.get(row, col) != playerNumber) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int col, int playerNumber) {
        for (int row = 0; row < matrixSize; row++) {
            if (cells.get(row, col) != playerNumber) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonals(int playerNumber) {
        boolean diag1Win = true;
        boolean diag2Win = true;
        for (int i = 0; i < matrixSize; i++) {
            if (getCell(i, i) != playerNumber) {
                diag1Win = false;
            }
            if (getCell(i, matrixSize - 1 - i) != playerNumber) {
                diag2Win = false;
            }
        }
        return diag1Win || diag2Win;
    }

    public int togglePlayerTurn() {
        playerTurn = (playerTurn == 1) ? 2 : 1;
        return playerTurn;
    }

}