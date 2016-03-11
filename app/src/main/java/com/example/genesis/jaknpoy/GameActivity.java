package com.example.genesis.jaknpoy;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    public enum Hand {
        ROCK,
        PAPER,
        SCISSORS;

        private static final List<Hand> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Hand randomHand() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    //Your code here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Your code here

    }

    public void move(View view) {

        //Your code here

        int result = compareHands(userHand,aiHand);
        updatehand(userHand, this.playerHandImage);
        updatehand(aiHand, this.aiHandImage);
        updateGameScreen(result);
        timedDisableButtons(1500);
        checkWinner(3);
    }

    /*
        Returns
            1 if hand1 wins
            2 if hand2 wins
            0 if draw
     */
    private int compareHands(Hand hand1, Hand hand2){
        //Your code here
    }

    //Updates Game Screen depending on compare value
    private void updateGameScreen(int compareVal){
        //Your code here
    }
    
    //Disable Buttons for a ms microseconds
    private void timedDisableButtons(int ms){

        //Your code here
        Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Your code here
                    }
                });
            }
        }, ms);
    }
    
    
    //Show what the players have chosen
    private void updatehand(Hand hand, ImageView iv){
        //Your code here
    }

    //Checks the winner
    //If there is a winner go to ResultActivity
    private void checkWinner(int winningScore){
        //Your code here
    }
}

