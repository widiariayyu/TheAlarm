package com.sempol.thealarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

/**
 * Created by Firelord on 23/04/2018.
 */

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context,
                Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaPlayer.start();
    }
}
