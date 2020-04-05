package com.compsci702.compsci702app.Tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static String DATABASE_NAME = "WordBank.db";
    private static int SCHEMA_NUMBER = 1;

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,SCHEMA_NUMBER);
        this.context = context; //rb
        //SQLiteDatabase db = this.getWritableDatabase(); //rb
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //rb
        db.execSQL("create table Words(Id Integer Primary Key Autoincrement, Sentence text)");
    }

    //rb
    public void onDeleteAllRows()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ "Words");
    }

    //rb
    public void onInsert() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        List<String> list;
        list = new ArrayList<String>();
        list.add("It is not sunny outside");
        list.add("It is very cold inside");
        list.add("It is not very cold inside");
        int n = 0;
        for (int i = 0; i < list.size(); i++) {
            cv.put("Sentence", list.get(n));
            db.insert("Words", null, cv);
            n++;
        }
    }

    //rb
    public Cursor alldata(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Words", null);
        return cursor;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion) {
            copyDBFromResource();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) { super.onOpen(db); }

    public void createDB(){
        if(!dbExists()){ copyDBFromResource(); }
    }

    private boolean dbExists(){
        File databasePath = context.getDatabasePath(DATABASE_NAME);
        File temp = new File(databasePath.getPath());

        if (temp.exists()){ return true; };

        if (!temp.getParentFile().exists()) { temp.getParentFile().mkdirs(); }

        return false;
    }

    private void copyDBFromResource() {
        InputStream myInput;
        OutputStream myOutput;

        try
        {
            myInput = context.getAssets().open(DATABASE_NAME);
            File path = context.getDatabasePath(DATABASE_NAME);
            myOutput = new FileOutputStream(path);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = myInput.read(buffer)) > 0)
            {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
