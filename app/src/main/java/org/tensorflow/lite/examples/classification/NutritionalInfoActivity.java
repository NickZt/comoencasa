package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class NutritionalInfoActivity extends AppCompatActivity {

    ChipGroup chipGroup;
    String[] nutrients = {"Grasas", "Carbohidratos", "Proteínas", "Colesterol",
            "Azúcares", "Fibra alimentaria", "Sodio", "Hierro", "Calorías"};

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
            chipGroup.addView(chip);
        }
    }

    private void setTextInNutrientDetail(String n) {

    }
}
