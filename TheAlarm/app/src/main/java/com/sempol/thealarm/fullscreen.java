package com.sempol.thealarm;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class fullscreen extends AppCompatActivity {
    private LinearLayout splashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        setupEnv();
        setTimer(1000, MainActivity.class);
    }

    public void setTimer(int delay, final Class intent){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), intent));
                finish();
            }
        }, delay);

        }

    private void setupEnv() {
        splashView = (LinearLayout) findViewById(R.id.splashView);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT) {
            splashView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }
}
