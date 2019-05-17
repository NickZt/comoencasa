package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

public class RecipeByIngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_by_ingredient);

        //Get Intent data
        Intent intentIngredientValue = getIntent();
        String ingredient = intentIngredientValue.getStringExtra("ingredient");

        ImageView imageViewRecipeByIngredient = findViewById(R.id.imageViewRecipeByIngredient);

        for(int i = 0; i < Utils.ingredientList.length; i++){
            if(ingredient.equals(Utils.ingredientList[i])){
                Log.v("Ingredient match",Utils.ingredientList[i]);
                imageViewRecipeByIngredient.setImageResource(Utils.imageResouceIdSuggestedRecipesList[i]);
                imageViewRecipeByIngredient.setOnClickListener(v -> {
                    Intent intent = new Intent(this, RecipeActivity.class);
                    intent.putExtra("ingredient", ingredient);
                    startActivity(intent);});
                break;
            }
        }


        /*
        TextView tv = findViewById(R.id.textView_suggested_recipe_value);

        //Show ingredient in TextView
        tv.setText(ingredient);




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


        // Filter results WHERE "recipe_daytime_category" = 'breakfast'
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_DESCRIPTION + " LIKE ?";
        String[] selectionArgs = { "%"+ ingredient + "%"  };

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


            itemIds.add(itemId);
        }
        cursor.close();

        */



    }

    /*
    public void onBackPressed() {
        Intent intentBackPressed = new Intent(this, ClassifierActivity.class);
        startActivity(intentBackPressed);
    }
    */
}
