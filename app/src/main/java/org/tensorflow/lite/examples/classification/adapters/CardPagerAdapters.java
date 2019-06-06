package org.tensorflow.lite.examples.classification.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import org.tensorflow.lite.examples.classification.R;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapters extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItemString> mData;
    private float mBaseElevation;

    public CardPagerAdapters() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItemS(CardItemString item) {
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

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(CardItemString item, View view) {
        TextView titleTextView = view.findViewById(R.id.txtTitle);
        titleTextView.setText(item.getTitle());
    }

}
