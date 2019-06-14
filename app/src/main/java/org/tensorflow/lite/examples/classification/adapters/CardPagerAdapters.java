package org.tensorflow.lite.examples.classification.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.RecipeActivity;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapters extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<RecipeModel> mData;
    private float mBaseElevation;

    public CardPagerAdapters() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItemS(RecipeModel item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.suggested_recipe, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = view.findViewById(R.id.cardView);

        ToggleButton btnFavorite = cardView.findViewById(R.id.btnfavorite);
        btnFavorite.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast.makeText(cardView.getContext(), "Button Favorite tapped", Toast.LENGTH_SHORT).show();
        });

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setOnClickListener(v ->
        {
            // Darío: Agregar lógica para redirigir a pantalla de receta.

            Intent intent = new Intent(v.getContext(), RecipeActivity.class);
            intent.putExtra("recipe",mData.get(position).getTitle());
            intent.putExtra("recipe_calorie",mData.get(position).getCalories());

            v.getContext().startActivity(intent);
        });

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(RecipeModel item, View view) {
        TextView titleTextView = view.findViewById(R.id.txtTitle);
        TextView caloriesTextView = view.findViewById(R.id.txtRecipeCalories);
        TextView lengthTextView = view.findViewById(R.id.txtRecipeLength);
        ImageView photoImageView = view.findViewById(R.id.imgRecipe);

        titleTextView.setText(item.getTitle());
        caloriesTextView.setText(item.getCalories());
        lengthTextView.setText(item.getLength());
        photoImageView.setImageResource(item.getPhoto());
    }

}
