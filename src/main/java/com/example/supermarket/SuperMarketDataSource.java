package com.example.supermarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SuperMarketDataSource {
    private SQLiteDatabase database;
    private SuperMarketDBHelper dbHelper;

    public SuperMarketDataSource(Context context) {
        dbHelper = new SuperMarketDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertSuperMarket(SuperMarket s) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("superMarketName", s.getName());
            initialValues.put("streetAddress", s.getStreetAddress());
            initialValues.put("city", s.getCity());
            initialValues.put("state", s.getState());
            initialValues.put("zipCode", s.getZipCode());
            initialValues.put("rating",s.getRating());
            didSucceed = database.insert("supermarket", null, initialValues) > 0;
        } catch (Exception e) {
            //do nothing
        }
        return didSucceed;
    }

    public boolean updateSuperMarket(SuperMarket s) {
        boolean didSucceed = false;
        try {
            long rowID = (long) s.getMarketID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("superMarketName", s.getName());
            updateValues.put("streetAddress", s.getStreetAddress());
            updateValues.put("city", s.getCity());
            updateValues.put("state", s.getState());
            updateValues.put("zipCode", s.getZipCode());
            updateValues.put("rating",s.getRating());

            didSucceed = database.update("supermarket", updateValues, "_id=" + rowID, null) > 0;
        } catch (Exception e) {
            //do nothing
        }
        return didSucceed;
    }

    public int getLastMarketID() {


    int lastID;


    try

    {
        String query = "SELECT MAX (_id) from supermarket";
        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        lastID = cursor.getInt(0);
        cursor.close();

    }
    catch(
    Exception e)

    {
        lastID = -1;
    }
    return lastID;
}

public ArrayList<SuperMarket> getSuperMarkets(String sortField, String sortOrder){
        ArrayList<SuperMarket> superMarkets = new ArrayList<>();

        try{
            String query = " SELECT * FROM supermarket ORDER BY " + sortField + " " + sortOrder;
            Cursor cursor = database.rawQuery(query,null);

            SuperMarket newSuperMarket;
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                newSuperMarket = new SuperMarket();
                newSuperMarket.setMarketID(cursor.getInt(0));
                newSuperMarket.setName(cursor.getString(1));
                newSuperMarket.setStreetAddress(cursor.getString(2));
                newSuperMarket.setCity(cursor.getString(3));
                newSuperMarket.setState(cursor.getString(4));
                newSuperMarket.setZipCode(cursor.getString(5));
                newSuperMarket.setRating(cursor.getString(6));
                superMarkets.add(newSuperMarket);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch(Exception e){
            superMarkets = new ArrayList<>();
        }
        return superMarkets;
}

public SuperMarket getSpecificSuperMarket(int marketID){

        SuperMarket superMarket = new SuperMarket();

        String query = "SELECT * FROM supermarket WHERE _id = " + marketID;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            superMarket.setMarketID(cursor.getInt(0));
            superMarket.setName(cursor.getString(1));
            superMarket.setStreetAddress(cursor.getString(2));
            superMarket.setCity(cursor.getString(3));
            superMarket.setState(cursor.getString(4));
            superMarket.setZipCode(cursor.getString(5));
            superMarket.setRating(cursor.getString(6));

            cursor.close();

        }
        return superMarket;

}

public boolean deleteSupermarket(int marketID){
        boolean didDelete = false;
        try{
            didDelete = database.delete("supermarket", "_id= " + marketID, null) > 0;

        }catch(Exception e){
            //does nothing
        }
        return didDelete;
}

}
