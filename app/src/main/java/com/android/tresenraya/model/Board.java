package com.android.tresenraya.model;

import android.widget.Button;

import java.util.List;

public class Board {
    Button matrix[][];

    public Board(List<Button> buttons) {
        updateMatrix(buttons);
    }

    public void updateMatrix(List<Button> buttons) {
        matrix = new Button[3][3];
        int cont = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = buttons.get(cont++);
            }
        }

    }


    /**
     * @return 1 or 2 depending on who won. returns 0 if anyone won
     */
    public boolean checkWin(String player) {
        boolean win = false;
        for (int i = 0; i < 3; i++) {
            if (matrix[i][0].getText().equals(player) && matrix[i][1].getText().equals(player) && matrix[i][2].getText().equals(player)) {
                win = true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (matrix[0][i].getText().equals(player) && matrix[1][i].getText().equals(player) && matrix[2][i].getText().equals(player)) {
                win = true;
            }
        }

        if (matrix[0][0].getText().equals(player) && matrix[1][1].getText().equals(player) && matrix[2][2].getText().equals(player)) {
            win = true;
        } else if (matrix[0][2].getText().equals(player) && matrix[1][1].getText().equals(player) && matrix[2][0].getText().equals(player)) {
            win = true;
        }


        return win;
    }
}
