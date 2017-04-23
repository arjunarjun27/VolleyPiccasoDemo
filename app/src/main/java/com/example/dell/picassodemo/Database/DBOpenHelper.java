package com.example.dell.picassodemo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dell on 4/15/2017.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    /* DATABASE VERSION */

    public static final int DATABASE_VERSION = 1;

    /*
     * TABLE STRING
     * */
    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA = ", ";

    /**
     * sql create statement
     */
    public static final String CREATE_POST_TABLE = "CREATE TABLE "
            + DatabaseContract.PostTable.TABLE_NAME + " ("
            + DatabaseContract.PostTable.TITLE + TEXT_TYPE + COMMA
            + DatabaseContract.PostTable.LINK + TEXT_TYPE + COMMA
            + DatabaseContract.PostTable.IMAGELINK + TEXT_TYPE + " )";

    public static final String DROP_POST_TABLE = "DROP TABLE IF EXISTS " + DatabaseContract.PostTable.TABLE_NAME;


    public DBOpenHelper(Context context) {
        super(context, DatabaseContract.DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_POST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_POST_TABLE);
        onCreate(db);

    }
}
