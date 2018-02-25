package com.pause;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by summerturner on 2/24/18.
 */

public class PauseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pause_layout);

        Button unlockButton = (Button)findViewById(R.id.unlockButton);
        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: stop Do Not Disturb and time count

                finish();
            }
        });

    }
}
