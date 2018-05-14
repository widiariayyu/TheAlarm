package com.sempol.thealarm;
/**
    *class ini untuk menangani click event dari TimePickerDialog
    */
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
//        MainActivity.times.add(new TimeModel(hourOfDay + ":" + minute));
        MainActivity.adapter.notifyDataSetChanged();

    }

}
/**
 this class is not used
 */
