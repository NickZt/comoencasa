package org.tensorflow.lite.examples.classification;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class FoodsFoundCameraActivity extends AppCompatActivity {

    ChipGroup chipGroup;
    String[] foods = {"PPPP", "Zucchini", "Ajo", "Berenjena", "Zanahoria", "Cebolla", "Lechuga"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_found_camera);

        chipGroup = findViewById(R.id.foodChipGroup);

        LayoutInflater inflater = LayoutInflater.from(FoodsFoundCameraActivity.this);
        for(String food : foods)
        {
            Chip chip = (Chip)inflater.inflate(R.layout.food_chip_item, null, false);
            chip.setText(food);
            chip.setOnCloseIconClickListener(view -> chipGroup.removeView(view));
            chipGroup.addView(chip);
        }
    }

}
