package com.westcoast.gyst.ui.overview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.orm.SugarContext;
import com.westcoast.gyst.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SugarContext.init(this);

    }

    public void delete() {

    }
}