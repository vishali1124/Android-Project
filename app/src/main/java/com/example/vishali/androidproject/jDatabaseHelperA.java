package com.example.vishali.androidproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.ParseException;

public class jDatabaseHelperA extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CleanIt.db";
    //janadi
    public static final String TABLE_NAME = "schedule_table";
    public static final String COL_1 = "PID";
    public static final String COL_2 = "CID";
    public static final String COL_3 = "CITY";
    public static final String COL_4 = "DATE";
    public static final String COL_5 = "TIME";

    //Erandi
    public static final String TABLE_NAME_e = "addPrice_table";
    public static final String COL_1_e = "ID";
    public static final String COL_2_e = "Garbage_Name";
    public static final String COL_3_e = "Price";

    //kirul
    public static final String TABLE_NAME_k = "student_table";
    public static final String COL_1_k = "ID";
    public static final String COL_2_k = "NAME";
    public static final String COL_3_k = "SURNAME";
    public static final String COL_4_k = "MARKS";
    public static final String COL_5_k = "DATE";


    public jDatabaseHelperA(Context context) {
        super(context, DATABASE_NAME, null, 1);    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //jandi
        db.execSQL("create table " + TABLE_NAME +" (PID INTEGER PRIMARY KEY AUTOINCREMENT,CID TEXT,CITY TEXT,DATE DATE,TIME TIME)");
        //erandi
        db.execSQL("create table " + TABLE_NAME_e +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Garbage_Name TEXT,Price INTEGER)");
        //kirul
        db.execSQL("create table " + TABLE_NAME_k + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER,DATE DATE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //jandi
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        //erandi
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_e);
        //kirul
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_k);
        onCreate(db);
    }
    //jandi
    public boolean insertData(String CID, String city, String date,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,CID);
        contentValues.put(COL_3,city);
        contentValues.put(COL_4,date);
        contentValues.put(COL_5,time);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //erandi
    public boolean insertData_e( String garbage_name, String garbage_price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_e,garbage_name);
        contentValues.put(COL_3_e,garbage_price);
        long result = db.insert(TABLE_NAME_e,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //kirul
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean insertData_k(String name, String surname, String marks, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME_k,null);
        int i = c.getCount();

        try {
            d = String.valueOf(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        contentValues.put(COL_1_k,i);
        contentValues.put(COL_2_k,name);
        contentValues.put(COL_3_k,surname);
        contentValues.put(COL_4_k,marks);
        contentValues.put(COL_5_k, d);
        long result = db.insert(TABLE_NAME_k,null,contentValues);
        if (result == -1){
            return false;
        }
        else
            return true;
    }


    //jandi
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


    //erandi
    public Cursor getAllData_e() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_e,null);
        return res;
    }

    //kirul
    public Cursor getAllData_k(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " +TABLE_NAME_k,null);
        return res;
    }


    //jandi
    public boolean updateData(String PID,String CID,String city,String date,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,PID);
        contentValues.put(COL_2,CID);
        contentValues.put(COL_3,city);
        contentValues.put(COL_4,date);
        contentValues.put(COL_4,time);

        db.update(TABLE_NAME, contentValues, "PID = ?",new String[] { PID });
        return true;
    }


    //erandi
    public boolean updateData_e(String id, String garbage_name, String garbage_price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_e,id);
        contentValues.put(COL_2_e,garbage_name);
        contentValues.put(COL_3_e,garbage_price);
        db.update(TABLE_NAME_e, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    //kirul
    public boolean updateData_k(String id, String name, String surname, String Marks, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_k,id);
        contentValues.put(COL_2_k,name);
        contentValues.put(COL_3_k,surname);
        contentValues.put(COL_4_k,Marks);
        contentValues.put(COL_5_k, date);
        db.update(TABLE_NAME_k,contentValues, "ID = ? ",new String[] { id });
        return true;
    }


    //jandi
    public Integer deleteData (String PID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "PID = ?",new String[] {PID});
    }

    //erandi
    public Integer deleteData_e (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_e, "ID = ?",new String[] {id});
    }

    //kirul
    public Integer deleteData_k(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_k, "ID = ? " , new String[] {id});
    }



}
