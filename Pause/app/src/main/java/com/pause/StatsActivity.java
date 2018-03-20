package com.pause;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import org.w3c.dom.Text;

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


        TextView timePaused = (TextView) findViewById(R.id.timeValue);
        float hours = myPreferences.getFloat("TOTALTIME", 0.0f);
        timePaused.setText(""+hours/60 + " HRS");

        /* ----- Daily ----- */
        // use static labels for horizontal and vertical labels
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Mon.", "Tues.", "Wed.", "Thurs.", "Fri.", "Sat.", "Sun."});
        staticLabelsFormatter.setVerticalLabels(new String[] {"0HR", "5HRS"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph.getViewport().setMaxY(5.0);
        graph.setTitleTextSize(75);

        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(7);
        graph.getViewport().setMinY(0.0);
        graph.getViewport().setMaxY(5.0);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);


        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, (myPreferences.getFloat("MON.", 0))/60),
                new DataPoint(2, (myPreferences.getFloat("TUES.", 0))/60),
                new DataPoint(3, (myPreferences.getFloat("WED.", 0))/60),
                new DataPoint(4, (myPreferences.getFloat("THURS.", 0))/60),
                new DataPoint(5, (myPreferences.getFloat("FRI.", 0))/60),
                new DataPoint(6, (myPreferences.getFloat("SAT.", 0))/60),
                new DataPoint(7, (myPreferences.getFloat("SUN.", 0))/60)
        });
        graph.addSeries(series);
        series.setColor(Color.WHITE);
        // styling
        series.setSpacing(10);

    }


    View.OnClickListener selectHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


}
