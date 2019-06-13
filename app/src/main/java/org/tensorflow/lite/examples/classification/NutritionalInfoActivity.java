package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class NutritionalInfoActivity extends AppCompatActivity {

    ChipGroup chipGroup;
    String[] nutrients = {"Calorias", "Calcio", "Sodio", "Fibra alimentaria",
            "Grasas", "Grasas saturadas", "Grasas trans", "Proteinas", "Carbohidratos"};

    ArrayList<String> nutricionalInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritional_info);

        chipGroup = findViewById(R.id.nutrientChipGroup);

        LayoutInflater inflater = LayoutInflater.from(NutritionalInfoActivity.this);
        for(String n : nutrients)
        {
            Chip chip = (Chip)inflater.inflate(R.layout.nutrient_chip_item, null, false);
            chip.setText(n);
            chip.setOnCloseIconClickListener(view -> setTextInNutrientDetail(n));
            chip.setOnClickListener(view -> setTextInNutrientDetail(n));
            chipGroup.addView(chip);
        }


        //Get Intent data
        Intent intentIngredientValue = getIntent();
        String recipe = intentIngredientValue.getStringExtra("recipe");

        //Get or create database
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //Get proyection
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_RECIPE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CALORIES,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CA,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_NA,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_FIBER,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_FATS,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_FATS_SAT,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_FATS_TRANS,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_PROTS,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CARBS,
                FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_SIZE,

        };

        //Filter results
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_RECIPE + " = ?";
        String[] selectionArgs = { recipe };

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME_RECIPE_INFO,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {
            String  calories = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CALORIES));
            String  ca = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CA));
            String  na = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_NA));
            String  fiber = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_FIBER));
            String  fats = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_FATS));
            String  fats_sat = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_FATS_SAT));
            String  fats_trans = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_FATS_TRANS));
            String  prots = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_PROTS));
            String  carbs = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_CARBS));
            String  size = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_RECIPE_INFO_SIZE));
            nutricionalInfo.add(calories);//0
            nutricionalInfo.add(ca);//1
            nutricionalInfo.add(na);//2
            nutricionalInfo.add(fiber);//3
            nutricionalInfo.add(fats);//4
            nutricionalInfo.add(fats_sat);//5
            nutricionalInfo.add(fats_trans);//6
            nutricionalInfo.add(prots);//7
            nutricionalInfo.add(carbs);//8
            nutricionalInfo.add(size);//9

        }
        cursor.close();


        //Set values of nutricional info
        TextView infoSize = findViewById(R.id.infoSize);
        infoSize.setText("Basado en una porcíon ( " + nutricionalInfo.get(9) + " )");

        TextView txtProteinsValue = findViewById(R.id.txtProteinsValue);
        txtProteinsValue.setText(nutricionalInfo.get(7));

        TextView txtCaloriesKCalValue = findViewById(R.id.txtCaloriesKCalValue);
        txtCaloriesKCalValue.setText(nutricionalInfo.get(0));

        TextView txtCarbohydratesIncValue = findViewById(R.id.txtCarbohydratesIncValue);
        txtCarbohydratesIncValue.setText(nutricionalInfo.get(8));

        TextView txtDietaryFiberValue = findViewById(R.id.txtDietaryFiberValue);
        txtDietaryFiberValue.setText(nutricionalInfo.get(3));

        TextView txtFatsValue = findViewById(R.id.txtFatsValue);
        txtFatsValue.setText(nutricionalInfo.get(4));

        TextView txtSaturatedFatsValue = findViewById(R.id.txtSaturatedFatsValue);
        txtSaturatedFatsValue.setText(nutricionalInfo.get(5));

        TextView txtTransFatsValue = findViewById(R.id.txtTransFatsValue);
        txtTransFatsValue.setText(nutricionalInfo.get(6));

        TextView txtSodiumValue = findViewById(R.id.txtSodiumValue);
        txtSodiumValue.setText(nutricionalInfo.get(2));

        TextView txtCalciumValue = findViewById(R.id.txtCalciumValue);
        txtCalciumValue.setText(nutricionalInfo.get(1));


        db.close();

    }


    private void setTextInNutrientDetail(String n) {
        Integer i = Arrays.asList(nutrients).indexOf(n);
        TextView nutrientDetail = findViewById(R.id.nutrientDetail);
        nutrientDetail.setText(nutricionalInfo.get(i));
    }
}
