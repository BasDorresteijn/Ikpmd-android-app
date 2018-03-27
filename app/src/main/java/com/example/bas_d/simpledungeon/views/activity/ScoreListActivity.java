package com.example.bas_d.simpledungeon.views.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bas_d.simpledungeon.R;
import com.example.bas_d.simpledungeon.dummy.DummyContent;
import com.example.bas_d.simpledungeon.model.Score;
import com.example.bas_d.simpledungeon.views.fragment.ScoreFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreListActivity extends AppCompatActivity implements ScoreFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);
        if(!isNetworkAvailable()) {
            TextView noInternet = new TextView(this);
            noInternet.setText(R.string.nointernet);
            this.addContentView(noInternet, new ViewGroup.LayoutParams(600,600));
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onListFragmentInteraction(Score item) {

    }
}
