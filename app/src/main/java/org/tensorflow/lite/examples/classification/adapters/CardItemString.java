package org.tensorflow.lite.examples.classification.adapters;

public class CardItemString {

    private String mTitleResource;

    public CardItemString(String title) {
        mTitleResource = title;
    }

    public String getTitle() {
        return mTitleResource;
    }
}