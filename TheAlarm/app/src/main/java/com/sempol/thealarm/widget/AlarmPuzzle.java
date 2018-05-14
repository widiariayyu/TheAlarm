package com.sempol.thealarm.widget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.sempol.thealarm.AlarmReceiver;
import com.sempol.thealarm.R;
import com.sempol.thealarm.TimeAdapter;

public class AlarmPuzzle extends AppCompatActivity {
    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.alarm_puzzle);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this,
                Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaPlayer.start();

        btn_confirm = (Button) findViewById(R.id.btn_puzzle);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                finish();
            }
        });
    }

}
