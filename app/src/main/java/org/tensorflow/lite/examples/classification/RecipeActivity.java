package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        //Get Intent data
        Intent intentIngredientValue = getIntent();
        String ingredient = intentIngredientValue.getStringExtra("ingredient");

        ImageView imageViewSelection = findViewById(R.id.imageViewSelection);

        for(int i = 0; i < Utils.ingredientList.length; i++){
            if(ingredient.equals(Utils.ingredientList[i])){
                Log.v("Ingredient match recipe",Utils.ingredientList[i]);
                imageViewSelection.setImageResource(Utils.imageResouceIdSelectionList[i]);
                imageViewSelection.setOnClickListener(v -> {
                    Intent intent = new Intent(this, NutricionalInfoActivity.class);
                    intent.putExtra("ingredient", ingredient);
                    startActivity(intent);});
            }
        }




    }
}
