package com.example.supermarket;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SuperMarketDBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "supermarkets.db";
    private static final int DATABASE_VERSION = 1;

    //database creation sql statement

    private static final String CREATE_TABLE_SUPERMARKET =
                    "CREATE TABLE supermarket (_id integer primary key autoincrement,"
                    + "superMarketName TEXT not null, streetAddress TEXT, "
                    + "city TEXT, state TEXT, zipCode TEXT, rating TEXT);" ;

    public SuperMarketDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SUPERMARKET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SuperMarketDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                      + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS supermarket");
        onCreate(db);

    }

}
