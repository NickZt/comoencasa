package org.tensorflow.lite.examples.classification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;

import org.tensorflow.lite.examples.classification.adapters.CardItemString;
import org.tensorflow.lite.examples.classification.adapters.CardPagerAdapters;
import org.tensorflow.lite.examples.classification.utils.ShadowTransformer;

public class SuggestedRecipesActivity<ingredients> extends AppCompatActivity {

    private SearchView searchView;
    private ViewPager mViewPager;

    private CardPagerAdapters mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;










    String titlesText [] = {"Bocaditos de coliflor", " Time Table 1", " Time Table 2", " Time Table 3", " Time Table 4", " Time Table 5",
            " Time Table 6", " Time Table 7", " Time Table 8", " Time Table 9"};

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        context = this;

        searchView = findViewById(R.id.search_view);
        mViewPager = findViewById(R.id.viewPager);
        mCardAdapter = new CardPagerAdapters();

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

        for (int i=0; i<titlesText.length; i++){
            mCardAdapter.addCardItemS(new CardItemString( titlesText[i]));
        }

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);


        //Get Intent data
        Intent intentIngredients = getIntent();
        String ingredients = intentIngredients.getStringExtra("ingredients");
        Log.v("INGREDIENTS OBTAINED",ingredients);

        //Search recipes with ingredients
        //TODO
        //TODO
        //TODO
        //TODO
        //TODO




    }
}
