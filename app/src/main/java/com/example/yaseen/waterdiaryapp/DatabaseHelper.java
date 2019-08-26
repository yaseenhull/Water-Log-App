package com.example.yaseen.waterdiaryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "waterRecord.db";
    public static final String TABLE_NAME = "waterRecord_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "SHOWER";
    public static final String COL3 = "TOILET";
    public static final String COL4 = "HYGIENE";
    public static final String COL5 = "LAUNDRY";
    public static final String COL6 = "DISHES";
    public static final String COL7 = "DRINKING";
    public static final String COL8 = "COOKING";
    public static final String COL9 = "CLEANING";
    public static final String COL10 = "OTHER";
    public static final String COL11 = "TOTAL";
    public static final String COL12 = "DATE";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,1);
    }

    /**sql queries used to create database
     * table will be dropped if already exists
     * addData adds data into table
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, TOILET TEXT, HYGIENE TEXT, LAUNDRY TEXT, DISHES TEXT, DRINKING TEXT, COOKING TEXT, CLEANING TEXT, OTHER TEXT, TOTAL TEXT, SHOWER TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean addData(String shower, String toilet, String hygiene, String laundry, String dishes, String drinking, String cooking, String cleaning, String other, String total, String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, shower);
        contentValues.put(COL3, toilet);
        contentValues.put(COL4, hygiene);
        contentValues.put(COL5, laundry);
        contentValues.put(COL6, dishes);
        contentValues.put(COL7, drinking);
        contentValues.put(COL8, cooking);
        contentValues.put(COL9, cleaning);
        contentValues.put(COL10, other);
        contentValues.put(COL11, total);
        contentValues.put(COL12, text);


        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}
