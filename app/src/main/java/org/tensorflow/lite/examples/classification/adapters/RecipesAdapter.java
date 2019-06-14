package org.tensorflow.lite.examples.classification.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import org.tensorflow.lite.examples.classification.R;

import java.util.ArrayList;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {
    private ArrayList<RecipeModel> mDataset;
    private LayoutInflater inflater;

    public RecipesAdapter(Context ctx, ArrayList<RecipeModel> myDataset) {
        inflater = LayoutInflater.from(ctx);
        mDataset = myDataset;
    }

    @Override
    public RecipesAdapter.RecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.recipe, parent, false);
        RecipesViewHolder vh = new RecipesViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecipesAdapter.RecipesViewHolder holder, int position) {
        holder.txtTitle.setText(mDataset.get(position).getTitle());
        holder.txtCalories.setText(mDataset.get(position).getCalories());
        holder.txtLength.setText(mDataset.get(position).getLength());
        holder.imgRecipe.setImageResource(mDataset.get(position).getPhoto());
        holder.btnFavorite.setChecked(mDataset.get(position).getFavorite());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    class RecipesViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtCalories;
        public TextView txtLength;
        public ImageView imgRecipe;
        public ToggleButton btnFavorite;

        public RecipesViewHolder(View recipeView) {
            super(recipeView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtCalories = itemView.findViewById(R.id.txtRecipeCalories);
            txtLength = itemView.findViewById(R.id.txtRecipeLength);
            imgRecipe = itemView.findViewById(R.id.imgRecipe);
            btnFavorite = itemView.findViewById(R.id.btnfavorite);
        }
    }
}
