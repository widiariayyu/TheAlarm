package com.sempol.thealarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sempol.thealarm.model.TimeModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimePickerHandler extends AppCompatActivity {
    Button btn_cancel, btn_save;
    TimePicker timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_handler);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_save = (Button) findViewById(R.id.btn_save);

        timePicker.setIs24HourView(true);

        //cancel timpicker
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });//end

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                if(android.os.Build.VERSION.SDK_INT >=23){
                    calendar.set(calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(),
                            timePicker.getMinute(), 0);
                }else{
                    calendar.set(calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute(), 0);
                }//endIfElse


                long times = calendar.getTimeInMillis();

                ///add timmillis to TimeModel
                MainActivity.times.add(new TimeModel(times));
                MainActivity.adapter.notifyDataSetChanged();

                finish();
            }
        });
    }

///UNUSED
//    private void setAlarm(long time) {
//        //getting the alarm manager
//        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//
//        //creating a new intent specifying the broadcast receiver
//        Intent i = new Intent(this, AlarmReceiver.class);
//
//        //creating a pending intent using the intent
//        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
//
//        //setting the repeating alarm that will be fired every day
//        if(Build.VERSION.SDK_INT >19){
//            am.setExact(AlarmManager.RTC,time,pi);
//        }else{
//            am.setRepeating(AlarmManager.RTC,AlarmManager.INTERVAL_DAY,time,pi);
//        }
//    }
//
//    private String convertor (long time){
//        Date date = new Date(time);
//        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
//        return format.format(date);
//    }

}
