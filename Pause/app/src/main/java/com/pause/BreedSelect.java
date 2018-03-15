package com.pause;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.TextView;

import org.w3c.dom.Text;


public class BreedSelect extends AppCompatActivity {
    ImageButton prevBreed;
    ImageButton nextBreed;
    ImageView currentImage;
    TextView currentName;
    Button breedSelect;

    String currentBreed;
    int breedItr = 1;
    SharedPreferences myPreferences;

   

    //https://stackoverflow.com/questions/12249495/android-imagebutton-change-image-onclick
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create new preference file or get if already created
        myPreferences = this.getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);
        boolean existingUser = myPreferences.getBoolean("USER?", false);
        Log.d("USER?", "existingUser: "+ existingUser);

        //If not existing user, create preference file
        if(!existingUser) {
            SharedPreferences.Editor myEditor = myPreferences.edit();
            myEditor.putBoolean("USER?", true);

            myEditor.commit();
        }else{
            Intent intent = new Intent(BreedSelect.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        //Get picture of breed
        setContentView(R.layout.activity_breed_select);
        currentImage = (ImageView)findViewById(R.id.currentBreed);
        //Get name of breed
        currentName = (TextView)findViewById(R.id.breedName);

        //Button listener, onclick change image
        prevBreed = (ImageButton)findViewById(R.id.prevBreed);
        nextBreed = (ImageButton)findViewById(R.id.nextBreed);
        prevBreed.setOnClickListener(prevImage);
        nextBreed.setOnClickListener(nextImage);

        breedSelect = (Button)findViewById(R.id.breedSelectButton);
        breedSelect.setOnClickListener(selectBreed);
    }


/*
    1   Beagle
    2   Shiba
    3   Shihtzu
 */

    View.OnClickListener nextImage = new OnClickListener() {
        @Override
        public void onClick(View v) {
            //Change to next breed
            switch(breedItr) {
                case 1: currentImage.setImageResource(R.drawable.shiba);
                    currentName.setText("Shiba");
                    breedItr = 2;
                    currentBreed = "shiba";
                    break;
                case 2: currentImage.setImageResource(R.drawable.shih_tzu);
                    currentName.setText("Shih Tzu");
                    breedItr = 3;
                    currentBreed = "shihtzu";
                    break;
                case 3: currentImage.setImageResource(R.drawable.beagle);
                    currentName.setText("Beagle");
                    breedItr = 1;
                    currentBreed = "beagle";
                    break;
            }
        }
    };

    View.OnClickListener prevImage = new OnClickListener() {
        public void onClick(View v) {
            //Change to prev breed
            switch(breedItr) {
                case 1: currentImage.setImageResource(R.drawable.shih_tzu);
                    currentName.setText("Shih Tzu");
                    breedItr = 3;
                    currentBreed = "shihtzu";
                    break;
                case 2: currentImage.setImageResource(R.drawable.beagle);
                    currentName.setText("Beagle");
                    breedItr = 1;
                    currentBreed = "beagle";
                    break;
                case 3: currentImage.setImageResource(R.drawable.shiba);
                    currentName.setText("Shiba");
                    breedItr = 2;
                    currentBreed = "shiba";
                    break;
            }
        }
    };

    View.OnClickListener selectBreed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Save breed to preference file
            SharedPreferences.Editor myEditor = myPreferences.edit();
            myEditor.putString("BREED", currentBreed);
            myEditor.commit();

            Intent intent = new Intent(BreedSelect.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

}
