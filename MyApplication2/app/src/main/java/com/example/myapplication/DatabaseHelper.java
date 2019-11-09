package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_Name="Quiz.db";
    public static final String Table_Name="Registration";
    public static final String Id="Id"; //variable for column = table col name
    public static final String Name="Name";
    public static final String Password="Password";
    public static final String Email="Email";

    //Database is Created inside the Constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_Name, null, 1);
    }

    //Table is Created in Oncreate
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Table_Name+"(Id Integer Primary Key AUTOINCREMENT,Name TEXT,Password Text,Email Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table If EXISTS "+Table_Name);
        onCreate(db);
    }

    public boolean insertData(String name,String password,String email)
    {
        SQLiteDatabase db =this.getWritableDatabase();//Creates Database and is here just for checking that db is created
        ContentValues contentValues = new ContentValues();
        contentValues.put(Name,name);
        contentValues.put(Password,password);
        contentValues.put(Email,email);

        long result=db.insert(Table_Name,null,contentValues);

        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean authenticate(String password,String email)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String[] columns = {"email"};
        String selection = "Email=? and Password=?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(Table_Name,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();

        if(count > 0)
            return true;
        else
            return false;
    }
}
