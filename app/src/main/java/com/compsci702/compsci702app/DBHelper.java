package com.compsci702.compsci702app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static String DATABASE_NAME = "WordBank.db";
    private static int SCHEMA_NUMBER = 1;


    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,SCHEMA_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(newVersion>oldVersion) {
            copyDBFromResource();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public void createDB(){
        if(!dbExists()){
            copyDBFromResource();
        }

    }

    private boolean dbExists(){

        File databasePath = context.getDatabasePath(DATABASE_NAME);
        File temp = new File(databasePath.getPath());

        if (temp.exists()) return true;

        if (!temp.getParentFile().exists()) {
            temp.getParentFile().mkdirs();
        }

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
