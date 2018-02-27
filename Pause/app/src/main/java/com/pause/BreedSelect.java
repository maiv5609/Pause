package com.pause;

import android.media.Image;
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
    }


    View.OnClickListener prevImage = new OnClickListener() {
        public void onClick(View v) {
            //Change to prev image if available
            currentImage.setImageResource(R.drawable.beagle);
        }
    };

    View.OnClickListener nextImage = new OnClickListener() {
        @Override
        public void onClick(View v) {
            //Change to next image if available
            currentImage.setImageResource(R.drawable.beagle2);
            }
    };

    View.OnClickListener selectBreed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(BreedSelect.this,MainActivity.class));
        }
    };

}
