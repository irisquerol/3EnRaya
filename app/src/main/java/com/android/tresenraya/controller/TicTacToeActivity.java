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
    private Button restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createListButtons();
        textTurn = findViewById(R.id.text_turn);

    }

    private void restartGame() {
        finish();
        startActivity(getIntent());
    }

    private void createListButtons() {
        buttons = new ArrayList<>(BUTTON_IDS.length);

        ImageButton restart = (ImageButton)findViewById(R.id.restart_b);
        restart.setOnClickListener(view -> restartGame());
        restart.setVisibility(View.GONE);

        for (int id : BUTTON_IDS) {
            Button button = findViewById(id);
            button.setOnClickListener(view -> print(button)); // maybe
            buttons.add(button);
        }
        board = new Board(buttons);
    }


    private void print(Button btn) {
      /*  if (btn.getText().equals(R.string.player1)) {
            showToast(0);
        } else if (btn.getText().equals(R.string.player2)) {
            showToast(0);
        } else {*/
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

    private void blockBoard() {
        for (Button btn : buttons) {
            btn.setEnabled(false);
        }
    }

    private void changeTextTurn() {
        if (turn == 1) {
            textTurn.setText(R.string.turn1);
        } else {
            textTurn.setText(R.string.turn2);
        }
    }

    private void showToast(int type) {
        Toast t;
        if (type == 0) {
            t = Toast.makeText(TicTacToeActivity.this,
                    R.string.block_toast, Toast.LENGTH_SHORT);
        } else if (type == 1) {
            t = Toast.makeText(TicTacToeActivity.this,
                    "PLAYER 1 HA GANADO -X-", Toast.LENGTH_SHORT);//PlayerX Win
        } else {
            t = Toast.makeText(TicTacToeActivity.this,
                    "PLAYER 2 HA GANADO -O-", Toast.LENGTH_SHORT);//PlayerX Win
        }

        t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        t.show();
    }

    private void verifyWin(boolean answer, boolean isTrue) {

    }


}