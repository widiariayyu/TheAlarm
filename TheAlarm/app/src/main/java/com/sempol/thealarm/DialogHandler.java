package com.sempol.thealarm;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;



public class DialogHandler extends DialogFragment {
//    TextView TimeDisplay;

//    public DialogHandler(TextView textView) {
//        TimeDisplay = textView;
//    }

    @NonNull
    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        int hour= calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);


        TimePickerDialog dialog;
        TimeSettings timeSettings = new TimeSettings(getActivity());
            dialog = new TimePickerDialog(getActivity(),timeSettings,hour,minute, DateFormat.is24HourFormat(getActivity()));
            return dialog;



    }
//    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
//        String time= null;
//        TimeDisplay.setText(time);
//    }
}
