package org.tensorflow.lite.examples.classification.adapters;

public class CardItemString {

    private String mTitleResource;
    private String mCaloriesResource;
    private String mLengthResource;
    private int mPhotoResource;

    public CardItemString(String title, String calories, String length, int photo) {
        mTitleResource = title;
        mCaloriesResource = calories;
        mLengthResource = length;
        mPhotoResource =  photo;
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

    public int getPhoto() {
        return mPhotoResource;
    }
}