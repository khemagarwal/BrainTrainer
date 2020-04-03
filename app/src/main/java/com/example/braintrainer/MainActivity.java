package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locationOfCorrectAns;
    TextView resultTextView;
    int score=0;
    int numberOfQuestion=0;
    TextView scoreTextView,problemTextView,timerTextView;
    Button button2,button3,button4,button5,playAgain;
    ConstraintLayout gameLayout;

    public void newQuestion()
    {
        Random random=new Random();
        int a=random.nextInt(21);
        int b=random.nextInt(21);

        problemTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));

        locationOfCorrectAns=random.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++)
        {
            if(i == locationOfCorrectAns)
            {
                answers.add(a+b);
            }
            else
            {
                int wrongAns=random.nextInt(41);
                while(wrongAns == a+b)
                {
                    wrongAns=random.nextInt(41);
                }
                answers.add(wrongAns);

            }

        }
        button2.setText(Integer.toString(answers.get(0)));
        button3.setText(Integer.toString(answers.get(1)));
        button4.setText(Integer.toString(answers.get(2)));
        button5.setText(Integer.toString(answers.get(3)));

    }
    public void playAgain(View view)
    {
     score=0;
     numberOfQuestion=0;
     timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
     newQuestion();
     playAgain.setVisibility(View.INVISIBLE);
     resultTextView.setText(" ");

        new CountDownTimer(30000+100,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public  void chooseAnswer(View view)
    {
        if(Integer.toString(locationOfCorrectAns).equals(view.getTag().toString()))
        {
                resultTextView.setText("Correct!!!");
                score++;
                numberOfQuestion++;
                scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        }
        else
        {
            resultTextView.setText("Wrong...:(");
            numberOfQuestion++;
            scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));


        }
        newQuestion();

    }
    public void goClick(View view)
    {
        playAgain(findViewById(R.id.timerTextView));
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=findViewById(R.id.button);
        problemTextView=(TextView)findViewById(R.id.problemTextView);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button5=findViewById(R.id.button5);
        resultTextView=findViewById(R.id.resulttextView);
        scoreTextView=findViewById(R.id.ScoreTextView);
        timerTextView=findViewById(R.id.timerTextView);
        playAgain=findViewById(R.id.playAgain);
    gameLayout=findViewById(R.id.gameLayout);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);



    }
}
