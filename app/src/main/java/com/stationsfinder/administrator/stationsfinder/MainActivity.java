package com.stationsfinder.administrator.stationsfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.stationsfinder.administrator.stationsfinder.adapter.*;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    String[] str={"Police Station","Fire Station","Emergency","Soldier Station"};
    int[] image={R.drawable.policeman,R.drawable.fireman,R.drawable.ambulance,R.drawable.policeman};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv= (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        Recycleradapter adapter=new  Recycleradapter(image,str);

        adapter.onCustomClick(new Recycleradapter.customclick() {
            @Override
            public void click(int pos) {
                if (pos==2){
                    startActivity(new Intent(MainActivity.this, EmergencyActivity.class));
                }
            }
        });
        rv.setAdapter(adapter);

    }
}
