package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {
            Intent i = new Intent(MenuActivity.this, StartActivity.class);
            startActivity(i);
        });

        Button btnSuggestedRecipes = findViewById(R.id.btnSuggestedRecipes);
        btnSuggestedRecipes.setOnClickListener(v -> {
            Intent i = new Intent(MenuActivity.this, SuggestedRecipesActivity.class);
            startActivity(i);
        });

        Button btnFoodsFound = findViewById(R.id.btnFoodsFound);
        btnFoodsFound.setOnClickListener(v -> {
            Intent i = new Intent(MenuActivity.this, FoodsFoundCameraActivity.class);
            startActivity(i);
        });
    }
}
