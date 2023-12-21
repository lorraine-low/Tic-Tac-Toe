package com.example.tictactoe;

import java.io.Serializable;

/**
 * @author Lorraine Low
 * @version Android Studio Hedgehog 2023.1.1
 */

public class Matrix implements Serializable {

    private int[][] matrix;
    private static final int MAX_SIZE = 5;

    public Matrix() {
        matrix = new int[MAX_SIZE][MAX_SIZE];
    }

    public void set(int rowIndex, int colIndex, int data) {
        if (rowIndex < MAX_SIZE && colIndex < MAX_SIZE) {
            matrix[rowIndex][colIndex] = data;
        }
    }

    public int get(int rowIndex, int colIndex) {
        if (rowIndex >= 0 && rowIndex < MAX_SIZE && colIndex >= 0 && colIndex < MAX_SIZE) {
            return matrix[rowIndex][colIndex];
        } else {
            return 0;
        }
    }
}
