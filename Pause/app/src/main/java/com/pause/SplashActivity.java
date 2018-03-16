package com.pause;

/**
 * Created by maiv on 2/12/18.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class SplashActivity extends AppCompatActivity {

    boolean existingUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
        SharedPreferences myPreferences = this.getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);

        // using while developing to clear user data from preferences
        myPreferences.edit().clear().commit();

        existingUser = myPreferences.getBoolean("USER?", false);

        // By this point user information should be setup and you can move onto next screen

        /**
         * Delay 1000 ms to display splash activity and forward along to main activity.
         */
        Handler handle=new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Transfer to breed select if no user data found for dog, otherwise go to main activity
                if(!existingUser ) {
                    Intent intent = new Intent(SplashActivity.this, BreedSelect.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                SplashActivity.this.finish();
            }
        },1500);

    }
}