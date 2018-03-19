package com.pause;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by summerturner on 3/19/18.
 */

public class CongratsActivity extends Activity {

    SharedPreferences myPreferences;
    TextView timeAchievedTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congrats_layout);


        myPreferences = this.getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);
        String breed = myPreferences.getString("BREED", "unknown");
        int bones = myPreferences.getInt("TOTALBONES", 0);

        int previousLockTime = myPreferences.getInt("PREVIOUSTIME", 0) * 60;
        Log.d("$$$$$$",""+previousLockTime);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putInt("TOTALBONES", bones+50);
        myEditor.commit();

        timeAchievedTextView = (TextView)findViewById(R.id.timeAchievedTextView);
        timeAchievedTextView.setText(
                String.format("%02d:%02d:%02d", previousLockTime / 3600,
                        (previousLockTime % 3600) / 60, (previousLockTime % 60)));

        Button nextButton = (Button)findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}
