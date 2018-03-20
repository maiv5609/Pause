package com.pause;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
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
    int count;
    boolean active = false;
    static Activity activity;

    private DevicePolicyManager devicePolicyManager=null;
    private ComponentName adminReciever = null;
    //private ComponentName broadcastReceiver = null;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pause_layout);
        activity = this;

        adminReciever = new ComponentName(this, AdminReceiver.class);
        devicePolicyManager = (DevicePolicyManager)getSystemService(DEVICE_POLICY_SERVICE);

        /*
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        broadcastReceiver = new UserPresentBroadcastReceiver();
        registerReceiver(broadcastReceiver, filter);

        Log.d("USER_PRESENT", filter.getAction(2));
        */

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

        Log.d("PAUSETIME", "selectedTime * 60 = "+pauseTime);

        // Add requested lock time to preference file. Assumes that user went full time
        Calendar calendar = Calendar.getInstance();

        //Getting week information
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int currWeek = calendar.WEEK_OF_MONTH;

        //Getting month information
        int month = myPreferences.getInt("MONTH", 0);
        int currMonth = calendar.MONTH;
        int year = calendar.YEAR;
        float monthTotal = myPreferences.getFloat("MONTHTOTAL", 0);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        Log.d("CURRENT LOCK", ""+selectedTime);
        Log.d("CURRENT DAY", ""+day);

        //Assign bones based on time they selected
        int totalBones = myPreferences.getInt("TOTALBONES", 0);
        int currBones = myPreferences.getInt("CURRBONES", 0);
        switch (selectedTime){
            case 1: // using 1 minute for demo and testing purposes
                totalBones = totalBones + 1;
                currBones = currBones + 1;
                myEditor.putInt("TOTALBONES", totalBones);
                myEditor.putInt("CURRBONES", currBones);
                break;
            case 15:
                totalBones = totalBones + 2;
                currBones = currBones + 2;
                myEditor.putInt("TOTALBONES", totalBones);
                myEditor.putInt("CURRBONES", currBones);
                break;
            case 30:
                totalBones = totalBones + 3;
                currBones = currBones + 3;
                myEditor.putInt("TOTALBONES", totalBones);
                myEditor.putInt("CURRBONES", currBones);
                break;
            case 45:
                totalBones = totalBones + 4;
                currBones = currBones + 4;
                myEditor.putInt("TOTALBONES", totalBones);
                myEditor.putInt("CURRBONES", currBones);
                break;
            case 60:
                totalBones = totalBones + 6;
                currBones = currBones + 6;
                myEditor.putInt("TOTALBONES", totalBones);
                myEditor.putInt("CURRBONES", currBones);
                break;
        }

        //Add day
        switch (day) {
            case Calendar.SUNDAY:
                myEditor.putFloat("SUN.", selectedTime);
                myEditor.putString("sunDate", date);
                break;
            case Calendar.MONDAY:
                myEditor.putFloat("MON.", selectedTime);
                myEditor.putString("monDate", date);
                break;
            case Calendar.TUESDAY:
                myEditor.putFloat("TUES.", selectedTime);
                myEditor.putString("tuesDate", date);
                break;
            case Calendar.WEDNESDAY:
                myEditor.putFloat("WED.", selectedTime);
                myEditor.putString("wedDate", date);
                break;
            case Calendar.THURSDAY:
                myEditor.putFloat("THURS.", selectedTime);
                myEditor.putString("thursDate", date);
                break;
            case Calendar.FRIDAY:
                myEditor.putFloat("FRI.", selectedTime);
                myEditor.putString("friDate", date);
                break;
            case Calendar.SATURDAY:
                myEditor.putFloat("SAT.", selectedTime);
                myEditor.putString("satDate", date);
                break;
        }

        if (month == currMonth){
            //Current month
            monthTotal = monthTotal + selectedTime;
            myEditor.putFloat("MONTHTOTAL", monthTotal);
            myEditor.putFloat(currMonth+"MONTHTOTAL", monthTotal);
        }else{
            //New month
            monthTotal = 0 + selectedTime;
            myEditor.putFloat("MONTHTOTAL", monthTotal);
            myEditor.putFloat(currMonth+"MONTHTOTAL", monthTotal);
        }
        //Add month
        myEditor.putInt("YEAR", year);
        myEditor.putFloat("TOTALTIME", myPreferences.getFloat("TOTALTIME", 0) + selectedTime);
        myEditor.putInt("PREVIOUSTIME", selectedTime);
        myEditor.commit();

        /* Timer increments count when activity starts until unlock button is clicked. */
        t = new Timer();
        timeTextView = (TextView)findViewById(R.id.timeTextView);
        timeTextView.setText(
                String.format("%02d:%02d:%02d", pauseTime / 3600,
                        (pauseTime % 3600) / 60, (pauseTime % 60)));

        // if (active) {
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

                            /*
                            if (count == 2)
                               lockMeNow();
                            */

                            if (pauseTime == 0) {
                                startActivity(new Intent(PauseActivity.this, CongratsActivity.class));
                                finish();
                            }

                            pauseTime--;
                            count++;
                        }
                    });
                }
            }, 1000, 1000);
        // }



        Button unlockButton = (Button)findViewById(R.id.unlockButton);
        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.cancel();
                // onDestroy();
                finish();
            }
        });

    }

    /**
     * https://github.com/commonsguy/cw-omnibus/tree/master/DeviceAdmin/LockMeNow
     */
    public void lockMeNow() {
        Log.d("myTag","lockMeNow()");
        if (devicePolicyManager.isAdminActive(adminReciever)) {
            devicePolicyManager.lockNow();
            active = true;
        }
        else {
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminReciever);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                    getString(R.string.device_admin_explanation));
            startActivity(intent);
        }
    }

    /*
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.v("$$$$$$", "In Method: onDestroy()");

        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null;
        }

    }
    */

    public static void closeActivity(){
        activity.finish();
    }
}
