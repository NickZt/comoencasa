package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class NutricionalInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutricional_info);


        //Get Intent data
        Intent intentIngredientValue = getIntent();
        String ingredient = intentIngredientValue.getStringExtra("ingredient");

        ImageView imageViewNutricionalInfo = findViewById(R.id.imageViewNutricionalInfo);

        for(int i = 0; i < Utils.ingredientList.length; i++){
            if(ingredient.equals(Utils.ingredientList[i])){
                Log.v("Ingredient match info",Utils.ingredientList[i]);
                imageViewNutricionalInfo.setImageResource(Utils.imageResouceIdNuticionalInfoList[i]);
                imageViewNutricionalInfo.setOnClickListener(v -> {
                    Intent intent = new Intent(this, ScrollingActivity.class);
                    startActivity(intent);});
            }
        }
    }
}
