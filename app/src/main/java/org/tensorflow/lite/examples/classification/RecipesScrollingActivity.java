package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.tensorflow.lite.examples.classification.adapters.RecipeModel;
import org.tensorflow.lite.examples.classification.adapters.RecipesAdapter;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class RecipesScrollingActivity extends AppCompatActivity {

    private SearchView searchView;

    private RecyclerView mListEntrances;
    private RecyclerView.Adapter mAdapterEntrances;
    private RecyclerView.LayoutManager mLayoutManagerEntrances;

    private RecyclerView mListPrincipals;
    private RecyclerView.Adapter mAdapterPrincipals;
    private RecyclerView.LayoutManager mLayoutManagerPrincipals;

    private RecyclerView mListDesserts;
    private RecyclerView.Adapter mAdapterDesserts;
    private RecyclerView.LayoutManager mLayoutManagerDesserts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_scrolling);

        searchView = findViewById(R.id.search_view);
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

        mListEntrances = findViewById(R.id.entrancesRecipesList);
        mListEntrances.setHasFixedSize(true);
        mLayoutManagerEntrances = new LinearLayoutManager(this);
        mListEntrances.setLayoutManager(mLayoutManagerEntrances);

        mListPrincipals = findViewById(R.id.principalRecipesList);
        mListPrincipals.setHasFixedSize(true);
        mLayoutManagerPrincipals = new LinearLayoutManager(this);
        mListPrincipals.setLayoutManager(mLayoutManagerPrincipals);

        mListDesserts = findViewById(R.id.dessertsRecipesList);
        mListDesserts.setHasFixedSize(true);
        mLayoutManagerDesserts = new LinearLayoutManager(this);
        mListDesserts.setLayoutManager(mLayoutManagerDesserts);



        ArrayList<RecipeModel> listEntrances = new ArrayList<>();
        ArrayList<RecipeModel> listPrincipals = new ArrayList<>();
        ArrayList<RecipeModel> listDesserts = new ArrayList<>();

        //Get or create database
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();



        /////ENTRADAS/////
        //Get recipes/time in each category
        String[] projection1 = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_NAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TIME
        };

        String selection1 = FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TYPE + " = ? ";
        String[] selectionArgs1 = { "entrada" };

        Cursor cursor1 = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE,   // The table to query
                projection1,             // The array of columns to return (pass null to get all)
                selection1,              // The columns for the WHERE clause
                selectionArgs1,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );


        ArrayList<String> recipesName1 = new ArrayList<>();
        ArrayList<String> recipesTimes1 = new ArrayList<>();
        ArrayList<String> recipesCalorie1 = new ArrayList<>();



        while(cursor1.moveToNext()) {
            String recipeName = cursor1.getString(
                    cursor1.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_NAME));
            recipesName1.add(recipeName);
            String recipeTime = cursor1.getString(
                    cursor1.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TIME));
            recipesTimes1.add(recipeTime);

            //Get calories for each recipe
            String[] projection11 = {
                    BaseColumns._ID,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_RECIPE,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CALORIES
            };

            String selection11 = FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_RECIPE + " = ? ";
            String[] selectionArgs11 = { recipeName };

            Cursor cursor11 = db.query(
                    FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE_INFO,   // The table to query
                    projection11,             // The array of columns to return (pass null to get all)
                    selection11,              // The columns for the WHERE clause
                    selectionArgs11,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );


            while(cursor11.moveToNext()) {
                String  recipeCalorie = cursor11.getString(
                        cursor11.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CALORIES));
                recipesCalorie1.add(recipeCalorie);

            }
            cursor11.close();


        }
        cursor1.close();

        Log.v("ENTRADAS",recipesName1.toString());
        Log.v("ENTRADAS TIME",recipesTimes1.toString());
        Log.v("ENTRADAS CAL",recipesCalorie1.toString());

        /////PRINCIPAL/////
        //Get recipes/time in each category
        String[] projection2 = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_NAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TIME
        };

        String selection2 = FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TYPE + " = ? ";
        String[] selectionArgs2 = { "principal" };

        Cursor cursor2 = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE,   // The table to query
                projection2,             // The array of columns to return (pass null to get all)
                selection2,              // The columns for the WHERE clause
                selectionArgs2,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );


        ArrayList<String> recipesName2 = new ArrayList<>();
        ArrayList<String> recipesTimes2 = new ArrayList<>();
        ArrayList<String> recipesCalorie2 = new ArrayList<>();



        while(cursor2.moveToNext()) {
            String recipeName = cursor2.getString(
                    cursor2.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_NAME));
            recipesName2.add(recipeName);
            String recipeTime = cursor2.getString(
                    cursor2.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TIME));
            recipesTimes2.add(recipeTime);

            //Get calories for each recipe
            String[] projection22 = {
                    BaseColumns._ID,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_RECIPE,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CALORIES
            };

            String selection22 = FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_RECIPE + " = ? ";
            String[] selectionArgs22 = { recipeName };

            Cursor cursor22 = db.query(
                    FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE_INFO,   // The table to query
                    projection22,             // The array of columns to return (pass null to get all)
                    selection22,              // The columns for the WHERE clause
                    selectionArgs22,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );


            while(cursor22.moveToNext()) {
                String  recipeCalorie = cursor22.getString(
                        cursor22.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CALORIES));
                recipesCalorie2.add(recipeCalorie);

            }
            cursor22.close();


        }
        cursor2.close();

        Log.v("PRINCIPAL",recipesName2.toString());
        Log.v("PRINCIPAL TIME",recipesTimes2.toString());
        Log.v("PRINCIPAL CAL",recipesCalorie2.toString());



        /////POSTRE/////
        //Get recipes/time in each category
        String[] projection3 = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_NAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TIME
        };

        String selection3 = FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TYPE + " = ? ";
        String[] selectionArgs3 = { "postre" };

        Cursor cursor3 = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE,   // The table to query
                projection3,             // The array of columns to return (pass null to get all)
                selection3,              // The columns for the WHERE clause
                selectionArgs3,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );


        ArrayList<String> recipesName3 = new ArrayList<>();
        ArrayList<String> recipesTimes3 = new ArrayList<>();
        ArrayList<String> recipesCalorie3 = new ArrayList<>();



        while(cursor3.moveToNext()) {
            String recipeName = cursor3.getString(
                    cursor3.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_NAME));
            recipesName3.add(recipeName);
            String recipeTime = cursor3.getString(
                    cursor3.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TIME));
            recipesTimes3.add(recipeTime);

            //Get calories for each recipe
            String[] projection33 = {
                    BaseColumns._ID,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_RECIPE,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CALORIES
            };

            String selection33 = FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_RECIPE + " = ? ";
            String[] selectionArgs33 = { recipeName };

            Cursor cursor33 = db.query(
                    FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE_INFO,   // The table to query
                    projection33,             // The array of columns to return (pass null to get all)
                    selection33,              // The columns for the WHERE clause
                    selectionArgs33,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );


            while(cursor33.moveToNext()) {
                String  recipeCalorie = cursor33.getString(
                        cursor33.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CALORIES));
                recipesCalorie3.add(recipeCalorie);

            }
            cursor33.close();


        }
        cursor3.close();

        Log.v("POSTRE",recipesName3.toString());
        Log.v("POSTRE TIME",recipesTimes3.toString());
        Log.v("POSTRE CAL",recipesCalorie3.toString());



        db.close();


        for(int i = 0; i < Objects.requireNonNull(recipesName1.toArray()).length; i++) {
            int resID = getResources().getIdentifier(
                    "ic_" + recipesName1.get(i).toLowerCase().replace(" ","_").
                            replace("ñ","n") ,
                    "drawable",
                    "org.tensorflow.lite.examples.classification");

            listEntrances.add(
                    new RecipeModel(recipesName1.get(i),
                            recipesCalorie1.get(i),
                            recipesTimes1.get(i),
                            resID,
                            new Random().nextBoolean()
                    )
            );

        }



        for(int i = 0; i < Objects.requireNonNull(recipesName2.toArray()).length; i++) {
            int resID = getResources().getIdentifier(
                    "ic_" + recipesName2.get(i).toLowerCase().replace(" ","_").
                            replace("ó","o") ,
                    "drawable",
                    "org.tensorflow.lite.examples.classification");

            listPrincipals.add(
                    new RecipeModel(recipesName2.get(i),
                            recipesCalorie2.get(i),
                            recipesTimes2.get(i),
                            resID,
                            new Random().nextBoolean()
                    )
            );
        }
        for(int i = 0; i < Objects.requireNonNull(recipesName3.toArray()).length; i++) {
            int resID = getResources().getIdentifier(
                    "ic_" + recipesName3.get(i).toLowerCase().replace(" ","_").
                            replace("á","a") ,
                    "drawable",
                    "org.tensorflow.lite.examples.classification");

            listDesserts.add(
                    new RecipeModel(recipesName3.get(i),
                            recipesCalorie3.get(i),
                            recipesTimes3.get(i),
                            resID,
                            new Random().nextBoolean()
                    )
            );
        }

        mAdapterEntrances = new RecipesAdapter(this, listEntrances);
        mListEntrances.setAdapter(mAdapterEntrances);
        mListEntrances.setLayoutManager(
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        mAdapterPrincipals = new RecipesAdapter(this, listPrincipals);
        mListPrincipals.setAdapter(mAdapterPrincipals);
        mListPrincipals.setLayoutManager(
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        mAdapterDesserts = new RecipesAdapter(this, listDesserts);
        mListDesserts.setAdapter(mAdapterDesserts);
        mListDesserts.setLayoutManager(
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        FloatingActionButton fab = findViewById(R.id.fbtnCamera);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, ClassifierActivity.class);
            startActivity(intent);
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ClassifierActivity.class);
        startActivity(intent);

    }
}
