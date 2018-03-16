package com.pause;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class nameDialogActivity extends Activity {
    SharedPreferences myPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_dialog);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.3));
        final EditText dogName = (EditText)findViewById(R.id.dogName);

        myPreferences = this.getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);

        Button nextButton = (Button)findViewById(R.id.nameNext);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Save dogname to preference file
                SharedPreferences.Editor myEditor = myPreferences.edit();
                myEditor.putString("DOGNAME", dogName.getText().toString());
                myEditor.commit();
                startActivity(new Intent(nameDialogActivity.this, MainActivity.class));
                finish();
            }
        });


        Button backButton = (Button)findViewById(R.id.nameBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}