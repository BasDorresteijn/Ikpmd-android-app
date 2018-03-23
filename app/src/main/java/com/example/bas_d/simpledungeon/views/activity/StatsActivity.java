package com.example.bas_d.simpledungeon.views.activity;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        SharedPreferences damage = getSharedPreferences(FixedValues.DAMAGE, 0);
        taken = damage.getInt(damageTaken, 0);
        dealt = damage.getInt(damageDealt, 0);
    }
}
