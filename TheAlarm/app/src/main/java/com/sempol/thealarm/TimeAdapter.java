package com.sempol.thealarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.sempol.thealarm.model.TimeModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {
    Context context;
    List<TimeModel> times;
    final int pi_id = (int) System.currentTimeMillis();


    public TimeAdapter(Context context, List<TimeModel> times) {
        this.context = context;
        this.times = times;
    }

    @Override
    public TimeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_time,parent,false);

        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TimeAdapter.ViewHolder holder, final int position) {
        String text_times = convertor(times.get(position).getTime());//convert milis to clock format
        holder.txtTime.setText(text_times);

        holder.getAdapterPosition();

//        if (times.isEmpty()) {
//            .setVisibility(View.GONE);
//            emptyView.setVisibility(View.VISIBLE);
//        }
//        else {
//            recyclerView.setVisibility(View.VISIBLE);
//            emptyView.setVisibility(View.GONE);
//        }

        //this function is to delete alarm from RecyclerView
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position); //remove alarm
                Toast.makeText(context,"Alarm was deleted",Toast.LENGTH_LONG).show();
                stopAlarmManager();
            }
        });

        //this function is to set alarm via switch
        holder.alarm_swith.setChecked(true);
        if(holder.alarm_swith.isChecked()){
            setAlarm(times.get(position).getTime());
            text_times = convertor(times.get(position).getTime());
            Toast.makeText(context, "Alarm set at "+text_times, Toast.LENGTH_SHORT).show();
        }

        holder.alarm_swith.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked) {
                if(isCheked){
                    setAlarm(times.get(position).getTime());
                    String text_times = convertor(times.get(position).getTime()); //convert milis to clock format
                    Toast.makeText(context, "Alarm set at "+text_times, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Alarm Disabled", Toast.LENGTH_SHORT).show();
                    stopAlarmManager();
                }
            }
        });
    }

    private void removeItem(int position) { //Class to remove alarm from recyclerview

        times.remove(position);                        //remove alarm position
        notifyItemRemoved(position);                    //give a notify
        notifyItemRangeChanged(position,times.size());
    }

    @Override
    public int getItemCount() {
        return times.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTime;
        ImageView mDelete;
        Switch alarm_swith;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTime=(TextView) itemView.findViewById(R.id.digital_clock);
            mDelete = (ImageView) itemView.findViewById(R.id.delete);
            alarm_swith = (Switch) itemView.findViewById(R.id.alarm_switch);


        }
    }
    //this class used to convert milis to clock format
    private String convertor (long time){
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    //this class used to set the alarm
    public void setAlarm(long time) {
        //getting the alarm manager
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //creating a new intent specifying the broadcast receiver
        Intent intent = new Intent(context, AlarmReceiver.class);

        ///


        //creating a pending intent using the intent
        PendingIntent pi = PendingIntent.getBroadcast(context, pi_id, intent, PendingIntent.FLAG_ONE_SHOT);

        //setting alarm depend on SDK version
        if(Build.VERSION.SDK_INT >19){
            am.setExact(AlarmManager.RTC_WAKEUP,time,pi);
        }else{
            am.setRepeating(AlarmManager.RTC_WAKEUP,AlarmManager.INTERVAL_DAY,time,pi);
        }
    }

    public void stopAlarmManager() {

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);

        PendingIntent pi = PendingIntent.getBroadcast(context, pi_id, intent, PendingIntent.FLAG_ONE_SHOT);
        manager.cancel(pi);//cancel the alarm manager of the pending intent


        //Stop the Media Player Service to stop sound
        context.stopService(new Intent(context, AlarmReceiver.class));
    }



}


