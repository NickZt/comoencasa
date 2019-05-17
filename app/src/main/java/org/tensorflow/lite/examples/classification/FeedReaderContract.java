package org.tensorflow.lite.examples.classification;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME_RECIPES = "recipes";
        public static final String COLUMN_NAME_RECIPE_DAYTIME_CATEGORY = "recipe_daytime_category";
        public static final String COLUMN_NAME_RECIPE_DESCRIPTION = "recipe_description";
        public static final String COLUMN_NAME_RECIPE_MAIN_IMAGE = "recipe_main_image";



        public static final String SQL_CREATE_RECIPES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME_RECIPES + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedEntry.COLUMN_NAME_RECIPE_DAYTIME_CATEGORY + " VARCHAR," +
                        FeedEntry.COLUMN_NAME_RECIPE_DESCRIPTION + " TEXT," +
                        FeedEntry.COLUMN_NAME_RECIPE_MAIN_IMAGE + " INTEGER" +
                        ")";

        public static final String SQL_DELETE_RECIPES =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_RECIPES;


    }


}