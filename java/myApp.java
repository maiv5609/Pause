package com.example.maiv.pause;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by maiv on 2/12/18.
 */

public class myApp extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        SystemClock.sleep(2000);
    }
}
