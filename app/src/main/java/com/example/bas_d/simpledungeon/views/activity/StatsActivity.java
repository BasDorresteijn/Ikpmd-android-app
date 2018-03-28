package com.example.bas_d.simpledungeon.views.activity;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bas_d.simpledungeon.R;
import com.example.bas_d.simpledungeon.model.FixedValues;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    private int dealt, taken;
    private final String damageDealt = "damagedealt", damageTaken = "damagetaken";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        setUpchart();
        setUpbutton();
    }

    private void setUpbutton() {
        Button resetData = findViewById(R.id.resetdata);
        resetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPreferences();
                Snackbar.make(view, R.string.statsreset, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void resetPreferences() {
        SharedPreferences stats = getSharedPreferences(FixedValues.DAMAGE, 0);
        SharedPreferences.Editor editor = stats.edit();
        editor.putInt(damageTaken, 0);
        editor.putInt(damageDealt, 0);
        editor.commit();
        getData();
    }

    private void setUpchart() {
        PieChart chart = findViewById(R.id.chart);
        chart.setDescription(getText(R.string.chartdescription).toString());
        chart.setData(getData());
    }

    public PieData getData() {
        readPreferences();
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        entries.add(new Entry(dealt,0));
        entries.add(new Entry(taken, 1));

        descriptions.add("dealt");
        descriptions.add("recieved");

        colors.add(Color.rgb(125, 168, 237)); //Blueish
        colors.add(Color.rgb(255, 119, 126)); //Redish

        PieDataSet data = new PieDataSet(entries, getText(R.string.damage).toString());
        data.setColors(colors);
        return new PieData(descriptions, data);
    }

    private void readPreferences() {
        SharedPreferences stats = getSharedPreferences(FixedValues.DAMAGE, 0);
        taken = stats.getInt(damageTaken, 0);
        dealt = stats.getInt(damageDealt, 0);
    }
}
