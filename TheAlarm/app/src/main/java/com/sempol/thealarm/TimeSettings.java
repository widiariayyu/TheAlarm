package com.sempol.thealarm;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sempol.thealarm.model.TimeModel;

public class TimeSettings implements TimePickerDialog.OnTimeSetListener {
    Context context;
    TextView resultTime;

    public TimeSettings(TextView textView) {
        resultTime = textView;
    }

    public TimeSettings (Context context)
    {
        this.context = context;

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(context,"Selected time is hour:"+hourOfDay+" minute :"+minute,Toast.LENGTH_LONG).show();
        MainActivity.times.add(new TimeModel(hourOfDay + ":" + minute));
        MainActivity.adapter.notifyDataSetChanged();
    }

}
