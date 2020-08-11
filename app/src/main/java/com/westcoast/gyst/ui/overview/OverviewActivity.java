package com.westcoast.gyst.ui.overview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orm.SugarContext;
import com.westcoast.gyst.R;
import com.westcoast.gyst.db.adapters.OverviewAdapter;
import com.westcoast.gyst.db.entities.Grade;
import com.westcoast.gyst.db.entities.Student;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class OverviewActivity extends AppCompatActivity {
    private int schuelerId;
    private Student student;

    private boolean editMode = false;
    private String noGradesText = "Noch keine Noten";
    private String averageText;
    private double average;


    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private TextView fullName;
    private TextView averageGrade;
    private TextView email;
    private FloatingActionButton fab;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // init
        schuelerId = getIntent().getIntExtra("schuelerId",0);
        if (!(schuelerId > 0))
            return;

        SugarContext.init(this);

        student = Student.findById(Student.class, schuelerId);
        if (student == null)
            return;

        rv = (RecyclerView)findViewById(R.id.recycleView);
        if (rv == null)
            return;

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        refreshView();


        fab = findViewById(R.id.overview_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCreateDialog();
                refreshView();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SugarContext.terminate();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SugarContext.init(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void refreshView() {
        OverviewAdapter.OverviewViewHolder.counter = 1;
        double totalGradeValue;

        List<Grade> grades = Grade.find(Grade.class, "schueler_id = ?", String.valueOf(schuelerId));

        totalGradeValue = grades.stream().mapToDouble(x -> Double.parseDouble(replaceSetDecimalSeparator(x.getNote()))).sum();
        average = (grades.size() > 0) ? (totalGradeValue / grades.size()) : 0;
        average = round(average, 2);

        averageGrade = findViewById(R.id.student_average);
        averageGrade.setText(average != 0 ? ("Ø " + average) : noGradesText);

        fullName = findViewById(R.id.student_full_name);
        fullName.setText(student.getVorname() + " " + student.getNachname());


        email = findViewById(R.id.student_email);
        email.setText(!student.getEmail().isEmpty() ? "(" + student.getEmail() + ")" : "");

        adapter = new OverviewAdapter(grades);
        rv.setAdapter(adapter);
    }

    private String replaceSetDecimalSeparator(String s) {
        return s.replace(',', '.');
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void showCreateDialog(){
        CreateGradeDialog.display(getSupportFragmentManager(), schuelerId);
    }
}