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

    private int userScore;
    private int aiScore;

    private TextView playerScoreText;
    private TextView aiScoreText;
    private TextView resultText;

    private ImageButton rockButton;
    private ImageButton paperButton;
    private ImageButton scissorButton;

    private ImageView playerHandImage;
    private ImageView aiHandImage;

    private List<ImageButton> handButtonsList;

    private Handler handler;

    public final static String RESULT_MESSAGE = "com.example.genesis.jaknpoy.RESULT_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerScoreText = (TextView) findViewById(R.id.playerScoreText);
        aiScoreText = (TextView) findViewById(R.id.aiScoreText);
        resultText = (TextView) findViewById(R.id.resultText);

        rockButton = (ImageButton) findViewById(R.id.rockButton);
        paperButton = (ImageButton) findViewById(R.id.paperButton);
        scissorButton = (ImageButton) findViewById(R.id.scissorButton);

        handButtonsList = new ArrayList<>();

        handButtonsList.add(rockButton);
        handButtonsList.add(paperButton);
        handButtonsList.add(scissorButton);

        playerHandImage = (ImageView) findViewById(R.id.playerHandImage);
        aiHandImage = (ImageView) findViewById(R.id.aiHandImage);

        handler = new Handler();

        userScore = 0;
        aiScore = 0;

    }

    public void move(View view) {
        Hand userHand = Hand.ROCK;
        Hand aiHand = Hand.randomHand();

        switch (view.getId()) {
            case R.id.rockButton: {
                userHand = Hand.ROCK;
            }
            break;

            case R.id.paperButton: {
                userHand = Hand.PAPER;
            }
            break;

            case R.id.scissorButton: {
                userHand = Hand.SCISSORS;
            }
            break;
        }

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
        int hand1Val = hand1.ordinal();
        int hand2Val = hand2.ordinal();

        int retVal = (3 + hand1Val - hand2Val) % 3;

        return retVal;
    }

    //Updates Game Screen depending on compare value
    private void updateGameScreen(int compareVal){

        if(compareVal == 0) {
            resultText.setText(R.string.draw_text);
        } else if(compareVal == 1){
            resultText.setText(R.string.win_text);
            this.userScore++;
            this.playerScoreText.setText(Integer.toString(this.userScore));
        } else if(compareVal == 2){
            resultText.setText(R.string.lose_text);
            this.aiScore++;
            this.aiScoreText.setText(Integer.toString(this.aiScore));
        }
    }

    private void timedDisableButtons(int ms){
        for(ImageButton ib : handButtonsList)
            ib.setEnabled(false);
        resultText.setVisibility(View.VISIBLE);
        playerHandImage.setVisibility(View.VISIBLE);
        aiHandImage.setVisibility(View.VISIBLE);

        Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(ImageButton ib : handButtonsList)
                            ib.setEnabled(true);
                        resultText.setVisibility(View.INVISIBLE);
                        playerHandImage.setVisibility(View.INVISIBLE);
                        aiHandImage.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }, ms);
    }

    private void updatehand(Hand hand, ImageView iv){
        switch(hand){
            case ROCK:{
                iv.setBackground(getResources().getDrawable(R.drawable.rock));
            }break;

            case PAPER:{
                iv.setBackground(getResources().getDrawable(R.drawable.paper));
            }break;

            case SCISSORS:{
                iv.setBackground(getResources().getDrawable(R.drawable.scissor));
            }break;
        }
    }

    private void checkWinner(int winningScore){
        if(this.userScore == winningScore || this.aiScore == winningScore) {
            final GameActivity thisActivity = this;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(thisActivity, ResultActivity.class);
                    String message = (userScore > aiScore) ? "You Won!" : "You Lost!";
                    intent.putExtra(RESULT_MESSAGE, message);
                    startActivity(intent);
                }
            }, 1000);
        }
    }
}

