package com.compsci702.compsci702app.Tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.compsci702.compsci702app.Activity.InputProcess;


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
        //db.execSQL("create table Words(Id Integer Primary Key Autoincrement, Sentence text)");
        db.execSQL("create table Words(Id Integer Primary Key Autoincrement, Sentence BLOB)");

    }

    //rb
//    public void onDeleteAllRows()
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("delete from "+ "Words");
//    }





    //rb
//    public void onInsert() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        List<String> list;
//        list = new ArrayList<String>();
//        list.add("An apple a day keeps the doctor away");
//        list.add("Better late then never");
//        list.add("What comes around goes around");
//        list.add("Laughter is the best medicine");
//        list.add("Run like the wind");
//        list.add("A blessing in disguise");
//        list.add("The grass is greener on the other side");
//        list.add("Once in a blue moon");
//        list.add("Between a rock and a hard place");
//        list.add("Bobs your uncle");
//        list.add("Bury your head in the sand");
//        list.add("Cheap as chips");
//        list.add("Bobs your uncle");
//        list.add("Bury your head in the sand");
//        list.add("Getting cold feet");
//        list.add("Curiosity killed the cat");
//        list.add("Don't put all your eggs in one basket");
//        list.add("Desperate times call for desperate measures");
//        list.add("Head over heels in love");
//        list.add("Hit the nail on the head");
//        list.add("Ignorance is bliss");
//        list.add("Kill two birds with one stone");
//        list.add("Leave no stone unturned");
//        list.add("No pain no gain");
//        list.add("On the straight and narrow");
//        list.add("Pulling my leg");
//        list.add("Sitting on the fence");
//        list.add("Speaking of the devil");
//        list.add("Take it with a pinch of salt");
//        list.add("Time flies when you are having fun");
//        list.add("Don't judge a book by its cover");
//        list.add("Seeing eye to eye");
//        list.add("The cats out of the bag");
//        list.add("Looking like a million dollars");
//        list.add("Give the benefit of the doubt");
//        list.add("Go back to the drawing board");
//        list.add("Go the extra mile");
//        list.add("Chip on your shoulder");
//        list.add("Cost an arm and a leg");
//        list.add("Cut a long story short");
//        list.add("Don't put all your eggs in one basket");
//        list.add("Don't run before you can walk");
//        list.add("Take it one step at a time");
//        list.add("Bite the bullet");
//        list.add("Bite off more than you can chew");
//        list.add("Bark up the wrong tree");
//        list.add("Don't beat around the bush");
//        list.add("Actions speak louder than words");
//        list.add("Add insult to injury");
//        list.add("Adding fuel to the fire");
//        list.add("A stones throw away");
//        list.add("A sandwich short of a picnic");
//        list.add("A blessing in disguise");
//
//        int n = 0;
//        for (int i = 0; i < list.size(); i++) {
//            //Encrypt fn comment after encryption
//            InputProcess ip = new InputProcess();
//            byte[] byteCipherText;
//            byteCipherText = ip.encrypt(list.get(n));
//            //System.out.println("byteCipherText " + byteCipherText);
////          cv.put("Sentence", list.get(n));
//            cv.put("Sentence", byteCipherText);
//            db.insert("Words", null, cv);
//            n++;
//        }
//    }

    //rb
    public Cursor alldata(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Words", null);
        return cursor;
    }

    public Cursor getDataFromDatabase(int listLength){
        SQLiteDatabase db = this.getReadableDatabase();
        //System.out.println(Integer.toString(listLength));
        Cursor cursor = db.rawQuery("SELECT * FROM Words WHERE id IN (SELECT id FROM Words ORDER BY RANDOM() LIMIT ? )",
                new String[]{Integer.toString(listLength)});
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