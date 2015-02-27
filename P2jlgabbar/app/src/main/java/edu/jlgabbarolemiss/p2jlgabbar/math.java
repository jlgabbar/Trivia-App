package edu.jlgabbarolemiss.p2jlgabbar;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by josephgabbard on 2/19/15.
 */

public class math extends MainActivity {

    EditText mathAnswer;
    Button submit;
    Button back;
    TextView problemView;
    TextView scoreView;
    int correct, incorrect;
    int a, b;
    Random ran = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_layout);
        Intent sender = getIntent();
        Random ran = new Random();
        a = ran.nextInt(100);
        b = ran.nextInt(100);
        problemView = (TextView) findViewById(R.id.mathProbView);
        problemView.setText(a + " + " + b);
        back = (Button) findViewById(R.id.returnButton);
        submit = (Button) findViewById(R.id.submitMathButton);
        correct = sender.getExtras().getInt("mathCorrect");
        incorrect = sender.getExtras().getInt("mathIncorrect");
        scoreView = (TextView) findViewById(R.id.scoreID);
        scoreView.setText(correct + " out of " + (correct + incorrect));


    }

    public void goBack(View v) {


        Intent iMessage = new Intent();
        iMessage.putExtra("correctAnswers", correct);
        iMessage.putExtra("incorrectAnswers", incorrect);
        setResult(1, iMessage);

        finish();

    }

    public void submitAnswer(View v) throws NumberFormatException {
        mathAnswer = (EditText) findViewById(R.id.mathAnswerView);
        //check to make sure not null
        try {
            if (Integer.parseInt(mathAnswer.getText().toString()) == (a + b)) {
                Toast.makeText(getApplicationContext(), "Correct!",
                        Toast.LENGTH_LONG).show();
                correct++;
                scoreView.setText(correct + " out of " + (correct + incorrect));
                a = ran.nextInt(100);
                b = ran.nextInt(100);
                problemView.setText(a + " + " + b);
                mathAnswer.setText("");

            } else {
                Toast.makeText(getApplicationContext(), "Wrong!",
                        Toast.LENGTH_LONG).show();
                incorrect++;
                scoreView.setText(correct + " out of " + (correct + incorrect));
                a = ran.nextInt(100);
                b = ran.nextInt(100);
                problemView.setText(a + " + " + b);
                mathAnswer.setText("");
            }
        } catch (NumberFormatException nfe) {
            Toast.makeText(getApplicationContext(), "Please enter a number",
                    Toast.LENGTH_LONG).show();
        }

    }


}
