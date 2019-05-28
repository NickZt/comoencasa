package org.tensorflow.lite.examples.classification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

public class ScrollingActivity extends AppCompatActivity {

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        /*
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());


        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //dbHelper.onDelete(db);
        //dbHelper.onCreate(db);

        // Create a new map of values, where column names are the keys
        //Later change. Loop values in text file
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_DAYTIME_CATEGORY, "starter");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_DESCRIPTION, "Starter 1");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_MAIN_IMAGE, R.drawable.breakfast);
        //values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_MAIN_IMAGE, "image");


        // Insert the new row, returning the primary key value of the new row
        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME_RECIPES, null, values);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            startActivity(new Intent(this, ClassifierActivity.class));
        });


        ImageView imageViewStarter = findViewById(R.id.imageView_starter);
        //@Override
        imageViewStarter.setOnClickListener(v -> {
                Log.v("type of meal", "starter");
                Intent intent = new Intent(this, RecipeByCategoryActivity.class);
                intent.putExtra("recipe_daytime_category", "starter");
                startActivity(intent);
        });

        ImageView imageViewMain = findViewById(R.id.imageView_main);
        //@Override
        imageViewMain.setOnClickListener(v -> {
            Log.v("type of meal", "main");
            Intent intent = new Intent(this, RecipeByCategoryActivity.class);
            intent.putExtra("recipe_daytime_category", "main");
            startActivity(intent);
        });
        ImageView imageViewDessert = findViewById(R.id.imageView_dessert);
        //@Override
        imageViewDessert.setOnClickListener(v -> {
            Log.v("type of meal", "dessert");
            Intent intent = new Intent(this, RecipeByCategoryActivity.class);
            intent.putExtra("recipe_daytime_category", "dessert");
            startActivity(intent);
        });

        */

        ImageView imageViewScrolling = findViewById(R.id.imageViewScrolling);
        //@Override
        imageViewScrolling.setOnClickListener(v -> {
            Intent intent = new Intent(this, ClassifierActivity.class);
            startActivity(intent);
        });


    }



}