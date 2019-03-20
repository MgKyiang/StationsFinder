package com.stationsfinder.administrator.stationsfinder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stationsfinder.administrator.stationsfinder.R;

/**
 * Created by Administrator on 12/12/2017.
 */

public class EmergencyViewHolder extends RecyclerView.ViewHolder {

public TextView name,phone,township;
public ImageView deleteEmergency;
public ImageView editEmergency;

public EmergencyViewHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.emergency_name);
        phone = (TextView)itemView.findViewById(R.id.emergency_phone);
        township = (TextView)itemView.findViewById(R.id.emergency_township);
        deleteEmergency = (ImageView)itemView.findViewById(R.id.delete_emergency);
        editEmergency = (ImageView)itemView.findViewById(R.id.edit_emergency);
        }
}

