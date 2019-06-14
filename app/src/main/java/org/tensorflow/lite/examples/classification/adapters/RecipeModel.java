package org.tensorflow.lite.examples.classification.adapters;

public class RecipeModel {

    private String mTitleResource;
    private String mCaloriesResource;
    private String mLengthResource;
    private int mPhotoResource;
    private boolean mFavoriteResource;

    public RecipeModel(String title, String calories, String length, int photo, boolean isFavorite) {
        mTitleResource = title;
        mCaloriesResource = calories;
        mLengthResource = length;
        mPhotoResource =  photo;
        mFavoriteResource = isFavorite;
    }

    public String getTitle() {
        return mTitleResource;
    }

    public String getCalories() {
        return mCaloriesResource;
    }

    public String getLength() {
        return mLengthResource;
    }

    public int getPhoto() { return mPhotoResource; }

    public boolean getFavorite() { return mFavoriteResource; }
}