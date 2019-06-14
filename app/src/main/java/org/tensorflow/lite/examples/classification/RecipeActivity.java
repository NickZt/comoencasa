package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        //Get Intent data
        Intent intentIngredientValue = getIntent();
        String recipe = intentIngredientValue.getStringExtra("recipe");
        String recipe_calorie = intentIngredientValue.getStringExtra("recipe_calorie");



        ToggleButton btnFavorite = findViewById(R.id.btnfavorite);
        btnFavorite.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast.makeText(this, "Button Favorite tapped", Toast.LENGTH_SHORT).show();
        });

//        ImageView imageViewSelection = findViewById(R.id.imageViewSelection);
//
//        for(int i = 0; i < Utils.ingredientList.length; i++){
//            if(ingredient.equals(Utils.ingredientList[i])){
//                Log.v("Ingredient match recipe",Utils.ingredientList[i]);
//                imageViewSelection.setImageResource(Utils.imageResouceIdSelectionList[i]);
//                imageViewSelection.setOnClickListener(v -> {
//                    Intent intent = new Intent(this, NutricionalInfoActivity.class);
//                    intent.putExtra("ingredient", ingredient);
//                    startActivity(intent);});
//            }
//        }


        //Get or create database
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //Get proyection
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_DESCRIPTION,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TIME
        };

        //Filter results
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_NAME + " = ?";
        String[] selectionArgs = { recipe };

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );


        ArrayList<String> recipesDescription = new ArrayList<>();
        ArrayList<String> recipesTime = new ArrayList<>();

        while(cursor.moveToNext()) {
            String  recipeDescription = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_DESCRIPTION));
            recipesDescription.add(recipeDescription);
            String  recipeTime = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_TIME));
            recipesTime.add(recipeTime);

        }
        cursor.close();


        TextView txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(recipe);
        TextView txtDescription = findViewById(R.id.txtDescription);
        txtDescription.setText(recipesDescription.get(0));
        TextView txtRecipeLength = findViewById(R.id.txtRecipeLength);
        txtRecipeLength.setText(recipesTime.get(0) + " minutos");
        TextView txtRecipeCalories = findViewById(R.id.txtRecipeCalories);
        txtRecipeCalories.setText(recipe_calorie);

        //Lists ingredients
        //Get proyection
        String[] projection1 = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_INGREDIENT_NAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_INGREDIENT_RECIPE,

        };

        //Filter results
        String selection1 = FeedReaderContract.FeedEntry.COLUMN_NAME_INGREDIENT_RECIPE + " = ?";
        String[] selectionArgs1 = { recipe };

        Cursor cursor1 = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME_INGREDIENT,   // The table to query
                projection1,             // The array of columns to return (pass null to get all)
                selection1,              // The columns for the WHERE clause
                selectionArgs1,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );


        StringBuilder sb = new StringBuilder();
        while(cursor1.moveToNext()) {
            String  ingredient = cursor1.getString(
                    cursor1.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_INGREDIENT_NAME));
            sb.append("- ");
            sb.append(ingredient);
            sb.append("\n");
        }
        cursor1.close();
        String ingredients = sb.toString();


        TextView txtIngredient = findViewById(R.id.txtIngredient);
        txtIngredient.setText(ingredients);
        Log.v("INGREDIENTS",ingredients);

        //Steps
        //Get proyection
        String[] projection2 = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_STEPS_RECIPE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_STEPS_STEP,

        };

        //Filter results
        String selection2 = FeedReaderContract.FeedEntry.COLUMN_NAME_STEPS_RECIPE + " = ?";
        String[] selectionArgs2 = { recipe };

        Cursor cursor2 = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME_STEPS,   // The table to query
                projection2,             // The array of columns to return (pass null to get all)
                selection2,              // The columns for the WHERE clause
                selectionArgs2,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );


        StringBuilder sb2 = new StringBuilder();
        while(cursor2.moveToNext()) {
            String step = cursor2.getString(
                    cursor2.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_STEPS_STEP));
            sb2.append(step);
            sb2.append("\n");
        }
        cursor2.close();
        String steps = sb2.toString();


        TextView txtElaboration = findViewById(R.id.txtElaboration);
        txtElaboration.setText(steps);
        Log.v("STEPS",steps);



        //Link to nutricional information
        TextView txtNutricionalInfo = findViewById(R.id.txtNutricionalInfo);
        txtNutricionalInfo.setOnClickListener(view -> {
            Intent intent = new Intent(this, NutritionalInfoActivity.class);
            intent.putExtra("recipe",recipe);
            startActivity(intent);
        });


        db.close();


        ImageView imgRecipe = findViewById(R.id.imgRecipe);
        imgRecipe.setImageResource(R.drawable.ic_milamburguesa_de_arroz_y_vegetales);

    }
}
