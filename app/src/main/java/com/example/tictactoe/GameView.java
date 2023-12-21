package com.example.tictactoe;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Lorraine Low
 * @version Android Studio Hedgehog 2023.1.1
 */

public class GameView {
    private Context context;
    private Activity activity;
    private TextView message;

    public GameView(Context context, Activity activity, TextView message) {
        this.context = context;
        this.activity = activity;
        this.message = message;
    }

    private Button getButtonById(int buttonId) {
        return activity.findViewById(buttonId);
    }

    public void restoreCellValue(boolean restoreText, int matrixSize, Matrix cells) {
        for (int row = 0; row < matrixSize; row++) {
            for (int col = 0; col < matrixSize; col++) {
                int buttonId = context.getResources().getIdentifier("button" + ((row * matrixSize) + col + 1), "id", context.getPackageName());
                Button button = getButtonById(buttonId);
                if (restoreText) {
                    int cellValue = cells.get(row, col);
                    if (cellValue != 0) {
                        button.setText(String.valueOf(cellValue));
                    }
                } else {
                    button.setTag((row * matrixSize) + col);
                }
            }
        }
    }

    public void resetButtons(int matrixSize) {
        for (int row = 0; row < matrixSize; row++) {
            for (int col = 0; col < matrixSize; col++) {
                int buttonId = context.getResources().getIdentifier("button" + ((row * matrixSize) + col + 1), "id", context.getPackageName());
                Button button = getButtonById(buttonId);
                button.setText("");
            }
        }
    }

    public void performButtonClick(int cellIndex) {
        int buttonId = context.getResources().getIdentifier("button" + (cellIndex + 1), "id", context.getPackageName());
        Button button = getButtonById(buttonId);
        button.performClick();
    }

    public TextView getMessage() {
        return this.message;
    }

    public void setMessage(String messageText) {
        this.message.setText(messageText);
    }

}