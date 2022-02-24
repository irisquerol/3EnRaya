package com.android.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int turno;

    private Button btn_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_1 = findViewById(R.id.btn_1);

        btn_1.setOnClickListener(view -> verifyWin(true, true));

    }


    private void verifyWin(boolean answer, boolean isTrue) {

    }

}