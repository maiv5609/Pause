package com.pause;

/**
 * Created by maiv on 2/12/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;

import java.io.*;

import java.io.FileInputStream;

public class SplashActivity extends AppCompatActivity {
    String fileContents = "Test";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        boolean existingUser = true;
        super.onCreate(savedInstanceState);

        //After creating page make sure to check if the user has any data
        //Attempt opening user file data, otherwise create
        String USERFILE = getString(R.string.userData);
        try{
            FileInputStream userData = openFileInput(USERFILE);
        }catch(FileNotFoundException e){
            //Userfile not found, new user or file was deleted
            existingUser = false;
        }

        //If new user create new file
        if(existingUser == false){
            try{
                FileOutputStream userData = openFileOutput(USERFILE, MODE_PRIVATE);
                //Inital setup of file
                userData.write(fileContents.getBytes());
                userData.close();
            }catch(Exception e){
                //Error creating file
                e.printStackTrace();
            }
        }

        //By this point user information should be setup and you can move onto next screen

        /**
         * Delay 1000 ms to display splash activity and forward along to main activity.
         */
        Handler handle=new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Transfer to breed select if no user data found for dog, otherwise go to main activity
                Intent intent = new Intent(SplashActivity.this, BreedSelect.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        },1500);

    }
}