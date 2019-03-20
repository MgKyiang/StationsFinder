package com.stationsfinder.administrator.stationsfinder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.stationsfinder.administrator.stationsfinder.datamodel.emergency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/12/2017.
 */

public class SqliteDatabase extends SQLiteOpenHelper {

    private	static final int DATABASE_VERSION =1;
    private	static final String	DATABASE_NAME = "stationsfinder";
    private	static final String TABLE_EMERGENCY = "emergencys";
    //defining the table column for the table
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE= "phone";
    private static final String COLUMN_ADDRESS= "address";
    private static final String COLUMN_LATITUTE= "latitute";
    private static final String COLUMN_LONGTITUTE= "longtitute";
    private static final String COLUMN_TOWNSHIP= "township";

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREATE_EMERGENCY_TABLE = "CREATE	TABLE " + TABLE_EMERGENCY
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_PHONE + " TEXT,"
                + COLUMN_ADDRESS + " TEXT,"
                + COLUMN_LATITUTE+ " TEXT,"
                + COLUMN_LONGTITUTE + " TEXT,"
                + COLUMN_TOWNSHIP + " TEXT" + ")";
        db.execSQL(CREATE_EMERGENCY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMERGENCY);
        onCreate(db);
    }
//get all emergency record from the table
    public List<emergency> listEmergency(){
        String sql = "select * from " + TABLE_EMERGENCY;
        SQLiteDatabase db = this.getReadableDatabase();
        List<emergency> storeEmergency = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String phone=cursor.getString(2);
                String address=cursor.getString(3);
                String lat=cursor.getString(4);
                String logti=cursor.getString(5);
                String township=cursor.getString(6);
                storeEmergency.add(new emergency(id, name, phone,address,lat,logti,township));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmergency;
    }
// save the emergency record into table
    public void addEmergency(emergency emergency){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, emergency.getName());
        values.put(COLUMN_PHONE, emergency.getPhoneno());
        values.put(COLUMN_ADDRESS,emergency.getAddress());
        values.put(COLUMN_LATITUTE,emergency.getLatitute());
        values.put(COLUMN_LONGTITUTE,emergency.getLongtitute());
        values.put(COLUMN_TOWNSHIP,emergency.getTownship());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_EMERGENCY, null, values);
    }
//update the emergency record to the table
    public void updateEmergency(emergency emergency){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, emergency.getName());
        values.put(COLUMN_PHONE, emergency.getPhoneno());
        values.put(COLUMN_ADDRESS,emergency.getAddress());
        values.put(COLUMN_LATITUTE,emergency.getLatitute());
        values.put(COLUMN_LONGTITUTE,emergency.getLongtitute());
        values.put(COLUMN_TOWNSHIP,emergency.getTownship());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_EMERGENCY, values, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(emergency.getId())});
    }
//find the emergency record by the emenrgnecy name
    public emergency findEmergency(String name){
        String query = "Select * FROM "	+ TABLE_EMERGENCY + " WHERE " + COLUMN_NAME + " = " + "name";
        SQLiteDatabase db = this.getWritableDatabase();
        emergency mEmergency = null;
        Cursor cursor = db.rawQuery(query,	null);
        //looping the record the cusor
        if	(cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            String ename = cursor.getString(1);
            String phone=cursor.getString(2);
            String address=cursor.getString(3);
            String lat=cursor.getString(4);
            String logti=cursor.getString(5);
            String township=cursor.getString(6);
            mEmergency = new emergency(id,ename, phone,address,lat,logti,township);
        }
        cursor.close();
        return mEmergency;
    }
//delete the emergency record by the emergency id
    public void deleteEmergency(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMERGENCY, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }
}