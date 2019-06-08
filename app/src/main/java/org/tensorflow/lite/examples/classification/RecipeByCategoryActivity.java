package org.tensorflow.lite.examples.classification;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeByCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_by_category);




        /*

        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_DAYTIME_CATEGORY,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_DESCRIPTION,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_MAIN_IMAGE
        };


        //Get Intent data
        Intent intent = getIntent();
        String recipe_daytime_category = intent.getStringExtra("recipe_daytime_category");


        // Filter results WHERE "recipe_daytime_category" = 'breakfast'
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_DAYTIME_CATEGORY + " = ?";
        String[] selectionArgs = { recipe_daytime_category };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_DESCRIPTION + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME_RECIPES,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );


        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemDescription = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_DESCRIPTION));
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            int itemMainImage = cursor.getInt(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_MAIN_IMAGE));


            Log.d("Recipe _ID",Long.toString(itemId));
            Log.d("Recipe Description",itemDescription);
            Log.d("Recipe Main Image",Integer.toString(itemMainImage));

            TextView tv1 = findViewById(R.id.textView_recipe_by_category);
            tv1.setText(itemDescription);



            itemIds.add(itemId);
        }
        cursor.close();

        */



    }

}
