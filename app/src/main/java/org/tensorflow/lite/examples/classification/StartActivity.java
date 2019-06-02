package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StartActivity extends AppCompatActivity {

    SearchView searchView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        searchView = findViewById(R.id.search_view);
        fab = findViewById(R.id.floatingActionButton);

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
    }
}