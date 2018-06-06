package com.sempol.thealarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

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
        puzzle.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        context.startActivity(puzzle);

    }
}
