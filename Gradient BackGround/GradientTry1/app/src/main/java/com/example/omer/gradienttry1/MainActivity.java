package com.example.omer.gradienttry1;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View    layout  =   findViewById(R.id.mainLayout);

        GradientDrawable    gradientDrawable    =   new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]   {0xff45c0cc,0xff0054a6}
        );
        gradientDrawable.setCornerRadius(0f);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            layout.setBackground(gradientDrawable);
        }
    }
}
