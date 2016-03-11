package com.example.genesis.jaknpoy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        String message = intent.getStringExtra(GameActivity.RESULT_MESSAGE);

        resultText = (TextView) findViewById(R.id.resultText);
        resultText.setText(message);

    }

    public void playAgain(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void returnToMainMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
