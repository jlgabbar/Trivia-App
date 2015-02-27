package edu.jlgabbarolemiss.p2jlgabbar;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    ImageButton b1, b2;
    Button b3;
    TextView mathTextView, geoTextView, totalTextView;
    int request_Code = 1;
    int request_Code_Geo = 2;
    int mathCorrect, mathIncorrect = 0;
    int geoCorrect, geoIncorrect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mathTextView = (TextView) findViewById(R.id.quiz1Output);
        geoTextView = (TextView) findViewById(R.id.quiz2Output);
        totalTextView = (TextView) findViewById(R.id.total_scoreID);
        totalTextView.setText((mathCorrect + geoCorrect) + " out of " + (mathCorrect + mathIncorrect + geoCorrect + geoIncorrect));
        mathTextView.setText(mathCorrect + " out of " + (mathCorrect + mathIncorrect));
        geoTextView.setText(geoCorrect + " out of " + (geoCorrect + geoIncorrect));
        b1 = (ImageButton) findViewById(R.id.math_button);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent("edu.jlgabbarolemiss.p2jlgabbar.math");
                i.putExtra("mathCorrect", mathCorrect);
                i.putExtra("mathIncorrect", mathIncorrect);
                startActivityForResult(i, request_Code);
            }
        });

        b2 = (ImageButton) findViewById(R.id.geo_button);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent("edu.jlgabbarolemiss.p2jlgabbar.geography");
                i.putExtra("geoCorrect", geoCorrect);
                i.putExtra("geoIncorrect", geoIncorrect);
                startActivityForResult(i, request_Code_Geo);
            }
        });

        b3 = (Button) findViewById(R.id.clear_buttonID);
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mathCorrect = 0;
                mathIncorrect = 0;
                geoCorrect = 0;
                geoIncorrect = 0;
                mathTextView.setText(mathCorrect + " out of " + (mathCorrect + mathIncorrect));
                geoTextView.setText(geoCorrect + " out of " + (geoCorrect + geoIncorrect));
                totalTextView.setText((mathCorrect + geoCorrect) + " out of " + (mathCorrect + mathIncorrect + geoCorrect + geoIncorrect));
            }
        });
    }

    protected void onActivityResult(int request_Code, int resultCode, Intent data) {
        super.onActivityResult(request_Code, resultCode, data);

        if (request_Code == 1) {
            if (data != null) {
                int x = data.getIntExtra("correctAnswers", 0);
                int y = data.getIntExtra("incorrectAnswers", 0);
                mathCorrect = x;
                mathIncorrect = y;
                mathTextView.setText(mathCorrect + " out of " + (mathCorrect + mathIncorrect));
                totalTextView.setText((mathCorrect + geoCorrect) + " out of " + (mathCorrect + mathIncorrect + geoCorrect + geoIncorrect));
            }
        } else {
            int x = data.getIntExtra("geoCorrectAnswers", 0);
            int y = data.getIntExtra("geoIncorrectAnswers", 0);
            geoCorrect = x;
            geoIncorrect = y;
            geoTextView.setText(geoCorrect + " out of " + (geoCorrect + geoIncorrect));
            totalTextView.setText((mathCorrect + geoCorrect) + " out of " + (mathCorrect + mathIncorrect + geoCorrect + geoIncorrect));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
