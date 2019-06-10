package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    SearchView searchView;
    FloatingActionButton fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        searchView = findViewById(R.id.search_view);
        fab = findViewById(R.id.fbtnCamera);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnClickListener(v -> searchView.setIconified(false));

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, ClassifierActivity.class);
            startActivity(intent);
        });

        //Get or create database
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //Insert records if table RECIPE is empty
        if(dbHelper.isTableEmpty(FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE)){
            try{
                InputStream recipe_is = getAssets().open(FeedReaderContract.FeedEntry.RECIPE_CSV);
                InputStream ingredient_is = getAssets().open(FeedReaderContract.FeedEntry.INGREDIENT_CSV);
                InputStream step_is = getAssets().open(FeedReaderContract.FeedEntry.STEP_CSV);
                InputStream recipe_info_is = getAssets().open(FeedReaderContract.FeedEntry.RECIPE_INFO_CSV);

                dbHelper.onInsert(db,recipe_is, ingredient_is,step_is,recipe_info_is);
            }catch (IOException e){
                e.printStackTrace();
            }

        }

        //Read DB and populate values

        String countByRecipeType = "(" + dbHelper.countRecords(db, FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TYPE,"entrada") + ")";
        TextView txtGroupCount1 = findViewById(R.id.txtGroupCount1);
        txtGroupCount1.setText(countByRecipeType);

        countByRecipeType = "(" + dbHelper.countRecords(db, FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TYPE,"principal") + ")";
        TextView txtGroupCount2 = findViewById(R.id.txtGroupCount2);
        txtGroupCount2.setText(countByRecipeType);

        countByRecipeType = "(" + dbHelper.countRecords(db, FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TYPE,"postre") + ")";
        TextView txtGroupCount3 = findViewById(R.id.txtGroupCount3);
        txtGroupCount3.setText(countByRecipeType);


        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_NAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TIME
        };


        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        ArrayList<String> recipesName = new ArrayList<>();
        ArrayList<String> recipesTime = new ArrayList<>();
        ArrayList<Integer> recipesID = new ArrayList<>();


        while(cursor.moveToNext()) {
            String  recipeName = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_NAME));
            recipesName.add(recipeName);
            String  recipeTime = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TIME));
            recipesTime.add(recipeTime);
            Integer  recipeID = cursor.getInt(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            recipesID.add(recipeID);

        }
        cursor.close();

        //COULD BE RANDOM RECIPES AT THE BEGINNING
        String recipe1 = recipesName.get(0);
        String recipe2 = recipesName.get(1);
        String recipe3 = recipesName.get(2);

        TextView txtRecipeName1 = findViewById(R.id.txtRecipeName1);
        txtRecipeName1.setText(recipe1);

        TextView txtRecipeName2 = findViewById(R.id.txtRecipeName2);
        txtRecipeName2.setText(recipe2);

        TextView txtRecipeName3 = findViewById(R.id.txtRecipeName3);
        txtRecipeName3.setText(recipe3);

        TextView txtRecipeLength1 = findViewById(R.id.txtRecipeLength1);
        txtRecipeLength1.setText(recipesTime.get(0) + " min");

        TextView txtRecipeLength2 = findViewById(R.id.txtRecipeLength2);
        txtRecipeLength2.setText(recipesTime.get(1) + " min");

        TextView txtRecipeLength3 = findViewById(R.id.txtRecipeLength3);
        txtRecipeLength3.setText(recipesTime.get(2) + " min");


        String[] projection2 = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_RECIPE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CALORIES
        };

        String selection2 = FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_RECIPE + " IN ( ? , ?, ? )";
        String[] selectionArgs2 = { recipe1, recipe2, recipe3 };

        Cursor cursor2 = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE_INFO,   // The table to query
                projection2,             // The array of columns to return (pass null to get all)
                selection2,              // The columns for the WHERE clause
                selectionArgs2,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        ArrayList<String> recipesCalorie = new ArrayList<>();

        while(cursor2.moveToNext()) {
            String  recipeCalorie = cursor2.getString(
                    cursor2.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CALORIES));
            recipesCalorie.add(recipeCalorie);

        }
        cursor2.close();

        TextView txtRecipeCalories1 = findViewById(R.id.txtRecipeCalories1);
        txtRecipeCalories1.setText(recipesCalorie.get(0));

        TextView txtRecipeCalories2 = findViewById(R.id.txtRecipeCalories2);
        txtRecipeCalories2.setText(recipesCalorie.get(1));

        TextView txtRecipeCalories3 = findViewById(R.id.txtRecipeCalories3);
        txtRecipeCalories3.setText(recipesCalorie.get(2));


        ImageView imgRecipe1 = findViewById(R.id.imgRecipe1);
        imgRecipe1.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipeActivity.class);
            intent.putExtra("recipe",recipe1);
            intent.putExtra("recipe_calorie",recipesCalorie.get(0));
            intent.putExtra("recipe_id",recipesID.get(0));

            startActivity(intent);
        });

        ImageView imgRecipe2 = findViewById(R.id.imgRecipe2);
        imgRecipe2.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipeActivity.class);
            intent.putExtra("recipe",recipe2);
            intent.putExtra("recipe_calorie",recipesCalorie.get(1));
            intent.putExtra("recipe_id",recipesID.get(1).toString());
            startActivity(intent);
        });
        ImageView imgRecipe3 = findViewById(R.id.imgRecipe3);
        imgRecipe3.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipeActivity.class);
            intent.putExtra("recipe",recipe3);
            intent.putExtra("recipe_calorie",recipesCalorie.get(2));
            intent.putExtra("recipe_id",recipesID.get(2));
            startActivity(intent);
        });



    }
}