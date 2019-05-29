package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    ListView list;
    //AdapterClass adapter;
    SearchView editsearch;
    String[] searchQueries;
    ArrayList<SearchQuery> arraylist;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        searchQueries = new String[] {
                "TextView", "ListView", "SearchView", "RatingBar", "ToolBar", "Button", "EditText",
                "ToggleButton", "ImageView", "SlidingDrawer", "Android"};
        arraylist = new ArrayList<>();

        list = findViewById(R.id.list_view);
        editsearch = findViewById(R.id.search_view);
        fab = findViewById(R.id.floatingActionButton);

        for (String strSearchQuery:searchQueries) {
            SearchQuery searchQuery = new SearchQuery(strSearchQuery);
            // Binds all strings into an array
            arraylist.add(searchQuery);
        }

        //adapter = new AdapterClass(this, arraylist);
        //list.setAdapter(adapter);
        editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                String text = newText;
                //adapter.filter(text);
                return false;
            }
        });

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, ClassifierActivity.class);
            startActivity(intent);
        });

        /*
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

}

class SearchQuery {

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    private String query;

    public SearchQuery(String query) {
        this.query = query;
    }
}
