package com.stationsfinder.administrator.stationsfinder.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.stationsfinder.administrator.stationsfinder.R;
import com.stationsfinder.administrator.stationsfinder.database.SqliteDatabase;
import com.stationsfinder.administrator.stationsfinder.datamodel.emergency;

import java.util.List;

/**
 * Created by Administrator on 12/12/2017.
 */

public class EmergencyAdapter extends  RecyclerView.Adapter<EmergencyViewHolder>{

    private Context context;
    private List<emergency> emergencyList;
    //create the instance of database
    private SqliteDatabase mDatabase;

    public EmergencyAdapter(Context context, List<emergency> emergencyList) {
        this.context = context;
        this.emergencyList = emergencyList;
        mDatabase = new SqliteDatabase(context);
    }

    @Override
    public EmergencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_emergency, parent, false);
        return new EmergencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmergencyViewHolder holder, int position) {
        final emergency emergency = emergencyList.get(position);

        holder.name.setText(emergency.getName());
        holder.phone.setText(emergency.getPhoneno());
        holder.township.setText(emergency.getTownship());
        //find the edit control and set click listener
        holder.editEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(emergency);
            }
        });
        //find the edit control and set click listener
        holder.deleteEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from database
                confirmDialog(emergency);
            }
        });
    }

    @Override
    public int getItemCount() {
        return emergencyList.size();
    }


    private void editTaskDialog(final emergency emergency){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_emergency, null);

        final EditText Editname = (EditText)subView.findViewById(R.id.et_name);
        final EditText Editphone = (EditText)subView.findViewById(R.id.et_phone);
        final EditText Editaddress = (EditText)subView.findViewById(R.id.et_address);
        final EditText Editlat = (EditText)subView.findViewById(R.id.et_lat);
        final EditText Editlogti = (EditText)subView.findViewById(R.id.et_lg);
        final Spinner Spinnertownship=(Spinner)subView.findViewById(R.id.sp_township);
        if(emergency != null){
            Editname.setText(emergency.getName());
            Editphone.setText(String.valueOf(emergency.getPhoneno()));
            Editaddress.setText(emergency.getAddress());
            Editlat.setText(emergency.getLatitute());
            Editlogti.setText(emergency.getLongtitute());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit emergency");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDIT EMERGENCY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = Editname.getText().toString();
                final String phone =Editphone.getText().toString();
                final  String address=Editaddress.getText().toString();
                final String lat=Editlat.getText().toString();
                final  String logti=Editlogti.getText().toString();
                final String township=Spinnertownship.getSelectedItem().toString();
                if(TextUtils.isEmpty(name)||
                        TextUtils.isEmpty(phone)||
                        TextUtils.isEmpty(address)||
                        TextUtils.isEmpty(lat)||
                        TextUtils.isEmpty(logti)||
                        TextUtils.isEmpty(township)){
                    Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{
                    mDatabase.updateEmergency(new emergency(emergency.getId(),name,phone,address,lat,logti,township));
                    //refresh the activity
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
    private void confirmDialog(final emergency emergency) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder
                .setMessage("Are you sure to delete this record?")
                .setPositiveButton("Yes",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Yes-code
                        mDatabase.deleteEmergency(emergency.getId());
                        //refresh the activity page.
                        ((Activity)context).finish();
                        context.startActivity(((Activity) context).getIntent());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}
