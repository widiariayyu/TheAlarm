package com.sempol.thealarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.sempol.thealarm.widget.AlarmPuzzle;

/**
 * Created by Firelord on 23/04/2018.
 */

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyAlarmBelal", "ALARM SOUNDED");
        Toast.makeText(context, "Alarm!! Alarm!! Alarm!!", Toast.LENGTH_SHORT).show();
                //this thing to start activity
        Intent puzzle = new Intent(context, AlarmPuzzle.class);
        context.startActivity(puzzle);

    }
}
