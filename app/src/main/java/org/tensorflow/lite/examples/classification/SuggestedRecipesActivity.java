package org.tensorflow.lite.examples.classification;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;

import org.tensorflow.lite.examples.classification.adapters.CardItemString;
import org.tensorflow.lite.examples.classification.adapters.CardPagerAdapters;
import org.tensorflow.lite.examples.classification.utils.ShadowTransformer;

import java.util.ArrayList;
import java.util.Arrays;

public class SuggestedRecipesActivity<ingredients> extends AppCompatActivity {

    private SearchView searchView;
    private ViewPager mViewPager;

    private CardPagerAdapters mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;

    String titlesText [] = {};

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        context = this;


        //Get Intent data
        Intent intentIngredients = getIntent();
        ArrayList<String> ingredients = intentIngredients.getStringArrayListExtra("ingredients");
        Log.v("INGREDIENTS OBTAINED",ingredients.toString());

        ingredients.add("papa");

        //Search recipes with ingredients
        //Get or create database
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT  COUNT(*) as c, " + FeedReaderContract.FeedEntry.COLUMN_NAME_INGREDIENT_RECIPE +

                " FROM " + FeedReaderContract.FeedEntry.TABLE_NAME_INGREDIENT + " WHERE ";

        for(String ingredient:ingredients){
            query += FeedReaderContract.FeedEntry.COLUMN_NAME_INGREDIENT_NAME + " LIKE " +
                    "'%" + ingredient + "%'" + " OR ";

        }
        query = query.substring(0,query.length()-3);
        query += "GROUP BY " + FeedReaderContract.FeedEntry.COLUMN_NAME_INGREDIENT_RECIPE;
        query += " ORDER BY c DESC";



        Log.v("QUERY",query);

        ArrayList<String> recipesName = new ArrayList<>();
        ArrayList<Integer> ingredientsCount = new ArrayList<>();
        ArrayList<String> recipesTime = new ArrayList<>();
        ArrayList<String> recipesCalorie = new ArrayList<>();


        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()) {
            String  recipeName = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_INGREDIENT_RECIPE));
            recipesName.add(recipeName);

            //Get time for each recipe
            String[] projection1 = {
                    BaseColumns._ID,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_NAME,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TIME
            };

            String selection1 = FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_NAME + " = ? ";
            String[] selectionArgs1 = { recipeName };

            Cursor cursor1 = db.query(
                    FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE,   // The table to query
                    projection1,             // The array of columns to return (pass null to get all)
                    selection1,              // The columns for the WHERE clause
                    selectionArgs1,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );


            while(cursor1.moveToNext()) {
                String  recipeTime = cursor1.getString(
                        cursor1.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TIME));
                recipesTime.add(recipeTime);

            }
            cursor1.close();

            //Get calories for each recipe
            String[] projection2 = {
                    BaseColumns._ID,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_RECIPE,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CALORIES
            };

            String selection2 = FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_RECIPE + " = ? ";
            String[] selectionArgs2 = { recipeName };

            Cursor cursor2 = db.query(
                    FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE_INFO,   // The table to query
                    projection2,             // The array of columns to return (pass null to get all)
                    selection2,              // The columns for the WHERE clause
                    selectionArgs2,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );


            while(cursor2.moveToNext()) {
                String  recipeCalorie = cursor2.getString(
                        cursor2.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CALORIES));
                recipesCalorie.add(recipeCalorie);

            }
            cursor2.close();

            Integer  ingredientCount = cursor.getInt(
                    cursor.getColumnIndexOrThrow("c"));
            ingredientsCount.add(ingredientCount);

        }
        cursor.close();
        Log.v("ING COUNT",ingredientsCount.toString());
        Log.v("RECIPES",recipesName.toString());
        Log.v("RECIPES TIME",recipesTime.toString());
        Log.v("RECIPES CAL",recipesCalorie.toString());


        ///Populate slider
        searchView = findViewById(R.id.search_view);
        mViewPager = findViewById(R.id.viewPager);
        mCardAdapter = new CardPagerAdapters();

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


        titlesText = Arrays.copyOf(recipesName.toArray(), recipesName.toArray().length, String[].class);

        for (int i=0; i<titlesText.length; i++){
            mCardAdapter.addCardItemS(
                    new CardItemString(titlesText[i], recipesCalorie.get(i), recipesTime.get(i), R.drawable.food_icon)
            );

            //DARÍO: AHÍ DEJÉ EL CONSTRUCTOR PARA QUE RECIBA DOS STRINGS (por las dudas) Y UN INT:
            //Calorías, duración y el int del id de recurso del drawable correspondiente.
        }

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);


        db.close();



    }
}
