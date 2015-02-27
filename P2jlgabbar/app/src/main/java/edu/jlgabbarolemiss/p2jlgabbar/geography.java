package edu.jlgabbarolemiss.p2jlgabbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.LinkedHashMap;

/**
 * Created by josephgabbard on 2/19/15.
 */
public class geography extends MainActivity {

    //LinkedHashMap<String,String> hmap = new LinkedHashMap<>();
    String[] keys;
    String[] values;
    int correct, incorrect;
    RadioGroup buttons;
    RadioButton b1, b2, b3, b4;
    TextView states, geoScoreView;
    Button submit, back;
    Random ran;
    int a, b, c, d;
    int answerIndex;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geography_layout);
//        for(int i = 0; i < Math.min(keys.length,values.length); i++){
//            hmap.put(keys[i],values[i]);
//        }
        keys = this.getResources().getStringArray(R.array.states);
        values = this.getResources().getStringArray(R.array.capitals);
        states = (TextView) findViewById(R.id.stateView);
        buttons = (RadioGroup) findViewById(R.id.radioGroup);
        geoScoreView = (TextView) findViewById(R.id.geoScore);
        Intent sender = getIntent();
        correct = sender.getExtras().getInt("geoCorrect");
        incorrect = sender.getExtras().getInt("geoIncorrect");
        geoScoreView.setText(correct + " out of " + (correct + incorrect));
        b1 = (RadioButton) findViewById(R.id.radioButton);
        b2 = (RadioButton) findViewById(R.id.radioButton2);
        b3 = (RadioButton) findViewById(R.id.radioButton3);
        b4 = (RadioButton) findViewById(R.id.radioButton4);
        submit = (Button) findViewById(R.id.submitAnswerButton);
        back = (Button) findViewById(R.id.backButton);
        ran = new Random();
        a = ran.nextInt(16);
        b = ran.nextInt(16);
        c = ran.nextInt(16);
        d = ran.nextInt(16);
        checkRandom();
        setButtonText();
        decideAnswer();
        states.setText(keys[answerIndex]);
        buttons.clearCheck();

        /* Attach CheckedChangeListener to radio group */
        buttons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {

                }

            }
        });


    }

    public void onSubmit(View v) {
        if (buttons.getCheckedRadioButtonId() != -1) {
            RadioButton rb = (RadioButton) buttons.findViewById(buttons.getCheckedRadioButtonId());
            if (rb.getText().toString() == values[answerIndex]) {
                correct++;
                Toast.makeText(getApplicationContext(), "Correct!",
                        Toast.LENGTH_LONG).show();
                a = ran.nextInt(16);
                b = ran.nextInt(16);
                c = ran.nextInt(16);
                d = ran.nextInt(16);
                checkRandom();
                setButtonText();
                decideAnswer();
                states.setText(keys[answerIndex]);
                buttons.clearCheck();
                geoScoreView.setText(correct + " out of " + (correct + incorrect));
            } else {
                incorrect++;
                Toast.makeText(getApplicationContext(), "Wrong!",
                        Toast.LENGTH_LONG).show();
                a = ran.nextInt(16);
                b = ran.nextInt(16);
                c = ran.nextInt(16);
                d = ran.nextInt(16);
                checkRandom();
                setButtonText();
                decideAnswer();
                states.setText(keys[answerIndex]);
                buttons.clearCheck();
                geoScoreView.setText(correct + " out of " + (correct + incorrect));
            }

        } else {
            Toast.makeText(getApplicationContext(), "Please Select an Answer!",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void goBack(View v) {

        Intent i = new Intent();
        i.putExtra("geoCorrectAnswers", correct);
        i.putExtra("geoIncorrectAnswers", incorrect);
        setResult(2, i);

        finish();
    }

    public void checkRandom() {
        if (a == b || a == c || a == d) {
            a = ran.nextInt(16);
            checkRandom();
        }
        if (b == c || b == d) {
            b = ran.nextInt(16);
            checkRandom();
        }
        if (c == d) {
            c = ran.nextInt(16);
            checkRandom();
        }
    }

    public void setButtonText() {
        b1.setText(values[a]);
        b2.setText(values[b]);
        b3.setText(values[c]);
        b4.setText(values[d]);
    }

    public void decideAnswer() {
        int array[] = new int[4];
        array[0] = a;
        array[1] = b;
        array[2] = c;
        array[3] = d;
        int z = ran.nextInt(4);
        answerIndex = array[z];


    }
}
