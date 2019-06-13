package org.tensorflow.lite.examples.classification;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.tensorflow.lite.examples.classification.FeedReaderContract.FeedEntry.SQL_CREATE_INGREDIENT;
import static org.tensorflow.lite.examples.classification.FeedReaderContract.FeedEntry.SQL_CREATE_RECIPE;
import static org.tensorflow.lite.examples.classification.FeedReaderContract.FeedEntry.SQL_CREATE_RECIPE_INFO;
import static org.tensorflow.lite.examples.classification.FeedReaderContract.FeedEntry.SQL_CREATE_STEPS;
import static org.tensorflow.lite.examples.classification.FeedReaderContract.FeedEntry.SQL_DELETE_INGREDIENT;
import static org.tensorflow.lite.examples.classification.FeedReaderContract.FeedEntry.SQL_DELETE_RECIPE;
import static org.tensorflow.lite.examples.classification.FeedReaderContract.FeedEntry.SQL_DELETE_RECIPE_INFO;
import static org.tensorflow.lite.examples.classification.FeedReaderContract.FeedEntry.SQL_DELETE_STEPS;


public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Recetas.db";


    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_RECIPE);
        db.execSQL(SQL_CREATE_INGREDIENT);
        db.execSQL(SQL_CREATE_STEPS);
        db.execSQL(SQL_CREATE_RECIPE_INFO);
    }
    public void onDelete(SQLiteDatabase db) {

        db.execSQL(SQL_DELETE_RECIPE_INFO);
        db.execSQL(SQL_DELETE_STEPS);
        db.execSQL(SQL_DELETE_INGREDIENT);
        db.execSQL(SQL_DELETE_RECIPE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        onDelete(db);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public void onInsert(SQLiteDatabase db, InputStream recipe_is, InputStream ingredient_is,
                         InputStream step_is, InputStream info_is){


        //read RECIPE inputStream
        CustomCSVReader customCsvReader = new CustomCSVReader(recipe_is);
        try {
            List<String[]> rows;

            rows = customCsvReader.readCSV();

            for (String[] row:rows){
                ContentValues values = new ContentValues();

                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_NAME, row[1]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_DESCRIPTION, row[2]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TIME, row[3]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TYPE, row[4]);

                db.insert(FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE, null, values);
                Log.v("RECORD INSERTED IN DB",FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //read INGREDIENT inputStream
        customCsvReader = new CustomCSVReader(ingredient_is);
        try {
            List<String[]> rows;

            rows = customCsvReader.readCSV();

            for (String[] row:rows){
                ContentValues values = new ContentValues();

                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_INGREDIENT_NAME, row[1]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_INGREDIENT_RECIPE, row[2]);

                db.insert(FeedReaderContract.FeedEntry.TABLE_NAME_INGREDIENT, null, values);
                Log.v("RECORD INSERTED IN DB",FeedReaderContract.FeedEntry.TABLE_NAME_INGREDIENT);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //read STEPS inputStream
        customCsvReader = new CustomCSVReader(step_is);
        try {
            List<String[]> rows;

            rows = customCsvReader.readCSV();

            for (String[] row:rows){
                ContentValues values = new ContentValues();

                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_STEPS_STEP, row[1]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_STEPS_RECIPE, row[2]);

                db.insert(FeedReaderContract.FeedEntry.TABLE_NAME_STEPS, null, values);
                Log.v("RECORD INSERTED IN DB",FeedReaderContract.FeedEntry.TABLE_NAME_STEPS);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //read INFO inputStream
        customCsvReader = new CustomCSVReader(info_is);
        try {
            List<String[]> rows;

            rows = customCsvReader.readCSV();

            for (String[] row:rows){
                ContentValues values = new ContentValues();

                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_RECIPE, row[1]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_SIZE, row[2]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CALORIES, row[3]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CARBS, row[4]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_PROTS, row[5]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_FATS, row[6]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_FATS_SAT, row[7]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_FATS_TRANS, row[8]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_FIBER, row[8]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_NA, row[10]);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CA, row[11]);

                db.insert(FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE_INFO, null, values);
                Log.v("RECORD INSERTED IN DB",FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE_INFO);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public boolean isTableEmpty(String TableName){
       SQLiteDatabase database = this.getReadableDatabase();
        int NoOfRows = (int) DatabaseUtils.queryNumEntries(database,TableName);

        if (NoOfRows == 0){
            return true;
        }else {
            return false;
        }
    }

    public int countRecords(SQLiteDatabase db,String table, String column, String value) {
        String countQuery = "SELECT  COUNT(*) as c FROM " + table + " WHERE " +
                column + " = " + "'" + value + "'";

        Cursor cursor = db.rawQuery(countQuery, null);
        Integer count = null;

        while(cursor.moveToNext()) {
            count = cursor.getInt(cursor.getColumnIndexOrThrow("c"));
        }
        cursor.close();
        return count;
    }



}
