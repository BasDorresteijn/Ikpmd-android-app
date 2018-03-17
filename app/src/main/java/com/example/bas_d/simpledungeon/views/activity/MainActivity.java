package com.example.bas_d.simpledungeon.views.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.bas_d.simpledungeon.R;
import com.example.bas_d.simpledungeon.model.Score;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setupfirebase();
        button = findViewById(R.id.game);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
                Log.d("SimpleDungeon", "done");
            }
        });
    }

    private void setupfirebase() {
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        //myRef.push().setValue(new Score("henk", 100));

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Score> scores = new ArrayList<>();
                for(DataSnapshot data : dataSnapshot.getChildren()) {
                    scores.add(data.getValue(Score.class));
                }
                Collections.sort(scores, new Comparator<Score>() {
                    @Override
                    public int compare(Score s1, Score s2) {
                        if(s1.getScore() > s2.getScore()) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                });
                for(Score score : scores) {
                    Log.d("SimpleDungeon",  String.valueOf(score.getScore()));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("SimpleDungeon", "Failed to read value.", error.toException());
            }
        });
    }



}
