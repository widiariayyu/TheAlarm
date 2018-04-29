package com.sempol.thealarm.model;

/**
 * Created by Firelord on 29/04/2018.
 */

public class TimeMilis {
   private long [] alarm_time;

    public TimeMilis(long[] alarm_time) {
        this.alarm_time = alarm_time;
    }

    public long[] getAlarm_time() {
        return alarm_time;
    }

    public void setAlarm_time(long[] alarm_time) {
        this.alarm_time = alarm_time;
    }
}
    