package com.westcoast.gyst.ui.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.orm.SugarContext;
import com.westcoast.gyst.R;
import com.westcoast.gyst.ui.courses.CourseActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_layout);
        SugarContext.init(this);

        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashScreenActivity.this, CourseActivity.class);
            startActivity(i);

            finish();
        }, SPLASH_TIME_OUT);
    }
}
