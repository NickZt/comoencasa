package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PresentationActivity extends AppCompatActivity {
    private static int TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(PresentationActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        }, TIME_OUT);

    }
}
