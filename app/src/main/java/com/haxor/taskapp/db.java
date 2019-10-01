package com.haxor.taskapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UsersDB";
    private static final String TABLE_NAME = "User";
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";


    public db(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqL) {

        String create_table =  "CREATE TABLE User("+" id INTEGER PRIMARY KEY AUTOINCREMENT, " + "email TEXT, " + "password TEXT)";
        sqL.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqL, int i, int i1) {
        sqL.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqL);

    }

    public Boolean addUser(String email, String pass){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL, email);
        values.put(PASSWORD, pass);

        long inserts = db.insert(TABLE_NAME, null , values);
        if (inserts==-1) return false;
        else return true;

    }
    public Boolean checkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where Email=?", new String[]{email});
        if (cursor.getCount() > 0 ){

            return false;
        }
        else return true;
    }

    public Boolean login(String email, String password){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select  * from User where Email=? and Password=?", new String[]{email,password});
        if (cursor.getCount() > 0){
            return true;
        }
        else return false;
    }

    public Boolean updateUser( String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();;
        content.put(EMAIL, email);
        content.put(PASSWORD, password);
        db.update(TABLE_NAME, content, "Email = ?", new String[] {email} );
        return true;
    }
}
