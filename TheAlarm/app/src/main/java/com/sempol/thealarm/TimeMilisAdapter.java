package com.sempol.thealarm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.sempol.thealarm.model.TimeMilis;
import com.sempol.thealarm.model.TimeModel;

import java.util.List;

/**
 * Created by Firelord on 29/04/2018.
 */

public class TimeMilisAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {
    private final Context context;
    
    List<TimeMilis> timesMillis;

    public TimeMilisAdapter(Context context, List<TimeMilis> timesMillis) {
        this.context = context;
        this.timesMillis = timesMillis;
    }

    @Override
    public TimeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TimeAdapter.ViewHolder holder, final int position) {
        holder.getAdapterPosition();

        holder.alarm_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked){
                    Toast.makeText(context, "alarm is online", Toast.LENGTH_SHORT).show();
//                    MainActivity mainActivity = new MainActivity();
                    Toast.makeText(context, ""+ timesMillis.get(position).getAlarm_time(), Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(context, "alarm is offline", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return timesMillis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Switch alarm_switch;
        public ViewHolder(View itemView) {
            super(itemView);
            alarm_switch = (Switch) itemView.findViewById(R.id.alarm_switch);


        }
    }
}
