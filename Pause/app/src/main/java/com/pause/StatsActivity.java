package com.pause;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by turner36 on 2/27/18.
 */

public class StatsActivity extends AppCompatActivity {

    ImageButton homeButton;
    SharedPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_layout);

        homeButton = (ImageButton)findViewById(R.id.statsHomeButton);
        homeButton.setOnClickListener(selectHome);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        myPreferences = this.getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);


        /* ----- Weekly ----- */
        // use static labels for horizontal and vertical labels
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Mon.", "Tues.", "Wed.", "Thurs.", "Fri.", "Sat.", "Sun."});
        staticLabelsFormatter.setVerticalLabels(new String[] {"low",  "high"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, myPreferences.getInt("MON.", 0)),
                new DataPoint(1, myPreferences.getInt("TUES.", 0)),
                new DataPoint(2, myPreferences.getInt("WED.", 0)),
                new DataPoint(3, myPreferences.getInt("THURS.", 0)),
                new DataPoint(4, myPreferences.getInt("FRI.", 0)),
                new DataPoint(5, myPreferences.getInt("SAT.", 0)),
                new DataPoint(6, myPreferences.getInt("SUN.", 0)),
        });
        graph.addSeries(series);
        // styling
        series.setSpacing(50);
    }


    View.OnClickListener selectHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


}
