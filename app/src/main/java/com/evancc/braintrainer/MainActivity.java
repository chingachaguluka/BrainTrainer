package com.evancc.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView countDownTextView = null;
    TextView challengeTextView = null;
    TextView scoreTextView = null;
    GridLayout gridLayout = null;
    Button button1 = null;
    Button button2 = null;
    Button button3 = null;
    Button button4 = null;
    TextView feedbackTextView = null;
    Button playAgainButton = null;
    Button goButton = null;

    CountDownTimer countDownTimer = null;
    Random r = new Random();
    int result = 0;

    public void createChallenge() {

        //subroutine to create challenge and setup answers in the four buttons
        int firstNum = r.nextInt(60) + 30;
        int secondNum = r.nextInt(60) + 30;
        String resultText = "";

        List<String> dummyResult = new ArrayList<String>();

        result = firstNum + secondNum;
        resultText = Integer.toString(result);
        challengeTextView.setText(String.valueOf(firstNum) + " + " + String.valueOf(secondNum));

        for(int cnt = 0; cnt < 4; cnt++) {
            dummyResult.add(Integer.toString(r.nextInt(50) + result - 2));
        }

        int correctPosition = r.nextInt(3) + 0;
        dummyResult.set(correctPosition, resultText);

        /*
        for (int cnt = 0; cnt < 4; cnt++) {
            Log.i("Results", dummyResult.get(cnt));
        }
        */
        //Log.i("Correct Result", resultText);

        button1.setText(dummyResult.get(0));
        button2.setText(dummyResult.get(1));
        button3.setText(dummyResult.get(2));
        button4.setText(dummyResult.get(3));



    }

    public void resetGame(View view) {
        feedbackTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        createChallenge();
        startCountDownTimer();


    }

    public void startCountDownTimer() {
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDownTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
                //Log.i("Countdown Time", String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                countDownTextView.setText("0s");
                feedbackTextView.setText("Time's Up!");
                feedbackTextView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        };
        countDownTimer.start();
    }

    public void showControls() {
        //Subroutine to show all other views and hide the goButton
        countDownTextView.setVisibility(View.VISIBLE);
        challengeTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        //button1.setVisibility(View.VISIBLE);
        //button2.setVisibility(View.VISIBLE);
        //button3.setVisibility(View.VISIBLE);
        //button4.setVisibility(View.VISIBLE);
        feedbackTextView.setVisibility(View.VISIBLE);
        //playAgainButton.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);
    }

    public void startGame(View view) {
        showControls();
        createChallenge();
        startCountDownTimer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownTextView = findViewById(R.id.countDownTextView);
        challengeTextView = findViewById(R.id.challengeTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        gridLayout = findViewById(R.id.gridLayout);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        feedbackTextView = findViewById(R.id.feedbackTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        goButton = findViewById(R.id.goButton);


    }
}
