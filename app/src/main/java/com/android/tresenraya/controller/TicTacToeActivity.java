package com.android.tresenraya.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tresenraya.R;
import com.android.tresenraya.model.Board;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeActivity extends AppCompatActivity {
    private Board board;
    private int turn = 1;
    private TextView textTurn;
    List<Button> buttons;
    private static final int[] BUTTON_IDS = {
            R.id.btn_1,
            R.id.btn_2,
            R.id.btn_3,
            R.id.btn_4,
            R.id.btn_5,
            R.id.btn_6,
            R.id.btn_7,
            R.id.btn_8,
            R.id.btn_9,
    };
    private ImageButton restart;

    /**
     * Creamos y llamamos el la funcion para crear botones y el texto
     * @param savedInstanceState bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createListButtons();
        textTurn = findViewById(R.id.text_turn);

    }

    /**
     * This function restarts the application to play agian without
     * needing to close the app
     */
    private void restartGame() {
        finish();
        startActivity(getIntent());
    }

    /**
     * setOnCLickListener to all buttons
     */
    private void createListButtons() {
        buttons = new ArrayList<>(BUTTON_IDS.length);

        restart = findViewById(R.id.restart_b);
        restart.setOnClickListener(view -> restartGame());
        restart.setVisibility(View.GONE);

        for (int id : BUTTON_IDS) {
            Button button = findViewById(id);
            button.setOnClickListener(view -> print(button)); // maybe
            buttons.add(button);
        }
        board = new Board(buttons);
    }


    /**
     * This function prints in screen an X or O, depending on the
     * current player turn.
     *
     * Everytime a button is pressed we check if a player has won.
     *
     * @param btn buttons
     */
    private void print(Button btn) {
        if (turn == 1) {
            btn.setText(R.string.player1);//X
            btn.setEnabled(false);
            turn = 2;
        } else {
            btn.setText(R.string.player2);//O
            btn.setEnabled(false);
            turn = 1;
        }
        changeTextTurn();
        checkBoard();
        board.updateMatrix(buttons);
        if (board.checkWin("X")) {
            showToast(1);
            blockBoard();

            restart.setVisibility(View.VISIBLE);
            textTurn.setText(R.string.win1);
        }
        if (board.checkWin("O")) {
            showToast(2);
            blockBoard();
            restart.setVisibility(View.VISIBLE);
            textTurn.setText(R.string.win2);
        }
    }
    //}

    /**
     * Function to block the board once a player has won
     */
    private void blockBoard() {
        for (Button btn : buttons) {
            btn.setEnabled(false);
        }
    }

    /**
     * Function to check the status of the board
     */
    private void checkBoard() {
        boolean emptyBox = false;
        for (Button btn : buttons) {
            if (btn.isEnabled())
                emptyBox = true;
        }
        if (!emptyBox) {
            restart.setVisibility(View.VISIBLE);
            textTurn.setText(R.string.draw);
        }
    }

    /**
     * Change the text to inform the user the current player turn
     */
    private void changeTextTurn() {
        if (turn == 1) {
            textTurn.setText(R.string.turn1);
        } else {
            textTurn.setText(R.string.turn2);
        }
    }

    /**
     * Function that shows which player has won with a toast
     * @param type int
     */
    private void showToast(int type) {
        Toast t;
        if (type == 0) {
            t = Toast.makeText(TicTacToeActivity.this,
                    R.string.block_toast, Toast.LENGTH_SHORT);
        } else if (type == 1) {
            t = Toast.makeText(TicTacToeActivity.this,
                    R.string.win1, Toast.LENGTH_SHORT);//PlayerX Win
        } else {
            t = Toast.makeText(TicTacToeActivity.this,
                    R.string.win2, Toast.LENGTH_SHORT);//PlayerX Win
        }

        t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        t.show();
    }

}