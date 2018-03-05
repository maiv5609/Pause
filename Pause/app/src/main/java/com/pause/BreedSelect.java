package com.pause;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Intent;


public class BreedSelect extends AppCompatActivity {
    ImageButton prevBreed;
    ImageButton nextBreed;
    ImageView currentImage;
    Button breedSelect;

    String currentBreed;
    SharedPreferences myPreferences;

    //https://stackoverflow.com/questions/12249495/android-imagebutton-change-image-onclick
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_select);
        currentImage = (ImageView)findViewById(R.id.currentBreed);

        //Button listener, onclick change image
        prevBreed = (ImageButton)findViewById(R.id.prevBreed);
        nextBreed = (ImageButton)findViewById(R.id.nextBreed);
        prevBreed.setOnClickListener(prevImage);
        nextBreed.setOnClickListener(nextImage);

        breedSelect = (Button)findViewById(R.id.breedSelectButton);
        breedSelect.setOnClickListener(selectBreed);


        myPreferences = PreferenceManager.getDefaultSharedPreferences(BreedSelect.this);
        boolean existingUser = myPreferences.getBoolean("USER?", false);

        if(!existingUser) {
            SharedPreferences.Editor myEditor = myPreferences.edit();
            myEditor.putBoolean("USER?", true);

            myEditor.commit();
        }

    }


    View.OnClickListener prevImage = new OnClickListener() {
        public void onClick(View v) {
            //Change to prev image if available
            currentImage.setImageResource(R.drawable.shiba);
            currentBreed = "shiba";
        }
    };

    View.OnClickListener nextImage = new OnClickListener() {
        @Override
        public void onClick(View v) {
            //Change to next image if available
            currentImage.setImageResource(R.drawable.beagle);
            currentBreed = "beagle";
            }
    };

    View.OnClickListener selectBreed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            SharedPreferences.Editor myEditor = myPreferences.edit();
            myEditor.putString("BREED", currentBreed);
            myEditor.commit();


            Intent intent = new Intent(BreedSelect.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

}
