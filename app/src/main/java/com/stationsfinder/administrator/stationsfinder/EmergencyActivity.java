package com.stationsfinder.administrator.stationsfinder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.stationsfinder.administrator.stationsfinder.adapter.EmergencyAdapter;
import com.stationsfinder.administrator.stationsfinder.database.SqliteDatabase;
import com.stationsfinder.administrator.stationsfinder.datamodel.emergency;

import java.util.List;

public class EmergencyActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private SqliteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        FrameLayout fLayout = (FrameLayout) findViewById(R.id.activity_to_do);
        //finding the recycler view from the xml
        RecyclerView  emergencyRecyclerVew = (RecyclerView)findViewById(R.id.Emergency_list);
        //define the layoutmanager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //set the linerlayout Manager in the recycler view
          emergencyRecyclerVew.setLayoutManager(linearLayoutManager);
          emergencyRecyclerVew.setHasFixedSize(true);

        mDatabase = new SqliteDatabase(this);
        List<emergency> allEmergencies = mDatabase.listEmergency();

        if(allEmergencies.size() > 0){
              emergencyRecyclerVew.setVisibility(View.VISIBLE);
            EmergencyAdapter mAdapter = new EmergencyAdapter(this, allEmergencies);
              emergencyRecyclerVew.setAdapter(mAdapter);

        }else {
              emergencyRecyclerVew.setVisibility(View.GONE);
            Toast.makeText(this, "There is no emergency in the database. Start adding now", Toast.LENGTH_LONG).show();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add new quick task
                addTaskDialog();
            }
        });
    }
//create the emergency record with the Task Dialog
    private void addTaskDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_emergency, null);

        final EditText Editname = (EditText)subView.findViewById(R.id.et_name);
        final EditText Editphone = (EditText)subView.findViewById(R.id.et_phone);
        final EditText Editaddress = (EditText)subView.findViewById(R.id.et_address);
        final EditText Editlat = (EditText)subView.findViewById(R.id.et_lat);
        final EditText Editlogti = (EditText)subView.findViewById(R.id.et_lg);
        final Spinner Spinnertownship=(Spinner)subView.findViewById(R.id.sp_township);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add new emergency record");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("ADD EMERGENCY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = Editname.getText().toString();
                final String phone =Editphone.getText().toString();
                final String address=Editaddress.getText().toString();
                final String lat=Editlat.getText().toString();
                final  String logti=Editlogti.getText().toString();
                final String township=Spinnertownship.getSelectedItem().toString();
                if(TextUtils.isEmpty(name)||
                        TextUtils.isEmpty(phone)||
                        TextUtils.isEmpty(address)||
                        TextUtils.isEmpty(lat)||
                        TextUtils.isEmpty(logti)||
                        TextUtils.isEmpty(township)){
                    Toast.makeText(EmergencyActivity.this, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{
                    emergency newEmergency = new emergency(name,phone,address,lat,logti,township);
                    mDatabase.addEmergency(newEmergency);
                    //refresh the activity
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(EmergencyActivity.this, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDatabase != null){
            mDatabase.close();
        }
    }
}
