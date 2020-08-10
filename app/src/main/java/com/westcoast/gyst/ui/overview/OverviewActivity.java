package com.westcoast.gyst.ui.overview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.orm.SugarContext;
import com.westcoast.gyst.R;
import com.westcoast.gyst.db.adapters.CourseAdapter;
import com.westcoast.gyst.db.adapters.OverviewAdapter;
import com.westcoast.gyst.db.entities.Course;
import com.westcoast.gyst.db.entities.Grade;

import java.util.List;

public class OverviewActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private int schuelerId;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SugarContext.init(this);

        schuelerId = getIntent().getIntExtra("schuelerId",0);

        refresh();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void refresh() {
        long totalGradeValue;
        long average;
        List<Grade> grades = Grade.find(Grade.class, "schuelerId = ?", String.valueOf(schuelerId));

        totalGradeValue = grades.stream().mapToLong(x -> Long.parseLong(x.getNote())).sum();
        average = totalGradeValue / grades.size();

        adapter = new OverviewAdapter(grades, average);
        rv.setAdapter(adapter);
    }

    public void delete() {

    }
}