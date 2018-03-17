package com.pause;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.Context;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by summerturner on 2/24/18.
 *
 * Uses android-gif-drawable for animated GIFs https://github.com/koral--/android-gif-drawable
 */

public class PauseActivity extends Activity {

    SharedPreferences myPreferences;
    TextView timeTextView;
    Timer t;
    int pauseTime;
    boolean active = false;

    private DevicePolicyManager devicePolicyManager=null;
    private ComponentName cn=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pause_layout);

        cn=new ComponentName(this, AdminReceiver.class);
        devicePolicyManager=(DevicePolicyManager)getSystemService(DEVICE_POLICY_SERVICE);

        myPreferences = this.getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);
        String breed = myPreferences.getString("BREED", "unknown");
        GifImageView dogImage = (GifImageView)findViewById(R.id.gifImageView);

        if (breed.equals("beagle")) {
            dogImage.setImageResource(R.drawable.beagle_sleeping);
        } else if (breed.equals("shihtzu")) {
            dogImage.setImageResource(R.drawable.shih_tzu_sleeping);
        } else if (breed.equals("shiba")) {
            dogImage.setImageResource(R.drawable.shiba_sleeping);
        }

        int selectedTime = myPreferences.getInt("PAUSE_TIME", 0);
        pauseTime = selectedTime * 60;

        /* Timer increments count when activity starts until unlock button is clicked. */
        t = new Timer();
        timeTextView = (TextView)findViewById(R.id.timeTextView);
        timeTextView.setText(
                String.format("%02d:%02d:%02d", pauseTime / 3600,
                        (pauseTime % 3600) / 60, (pauseTime % 60)));

        lockMeNow();

        if (active) {
            t.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            timeTextView.setText(
                                    String.format("%02d:%02d:%02d", pauseTime / 3600,
                                            (pauseTime % 3600) / 60, (pauseTime % 60)));

                            if (pauseTime == 0)
                                t.cancel();

                            pauseTime--;
                        }
                    });
                }
            }, 1000, 1000);
        }

        Button unlockButton = (Button)findViewById(R.id.unlockButton);
        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.cancel();
                finish();
            }
        });

    }

    /**
     * https://github.com/commonsguy/cw-omnibus/tree/master/DeviceAdmin/LockMeNow
     */
    public void lockMeNow() {
        Log.d("myTag","lockMeNow()");
        if (devicePolicyManager.isAdminActive(cn)) {
            devicePolicyManager.lockNow();
            active = true;
        }
        else {
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, cn);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                    getString(R.string.device_admin_explanation));
            startActivity(intent);
        }
    }
}
