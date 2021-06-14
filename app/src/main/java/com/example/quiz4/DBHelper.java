package com.example.quiz4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class DBHelper  extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context,"Student.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table Students(id TEXT primary key,name TEXT, cgpa TEXT, university TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Students");

    }
    public boolean insertData(String name, String id, String cgpa, String university)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("id",id);
        cv.put("cgpa",cgpa);
        cv.put("university",university);
        long result = db.insert("Students",null,cv);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean updatestudentData(String name, String id, String cgpa, String university)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",id);
        cv.put("cgpa",cgpa);
        cv.put("university",university);
        Cursor c = db.rawQuery("Select * from Students where name=?", new String[]{name});
        if(c.getCount()>0) {

            long result = db.update("Students", cv, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false;
        }
    }
    public boolean deleteData(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("Select * from Students where name=?", new String[]{name});
        if(c.getCount()>0) {
            long result = db.delete("Students", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false;
        }
    }
    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("Select * from Students",null);
        return c;

        }
    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        String clearDBQuery = "DELETE FROM STUDENTS" ;
        db.execSQL(clearDBQuery);
    }
}
