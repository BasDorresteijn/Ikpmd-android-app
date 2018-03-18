package com.example.bas_d.simpledungeon.views.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bas_d.simpledungeon.R;
import com.example.bas_d.simpledungeon.model.Score;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class ScoreActivity extends AppCompatActivity {

    private int score = 0;
    private boolean died = false;
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        setScore();
        setupFirebase();
    }

    private void setScore() {
        TextView scorefield = findViewById(R.id.score);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            score = b.getInt("score");
            died = b.getBoolean("died");
            Log.d("SimpleDungeon", String.valueOf(score));
        }
        if(died) {
            TextView scoreHeader = findViewById(R.id.scoreHeader);
            scoreHeader.setText(R.string.defeat);
        }
        scorefield.setText(scorefield.getText() + " "+ String.valueOf(score));
    }

    private void setupFirebase() {
        final TextView textName = findViewById(R.id.textName);
        final Button button = findViewById(R.id.submitScore);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Objects.equals(textName.getText().toString(), "")) {
                    myRef.push().setValue(new Score(textName.getText().toString(), score));
                    Snackbar.make(view, R.string.succes, Snackbar.LENGTH_LONG).show();
                    button.setEnabled(false);
                }
            }
        });

    }
}
