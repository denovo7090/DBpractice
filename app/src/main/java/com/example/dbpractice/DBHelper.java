package com.example.dbpractice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE WORK (_id INTEGER PRIMARY KEY AUTOINCREMENT, item TEXT, price INTEGER, create_at TEXT);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public void insert(String create_at, String item, int price) {

        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("INSERT INTO WORK VALUES(null, '" + item"','"+price+"','"+create_at+"');");
        db.close();

    }

    public void update( String item, int price){

        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE WORK SET price=" + price +" WHERE item = '"+item"';");
        db.close();
    }

    public void delete(String item) {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM WORK WHERE item = '" + item +"';");
        db.close();
    }

    public String getResult() {
        SQLiteDatabase db=getReadableDatabase();
        String result = "";

        Cursor cursor=db.rawQuery("SELECT *FROM WORK",null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + ":"
                    + cursor.getString(1)
                    +"|"
                    + cursor.getInt(2)
                    +"원"
                    + cursor.getString(3)
                    + "\n";
        }
        return result;

    }

}