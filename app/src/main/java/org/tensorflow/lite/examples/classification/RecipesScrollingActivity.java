package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.tensorflow.lite.examples.classification.adapters.RecipeModel;
import org.tensorflow.lite.examples.classification.adapters.RecipesAdapter;

import java.util.ArrayList;
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

        for(int i=0; i < 10; i++) {
            listEntrances.add(
                    new RecipeModel("Entrada " + i,
                            "154cal",
                            "54min",
                            R.drawable.food_icon,
                            new Random().nextBoolean()
                    )
            );
            listPrincipals.add(
                    new RecipeModel("Principal " + i,
                            "333cal",
                            "20min",
                            R.drawable.food_icon,
                            new Random().nextBoolean()
                    )
            );
            listDesserts.add(
                    new RecipeModel("Postre " + i,
                            "501cal",
                            "33min",
                            R.drawable.food_icon,
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
    }
}
