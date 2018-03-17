package com.example.bas_d.simpledungeon.views.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.bas_d.simpledungeon.R;

public class ScoreActivity extends AppCompatActivity {

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        setScore();
    }

    private void setScore() {
        TextView scorefield = findViewById(R.id.score);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            score = b.getInt("score");
            Log.d("SimpleDungeon", String.valueOf(score));
        }
        scorefield.setText(scorefield.getText() + " "+ String.valueOf(score));
    }
}
